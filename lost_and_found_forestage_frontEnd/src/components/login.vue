<template>
  <el-dialog
    :title="!formIndex ? '登录' : '注册'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="370px"
    center
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      v-if="formIndex == 0"
      @keyup.enter.native="dataFormSubmit()"
    >
      <el-form-item label="" prop="studentNo">
        <el-input placeholder="输入学号" v-model="dataForm.studentNo" clearable>
          <template slot="prepend">帐号</template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="password">
        <el-input
          placeholder="输入密码"
          v-model="dataForm.password"
          show-password
          clearable
        >
          <template slot="prepend">密码</template>
        </el-input>
      </el-form-item>
    </el-form>

    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      v-if="formIndex == 1"
      @keyup.enter.native="dataFormSubmit()"
    >
      <el-form-item label="" prop="studentNo">
        <el-input placeholder="输入学号" v-model="dataForm.studentNo" clearable>
          <template slot="prepend">帐号</template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="studentName">
        <el-input
          placeholder="输入姓名"
          v-model="dataForm.studentName"
          clearable
        >
          <template slot="prepend">姓名</template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="className">
        <el-select
          v-model="dataForm.className"
          clearable
          placeholder="请选择年级"
        >
          <el-option
            v-for="item in classOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="" prop="specialtyName">
        <el-select
          v-model="dataForm.specialtyName"
          clearable
          placeholder="请选择专业"
        >
          <el-option
            v-for="item in specialtyOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="" prop="qq">
        <el-input placeholder="输入QQ" v-model="dataForm.qq" clearable>
          <template slot="prepend">QQ</template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="phone">
        <el-input placeholder="输入电话" v-model="dataForm.phone" clearable>
          <template slot="prepend">电话</template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="password">
        <el-input
          placeholder="输入密码"
          v-model="dataForm.password"
          show-password
          clearable
        >
          <template slot="prepend">密码</template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="repassword">
        <el-input
          placeholder="确认密码"
          v-model="dataForm.repassword"
          show-password
          clearable
        >
          <template slot="prepend">密码</template>
        </el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button
        type="text"
        style="width: 10vw"
        @click="init(1)"
        v-if="formIndex == 0"
        >没有账号？立即注册
      </el-button>
      <el-button
        type="text"
        style="width: 10vw"
        @click="init(0)"
        v-if="formIndex == 1"
        >已有账号？立即登录
      </el-button>
      <el-button type="primary" @click="dataFormSubmit()"> 确 定 </el-button>
    </span>
  </el-dialog>
</template>

