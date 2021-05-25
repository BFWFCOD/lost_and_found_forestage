<template>
  <el-dialog
    title="修改密码"
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="370px"
    center
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      status-icon
    >
      <el-form-item prop="password">
        <el-input
          type="password"
          v-model="dataForm.password"
          placeholder="输入原密码"
        ></el-input>
      </el-form-item>
      <el-form-item prop="captcha">
        <el-row :gutter="20">
          <el-col :span="14">
            <el-input v-model="dataForm.captcha" placeholder="验证码">
            </el-input>
          </el-col>
          <el-col :span="10" class="login-captcha">
            <img :src="captchaPath" @click="getCaptcha()" alt="" />
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item prop="newPassword">
        <el-input
          type="password"
          v-model="dataForm.newPassword"
          placeholder="输入新密码"
        ></el-input>
      </el-form-item>
      <el-form-item prop="confirmPassword">
        <el-input
          type="password"
          v-model="dataForm.confirmPassword"
          placeholder="输入确认密码"
        ></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
// 这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
// 例如：import '组件名称' from '组件路径';
import { getUUID } from "@/utils";
import { clearLoginInfo } from "@/utils";
export default {
  inject: ["reload"],
  components: {}, // import引入或定义的局部的组件需要在此处注入到vue对象中才能使用
  props: {},
  data() {
    // 这里存放需要用到的数据
    var validateConfirmPassword = (rule, value, callback) => {
      if (this.dataForm.newPassword !== value) {
        callback(new Error("确认密码与新密码不一致"));
      } else {
        callback();
      }
    };
    return {
      visible: false,
      dataForm: {
        id: null,
        password: "",
        newPassword: "",
        confirmPassword: ""
      },
      dataRule: {
        password: [
          { required: true, message: "原密码不能为空", trigger: "blur" }
        ],
        newPassword: [
          { required: true, message: "新密码不能为空", trigger: "blur" }
        ],
        confirmPassword: [
          { required: true, message: "确认密码不能为空", trigger: "blur" },
          { validator: validateConfirmPassword, trigger: "blur" }
        ],
        captcha: [
          { required: true, message: "验证码不能为空", trigger: "blur" }
        ]
      },
      captchaPath: ""
    }; // 将页面需要用到的数据返回
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
  methods: {
    init() {
      this.visible = true;
      this.dataForm.id = this.userId;
      console.log(this.dataForm.id);
    },
    // 提交表单
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl("/sys/password"),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id,
              password: this.dataForm.password,
              uuid: this.dataForm.uuid,
              captcha: this.dataForm.captcha,
              newPassword: this.dataForm.newPassword
            })
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "修改成功",
                type: "success",
                showClose: true,
                duration: 1000,
                onClose: () => {
                  this.$nextTick(() => {
                    // this.$router.replace({ name: "login" });
                  });
                }
              });
              this.visible = false;
              clearLoginInfo();
              this.reload();
              // console.log("修改:", data);
            } else {
              this.getCaptcha();
              this.$message({
                message: data.msg,
                type: "error",
                showClose: true,
                duration: 1000
              });
              // this.$message.error(data.msg);
              // console.log("修改:", data);
            }
          });
        }
      });
    },
    // 获取验证码
    getCaptcha() {
      this.dataForm.uuid = getUUID();
      this.captchaPath = this.$http.adornUrl(
        `/captcha.jpg?uuid=${this.dataForm.uuid}`
      );
    }
  }, // 方法集合
  created() {
    this.getCaptcha();
  }, // 生命周期 - 创建完成（可以访问当前this实例）
  mounted() {}, // 生命周期 - 挂载完成（可以访问DOM元素）
  beforeCreate() {}, // 生命周期 - 创建之前
  beforeMount() {}, // 生命周期 - 挂载之前
  beforeUpdate() {}, // 生命周期 - 更新之前
  updated() {}, // 生命周期 - 更新之后
  beforeDestroy() {}, // 生命周期 - 销毁之前
  destroyed() {}, // 生命周期 - 销毁完成
  activated() {} // 如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style lang="scss">
.login-captcha {
  overflow: hidden;
  > img {
    width: 100%;
    cursor: pointer;
  }
}
</style>
