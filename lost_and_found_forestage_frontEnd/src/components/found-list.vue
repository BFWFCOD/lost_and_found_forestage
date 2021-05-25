<template>
  <div v-loading="">
    <div
      style="display: flex; justify-content: space-around; flex-wrap: wrap"
      v-infinite-scroll="load"
      infinite-scroll-disabled="disabled"
      infinite-scroll-distance="100"
    >
      <el-card
        style="width: 330px; margin-top: 10px"
        v-for="(pickthing, index) in dataList"
        :key="index"
        v-loading="cardloading[index]"
        shadow="always"
      >
        <div slot="header" style="text-align: left">
          <span>{{ pickthing.thingsName }}</span>
        </div>
        <div>
          <div v-if="pickthing.thingsImg">
            <el-image
              :src="pickthing.thingsImg"
              :alt="pickthing.thingsName"
              :preview-src-list="[pickthing.thingsImg]"
              lazy
            >
              <div slot="placeholder" class="image-slot">
                加载中<span class="dot">...</span>
              </div>
              <div slot="error" class="image-slot">
                <i class="el-icon-picture-outline"></i>
              </div>
            </el-image>
          </div>
          <div
            style="
              text-align: left;
              color: #000;
              font-size: 12px;
              margin-top: 13px;
            "
            v-if="pickthing.thingsDes"
          >
            <span>物品描述: </span><span>{{ pickthing.thingsDes }}</span>
          </div>
          <div style="color: #000; font-size: 12px; margin-top: 13px">
            <el-tag
              :key="pickthing.pickPlace"
              size="mini"
              style="margin-right: 8px"
              type="success"
              effect="plain"
            >
              {{ pickthing.pickPlace }}
            </el-tag>
          </div>
          <div
            style="color: #000; font-size: 12px; margin-top: 13px"
            v-if="pickthing.storagePlace"
          >
            <el-tag
              :key="pickthing.storagePlace"
              size="mini"
              style="margin-right: 8px"
              type="warning"
              effect="plain"
            >
              {{ pickthing.storagePlace }}
            </el-tag>
          </div>

          <div
            style="
              text-align: right;
              color: #000;
              font-size: 12px;
              margin-top: 13px;
            "
          >
            <el-tag size="mini" type="" effect="dark"> 招领启事 </el-tag>
          </div>
          <div
            style="
              text-align: left;
              color: #000;
              font-size: 12px;
              margin-top: 13px;
              display: flex;
              align-items: center;
            "
          >
            <el-switch
              v-model="pickthing.status"
              active-text="已认领"
              active-color="#13ce66"
              inactive-text="未认领"
              inactive-color="#ff4949"
              :active-value="0"
              :inactive-value="1"
              disabled
            >
            </el-switch>
          </div>
          <div
            style="
              text-align: left;
              color: #20a0ff;
              font-size: 12px;
              margin-top: 13px;
            "
            v-if="userId != 0"
          >
            <el-button
              type="info"
              icon="el-icon-s-custom"
              size="small"
              style="margin-left: 3px"
              @click="lookInfoClick(pickthing)"
              >查看发布者信息
            </el-button>
          </div>
          <div
            style="
              text-align: right;
              color: #20a0ff;
              font-size: 12px;
              margin-top: 13px;
            "
          >
            <!-- <span>时间: </span> -->
            <span>{{ pickthing.publishTime | formatDate }}</span>
          </div>
        </div>
      </el-card>
    </div>
    <!-- <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper"
    >
    </el-pagination> -->
    <el-alert
      v-if="dataListLoading"
      title="加载中..."
      type="success"
      center
      :closable="false"
      show-icon
    >
    </el-alert>
    <el-alert
      v-if="noMore"
      title="碧刷了，一滴没有了"
      type="warning"
      center
      show-icon
    >
    </el-alert>
    <!-- <div v-if="dataListLoading || noMore" style="text-align: center">
      <p v-if="dataListLoading">加载中...</p>
      <p v-if="noMore">碧刷了，没有了</p>
    </div> -->

    <look-user-info
      v-if="userinfovisible"
      ref="lookUserInfo"
      :click_pickthings_data="click_pickthings_data"
    ></look-user-info>
  </div>
</template>

<script>
// 这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
// 例如：import '组件名称' from '组件路径';
import lookUserInfo from "./look-user-info";
export default {
  components: { lookUserInfo }, // import引入或定义的局部的组件需要在此处注入到vue对象中才能使用
  props: ["home_key"],
  data() {
    // 这里存放需要用到的数据
    return {
      click_pickthings_data: {},
      cardloading: [],
      key: "",
      dataList: [],
      pageIndex: 1,
      pageSize: 3,
      totalPage: 0,
      dataListLoading: false,
      noMore: false,
      userinfovisible: false
    }; // 将页面需要用到的数据返回
  },
  computed: {
    disabled() {
      return this.dataListLoading || this.noMore;
    },
    userId: {
      get() {
        return this.$store.state.user.id;
      },
      set(val) {
        this.$store.commit("user/updateId", val);
      }
    },
    userName: {
      get() {
        return this.$store.state.user.name;
      },
      set(val) {
        this.$store.commit("user/updateName", val);
      }
    }
  }, // 存放需要实时计算数据并呈现的函数
  watch: {
    home_key: function(newValue, oldValue) {
      console.log("home_key：" + newValue);
      this.key = newValue;
      this.loadUserList();
    }
  }, // 监控data中的数据变化
  methods: {
    loadUserList() {
      this.dataListLoading = true;
      console.log(this.key);
      this.$http({
        url: this.$http.adornUrl("/lost_and_found/pickthings/list"),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
          key: this.key
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.page.list;
          this.totalPage = data.page.totalCount;
          if (this.pageSize >= this.totalPage) {
            this.noMore = true;
          }
          console.log(data.page);
        } else {
          this.dataList = [];
          this.totalPage = 0;
          if (this.pageSize >= this.totalPage) {
            this.noMore = true;
          }
          this.$message({
            showClose: true,
            message: data.msg,
            duration: 1000,
            type: "error"
          });
          // this.$message.error(data.msg);
        }
        this.dataListLoading = false;
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.loadUserList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.loadUserList();
    },
    load() {
      this.dataListLoading = true;
      this.pageSize += 3;
      this.pageIndex = 1;
      this.loadUserList();
    },
    lookInfoClick(user) {
      this.userinfovisible = true;
      this.click_pickthings_data = user;
      console.log("父组件：click_pickthings_data:", this.click_pickthings_data);
      this.$nextTick(() => {
        this.$refs.lookUserInfo.init(0);
      });
    }
  }, // 方法集合
  created() {}, // 生命周期 - 创建完成（可以访问当前this实例）
  mounted() {
    this.loadUserList();
  }, // 生命周期 - 挂载完成（可以访问DOM元素）
  beforeCreate() {}, // 生命周期 - 创建之前
  beforeMount() {}, // 生命周期 - 挂载之前
  beforeUpdate() {}, // 生命周期 - 更新之前
  updated() {}, // 生命周期 - 更新之后
  beforeDestroy() {}, // 生命周期 - 销毁之前
  destroyed() {}, // 生命周期 - 销毁完成
  activated() {} // 如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped></style>