<script>
import { isQQ, isMobile } from "@/utils/validate";
import { addLoginInfo } from "@/utils";
export default {
  inject: ["reload"],
  data() {
    var validateConfirmPassword = (rule, value, callback) => {
      if (value !== this.dataForm.password) {
        callback(new Error("两次密码不一致"));
      } else {
        callback();
      }
    };
    var validateQQ = (rule, value, callback) => {
      if (!isQQ(value)) {
        callback(new Error("QQ号码格式错误"));
      } else {
        callback();
      }
    };
    var validateMobile = (rule, value, callback) => {
      if (!isMobile(value)) {
        callback(new Error("手机号格式错误"));
      } else {
        callback();
      }
    };
    return {
      visible: false,
      formIndex: 0,
      dataForm: {
        studentNo: "",
        studentName: "",
        className: "",
        specialtyName: "",
        qq: "",
        phone: "",
        password: "",
        repassword: ""
      },
      dataRule: {
        studentNo: [
          { required: true, message: "学号不能为空", trigger: "blur" }
        ],
        studentName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        className: [
          { required: true, message: "年级不能为空", trigger: "blur" }
        ],
        specialtyName: [
          { required: true, message: "专业不能为空", trigger: "blur" }
        ],
        qq: [
          { required: true, message: "QQ不能为空", trigger: "blur" },
          { validator: validateQQ, trigger: "blur" }
        ],
        phone: [
          { required: true, message: "手机号不能为空", trigger: "blur" },
          { min: 7, max: 11, message: "长度为 7 到 11", trigger: "blur" },
          { validator: validateMobile, trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
        repassword: [
          { required: true, message: "确认密码不能为空", trigger: "blur" },
          { validator: validateConfirmPassword, trigger: "blur" }
        ]
      },
      classOptions: [
        {
          value: "2016级",
          label: "2016级"
        },
        {
          value: "2017级",
          label: "2017级"
        },
        {
          value: "2018级",
          label: "2018级"
        },
        {
          value: "2019级",
          label: "2019级"
        },
        {
          value: "2020级",
          label: "2020级"
        },
        {
          value: "2021级",
          label: "2021级"
        }
      ],
      specialtyOptions: [
        {
          value: "机械设计制造及自动化",
          label: "机械设计制造及自动化"
        },
        {
          value: "网络工程",
          label: "网络工程"
        },
        {
          value: "软件工程",
          label: "软件工程"
        },
        {
          value: "土木工程",
          label: "土木工程"
        },
        {
          value: "自动化",
          label: "自动化"
        },
        {
          value: "电子信息工程",
          label: "电子信息工程"
        },
        {
          value: "电子科学与技术",
          label: "电子科学与技术"
        }
      ]
    };
  },
  computed: {}, // 存放需要实时计算数据并呈现的函数
  watch: {}, // 监控data中的数据变化
  methods: {
    init(id) {
      this.visible = true;
      this.formIndex = id || 0;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.formIndex == 1) {
          console.log(this.formIndex);
        }
      });
    },
    // 提交表单
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          if (this.formIndex == 0) {
            // console.log(this.dataForm);
            this.$http({
              url: this.$http.adornUrl("/sys/login"),
              method: "post",
              data: this.$http.adornData({
                studentNo: this.dataForm.studentNo,
                password: this.dataForm.password
              })
            })
              .then(({ data }) => {
                if (data && data.code === 0) {
                  console.log("登陆成功：", data);
                  addLoginInfo(data.token);
                  this.userId = data.user.id;
                  this.userName = data.user.studentName;
                  console.log(
                    "获取当前管理员信息,userId+userName:",
                    this.userId,
                    this.userName
                  );
                  // this.$cookies.set("loginButtonVisible", false);
                  // this.$router.push({ name: "refresh" });
                  // this.$nextTick(() => {
                  //   this.$router.push({ name: "domain" });
                  // });
                  // this.visible = false;
                  this.reload();
                  this.visible = false;
                  this.$message({
                    message: "登录成功",
                    type: "success",
                    duration: 1000,
                    showClose: true,
                    onClose: () => {
                      // this.$emit("refreshDataList");
                    }
                  });
                } else {
                  this.$message({
                    showClose: true,
                    message: data.msg,
                    duration: 1000,
                    type: "error"
                  });
                  // this.$message.error(data.msg);
                  // this.$router.replace({ name: "domain" });
                  // this.reload();
                }
              })
              .catch(err => {
                console.log("login出错了...", err);
                reject(false);
              });
          } else {
            this.$http({
              url: this.$http.adornUrl("/sys/register"),
              method: "post",
              data: this.$http.adornData({
                studentNo: this.dataForm.studentNo,
                password: this.dataForm.password,
                studentName: this.dataForm.studentName,
                className: this.dataForm.className,
                specialtyName: this.dataForm.specialtyName,
                qq: this.dataForm.qq,
                phone: this.dataForm.phone
              })
            }).then(({ data }) => {
              console.log(data);
              if (data && data.code === 0) {
                this.init(0);
                this.$notify.success({
                  message: "注册成功",
                  duration: 1000,
                  showClose: true
                });
              } else {
                this.$message({
                  showClose: true,
                  message: data.msg,
                  duration: 1000,
                  type: "error"
                });
                // this.$message.error(data.msg);
              }
            });
          }
        }
      });
    }
  }
};
</script>
<style lang=""></style>
