<template>
  <el-dialog
    title="帖子信息"
    :close-on-click-modal="false"
    :visible.sync="visible"
  >
    <el-menu
      default-active="1"
      mode="horizontal"
      background-color=""
      text-color=""
      active-text-color="#ffd04b"
    >
      <el-menu-item index="1" @click="showType = 0">招领列表</el-menu-item>
      <el-menu-item index="2" @click="showType = 1">寻物列表</el-menu-item>
    </el-menu>
    <div>
      <el-table
        :data="things"
        v-loading="dataListLoading"
        height="500"
        style="width: 100%"
        v-el-table-infinite-scroll="loadMethod"
        :border="true"
        :stripe="true"
      >
        <el-table-column
          prop="thingsType"
          header-align="center"
          align="center"
          label="物品类型"
          fixed
        >
        </el-table-column>
        <el-table-column
          prop="thingsName"
          header-align="center"
          align="center"
          label="物品名称"
        >
        </el-table-column>
        <el-table-column
          :prop="showType == 0 ? 'pickPlace' : 'lostPlace'"
          header-align="center"
          align="center"
          :label="showType == 0 ? '寻到地点' : '丢失地点'"
        >
        </el-table-column>
        <el-table-column
          :prop="showType == 0 ? 'pickTime' : 'lostTime'"
          header-align="center"
          align="center"
          :label="showType == 0 ? '寻到时间' : '丢失时间'"
        >
          <template slot-scope="scope">
            <span>{{
              showType == 0 ? scope.row.pickTime : scope.row.lostTime
            }}</span>
          </template>
        </el-table-column>

        <el-table-column
          prop="status"
          header-align="center"
          align="center"
          label="状态"
        >
          <template slot-scope="scope">
            <!-- <span>{{ scope.row.status == 1 ? `未认领` : `已认领` }}</span> -->
            <el-tag
              :effect="scope.row.status === 0 ? 'dark' : 'plain'"
              :type="scope.row.status === 0 ? 'success' : 'info'"
              disable-transitions
              >{{
                showType == 0
                  ? scope.row.status == 1
                    ? `未认领`
                    : `已认领`
                  : scope.row.status == 1
                  ? `未寻到`
                  : `已寻到`
              }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          header-align="center"
          align="center"
          width="150"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button
              type="text"
              size="mini"
              @click="checkOrUpdateHandle(showType, scope.row.id)"
              >查看</el-button
            >
            <el-button
              type="text"
              size="mini"
              @click="deleteHandle(scope.row.id)"
              >删除</el-button
            >
          </template>
        </el-table-column>
        <!-- 弹窗, 新增 / 修改 -->
        <check-or-update
          v-if="checkOrUpdateVisible"
          ref="checkOrUpdate"
          @refreshDataList="getDataList"
        ></check-or-update>
      </el-table>

      <el-alert
        v-if="dataListLoading"
        title="正在努力加载中..."
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
    </div>
  </el-dialog>
</template>

<script>
import checkOrUpdate from "./check-or-update";
// import elTableInfiniteScroll from "el-table-infinite-scroll"; // 在el-table上使用无限滚动加载: https://blog.csdn.net/weixin_41635393/article/details/105976512
export default {
  directives: {
    // "el-table-infinite-scroll": elTableInfiniteScroll,
    "el-table-infinite-scroll": {
      bind(el, binding) {
        const selectWrap = el.querySelector(".el-table__body-wrapper");
        selectWrap.addEventListener("scroll", function() {
          let sign = 1;
          const scrollDistance =
            this.scrollHeight - this.scrollTop - this.clientHeight;
          if (this.scrollHeight == this.clientHeight) return; //没有滚动条
          if (scrollDistance <= sign) {
            binding.value();
          }
        });
      }
    }
  },
  components: {
    checkOrUpdate
  },
  data() {
    return {
      visible: false,
      showType: 0,
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      noMore: false,
      dataListLoading: false,
      checkOrUpdateVisible: false,
      things: []
    };
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
    showType: function(newValue, oldValue) {
      console.log("showType：" + newValue);
      this.noMore = false;
      this.pageSize = 10;
      this.$nextTick(() => {
        this.getDataList();
      });
    }
  }, // 监控data中的数据变化
  methods: {
    init() {
      this.noMore = false;
      this.visible = true;
      this.$nextTick(() => {
        this.getDataList();
      });
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl(
          `/lost_and_found/${
            this.showType == 0 ? "pickthings" : "lostthings"
          }/personal`
        ),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize
        })
      })
        .then(({ data }) => {
          if (data && data.code === 0) {
            console.log(data);
            this.showType == 0
              ? (this.things = data.page.list)
              : (this.things = data.page.list);
            this.totalPage = data.page.totalCount;
            if (this.pageSize >= this.totalPage) {
              this.noMore = true;
            }
          } else {
            this.things = [];
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
          }
          this.dataListLoading = false;
        })
        .catch(err => {
          console.log("出错了...", err);
        });
    },
    loadMethod() {
      // this.noMore = false;
      // this.dataListLoading = true;
      // this.pageSize += 3;
      // this.pageIndex = 1;
      // this.$nextTick(() => {
      //   this.getDataList();
      // });
      if (!this.noMore && !this.dataListLoading) {
        this.pageSize += 3;
        this.pageIndex = 1;
        this.$nextTick(() => {
          this.getDataList();
        });
        // setTimeout(() => {
        //   this.getDataList();
        // }, 100);
      }
    },
    // 查看 / 修改
    checkOrUpdateHandle(showType, id) {
      this.checkOrUpdateVisible = true;
      this.$nextTick(() => {
        // console.log(id)
        this.$refs.checkOrUpdate.init(showType, id);
      });
    },
    // 删除
    deleteHandle(id) {
      var ids = id
        ? [id]
        : this.dataListSelections.map(item => {
            return item.id;
          });
      this.$confirm(
        `确定对[id=${ids.join(",")}]进行[${id ? "删除" : "批量删除"}]操作?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          this.$http({
            url: this.$http.adornUrl(
              `/lost_and_found/${
                this.showType == 0 ? "pickthings" : "lostthings"
              }/delete`
            ),
            method: "post",
            data: this.$http.adornData(ids, false)
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.getDataList();
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        })
        .catch(action => {
          if (action === "cancel") {
            console.log("取消了...", action);
          } else {
            console.log("出错了...", action);
          }
        });
    }
    // 根据不同状态，显示不同列的颜色   但是无效
    // :row-class-name="tableRowClassName"
    // tableRowClassName({ row, rowIndex }) {
    //   if (row.status == 0) {
    //     // console.log("status:0", row);
    //     return "success-row";
    //   } else if (row.status == 1) {
    //     // console.log("status:1", row);
    //     return "warning-row";
    //   } else {
    //     return "";
    //   }
    // }
  }
};
</script>
