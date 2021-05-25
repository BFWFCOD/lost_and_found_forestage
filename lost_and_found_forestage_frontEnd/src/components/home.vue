<template>
  <div id="box">
    <el-row :gutter="10" type="flex" justify="center" align="middle">
      <el-col :span="4" style="min-width: 148px">
        <div class="son" style="background-color: #409eff">
          <p class="title-font">校园失物招领</p>
        </div>
      </el-col>
      <el-col :span="7" style="min-width: 460px">
        <div class="bg-purple-dark">
          <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color=""
            text-color=""
            active-text-color="#ffd04b"
            style="width: 100%"
            :router="true"
          >
            <el-menu-item index="domain">首页</el-menu-item>
            <el-menu-item index="foundlist">招领启事</el-menu-item>
            <el-menu-item index="lostlist">寻物启事</el-menu-item>
            <el-menu-item @click="open">发布</el-menu-item>
            <el-submenu index="">
              <template slot="title">个人信息</template>
              <el-menu-item @click="personalInfoClick">个人资料</el-menu-item>
              <el-menu-item @click="personalPostInfoClick"
                >帖子信息</el-menu-item
              >
              <el-menu-item @click="updatePasswordClick">修改密码</el-menu-item>
              <el-menu-item @click.native="logoutHandle()">退出</el-menu-item>
            </el-submenu>
          </el-menu>
        </div>
      </el-col>
      <el-col :span="2.5" style="min-width: 100px">
        <div class="demo-input-suffix">
          <el-input
            placeholder="搜索"
            prefix-icon="el-icon-search"
            v-model="key"
            @keyup.enter.native="searchClick()"
            clearable
          >
          </el-input>
        </div>
      </el-col>
      <el-col :span="2.5" style="min-width: 200px" v-if="userId == 0">
        <div class="grid-content father">
          <div class="son">
            <el-row>
              <el-button type="primary" @click="loginClick(0)">登录</el-button>
              <el-button type="primary" @click="loginClick(1)">注册</el-button>
            </el-row>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="10" type="flex" justify="center">
      <el-col :span="14">
        <el-carousel :interval="4000" type="card" height="200px">
          <el-carousel-item v-for="(img, index) in imgs" :key="index">
            <el-image :src="img"></el-image>
          </el-carousel-item>
        </el-carousel>
      </el-col>
    </el-row>

    <el-row :gutter="10" type="flex" justify="center">
      <el-col :span="16">
        <el-container style="height: 63vh; border: 1px solid #eee">
          <el-main>
            <router-view :home_key="this.key"></router-view>
            <!--  -->
          </el-main>
        </el-container>
      </el-col>
    </el-row>

    <publish
      v-if="publishVisible"
      ref="Publish"
      :publishUserInfo="userInfo"
    ></publish>
    <personal-info
      v-if="personalInfoVisible"
      ref="PersonalInfo"
      :userInfo="userInfo"
    ></personal-info>
    <personal-post-info
      v-if="personalPostInfoVisible"
      ref="PersonalPostInfo"
    ></personal-post-info>
    <login v-if="loginDialogVisible" ref="Login"></login>
    <update-password
      v-if="updatePasswordVisible"
      ref="UpdatePassword"
    ></update-password>
  </div>
</template>

<script>
import Publish from "@/components/publish.vue";
import PersonalInfo from "@/components/personal-info.vue";
import PersonalPostInfo from "@/components/personal-post-info.vue";
import Login from "@/components/login.vue";
import UpdatePassword from "@/components/update-password.vue";
import { clearLoginInfo } from "@/utils";
export default {
  inject: ["reload"],
  components: {
    Publish,
    PersonalInfo,
    PersonalPostInfo,
    Login,
    UpdatePassword
  },
  computed: {
    userId: {
      get() {
        return this.$store.state.user.id;
      },
      set(val) {
        this.$store.commit("user/updateId", val);
      }
    },
    studentNo: {
      get() {
        return this.$store.state.user.studentNo;
      },
      set(val) {
        this.$store.commit("user/updateStudentNo", val);
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
  watch: {}, // 监控data中的数据变化
  data() {
    return {
      activeIndex: "domain",
      key: "",
      publishVisible: false,
      personalInfoVisible: false,
      personalPostInfoVisible: false,
      loginDialogVisible: false,
      // loginButtonVisible: true,
      updatePasswordVisible: false,
      userInfo: {},
      imgs: [
        "https://gulimall-bfcod.oss-cn-shanghai.aliyuncs.com/l%26f/1.jpg",
        "https://gulimall-bfcod.oss-cn-shanghai.aliyuncs.com/l%26f/2.jpg",
        "https://gulimall-bfcod.oss-cn-shanghai.aliyuncs.com/l%26f/3.jpg",
        "https://gulimall-bfcod.oss-cn-shanghai.aliyuncs.com/l%26f/4.jpg",
        "https://gulimall-bfcod.oss-cn-shanghai.aliyuncs.com/l%26f/5.jpg",
        "https://gulimall-bfcod.oss-cn-shanghai.aliyuncs.com/l%26f/6.jpg"
        // require("@/assets/img/1.jpg"),
        // require("@/assets/img/2.jpg"),
        // require("@/assets/img/3.jpg"),
        // require("@/assets/img/4.jpg"),
        // require("@/assets/img/5.jpg"),
        // require("@/assets/img/6.jpg"),
      ]
    };
  },
  methods: {
    // 菜单点击事件
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      console.log("handleSelect:" + this.key);
    },
    // 点击发布
    open() {
      this.$confirm("选择发布类型", "", {
        distinguishCancelAndClose: true,
        confirmButtonText: "寻物启事",
        cancelButtonText: "招领启事",
        center: true
      })
        .then(() => {
          this.publishVisible = true;
          this.$nextTick(() => {
            this.$refs.Publish.init(1);
          });
          // this.$notify.success({
          //   message: this.publishVisible,
          //   duration: 1000,
          //   showClose: true
          // });
        })
        .catch(action => {
          if (action === "cancel") {
            this.publishVisible = true;
            this.$nextTick(() => {
              this.$refs.Publish.init(0);
            });
          }
          // this.$notify.success({
          //   message: action === "cancel" ? this.publishVisible : "关闭",
          //   duration: 1000,
          //   showClose: true
          // });
        });
    },
    // 点击用户信息
    personalInfoClick() {
      this.personalInfoVisible = true;
      this.$nextTick(() => {
        this.$refs.PersonalInfo.init();
      });
    },
    // 点击用户帖子信息
    personalPostInfoClick() {
      this.personalPostInfoVisible = true;
      this.$nextTick(() => {
        this.$refs.PersonalPostInfo.init();
      });
    },
    // 点击登录或注册
    loginClick(index) {
      this.loginDialogVisible = true;
      this.$nextTick(() => {
        this.$refs.Login.init(index);
      });
    },
    // 点击修改密码
    updatePasswordClick() {
      this.updatePasswordVisible = true;
      this.$nextTick(() => {
        this.$refs.UpdatePassword.init();
      });
    },
    // 搜索框事件
    searchClick() {
      // this.$router.push({ name: "domain", params: { key: this.key } });
      console.log("搜索框事件:" + this.key);
    },
    // 退出登录事件
    logoutHandle() {
      this.$confirm(`确定进行[退出]操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/sys/logout"),
            method: "post",
            data: this.$http.adornData()
          }).then(({ data }) => {
            if (data && data.code === 0) {
              clearLoginInfo();
              // this.$nextTick(() => {
              //   this.$router.push({ name: "domain" });
              // });
              // this.reload();
              this.$notify.success({
                message: "退出成功",
                duration: 1000,
                showClose: true
              });
            }
          });
        })
        .catch(() => {});
    },
    // 获取当前管理员信息
    getUserInfo() {
      this.loading = true;
      this.$http({
        url: this.$http.adornUrl("/lost_and_found/student/info"),
        method: "get",
        params: this.$http.adornParams()
      })
        .then(({ data }) => {
          // console.log("获取当前用户信息:data=", data);
          if (data && data.code === 0) {
            this.userInfo = data.user;
            console.log("home.userInfo:", this.userInfo);
            this.userId = data.user.id;
            this.studentNo = data.user.studentNo;
            this.userName = data.user.studentName;
            console.log("userId:", this.userId);
            // console.log(
            //   "获取当前用户信息,userId+userName:",
            //   this.userId,
            //   this.userName
            // );
          }
          this.loading = false;
        })
        .catch(err => {
          this.loading = false;
        });
    }
  }, // 方法集合
  created() {}, // 生命周期 - 创建完成（可以访问当前this实例）
  mounted() {
    this.getUserInfo();
    // this.loading = true;
    // let token = this.$cookies.get("forestageToken")
    //   ? this.$cookies.get("forestageToken")
    //   : null;
    // this.loginButtonVisible = !token || !/\S/.test(token) ? true : false;
    // this.$nextTick(() => {
    //   this.$router.push({ name: "domain" });
    // });
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

<style>
::-webkit-scrollbar {
  /*滚动条整体样式*/
  width: 5px;
  /*高宽分别对应横竖滚动条的尺寸*/
  height: 1px;
}
::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  border-radius: 10px;
  background-color: rgb(197, 197, 197);
  background-image: -webkit-linear-gradient(
    45deg,
    rgba(255, 255, 255, 0.2) 25%,
    transparent 25%,
    transparent 50%,
    rgba(255, 255, 255, 0.2) 50%,
    rgba(255, 255, 255, 0.2) 75%,
    transparent 75%,
    transparent
  );
}
::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  /*border-radius: 10px;*/
  background: #ededed;
}
.el-row {
  margin-bottom: 10px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 72px;
  /* border: 1px solid rgb(245, 22, 22); */
}
.father {
  border-radius: 4px;
  line-height: 72px;
  text-align: center;
  width: 100%;
  height: 72px;
}
.son {
  border-radius: 4px;
  display: inline-block;
  width: 100%;
  height: 72px;
}
.title-font {
  font-size: 20px;
  text-align: center;
  color: #fff;
}
.title-font2 {
  font-size: 10px;
  color: #fff;
}
</style>
