<template>
  <el-dialog
    title="个人信息"
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="470px"
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      label-width="50px"
      label-position="right"
      @keyup.enter.native="dataFormSubmit()"
    >
      <!-- <el-form-item label="帐号" prop="studentNo">
        <el-input placeholder="输入学号" v-model="dataForm.studentNo" clearable>
        </el-input>
      </el-form-item> -->
      <el-form-item label="姓名" prop="studentName">
        <el-input
          placeholder="输入姓名"
          v-model="dataForm.studentName"
          clearable
        >
        </el-input>
      </el-form-item>
      <el-form-item label="年级" prop="className">
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
      <el-form-item label="专业" prop="specialtyName">
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
      <el-form-item label="QQ" prop="qq">
        <el-input placeholder="输入QQ" v-model="dataForm.qq" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input placeholder="输入电话" v-model="dataForm.phone" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input placeholder="输入邮箱" v-model="dataForm.email" clearable>
        </el-input>
      </el-form-item>
      <!-- <el-form-item label="密码" prop="newpassword">
        <el-input
          placeholder="输入修改密码"
          v-model="dataForm.newpassword"
          show-password
          clearable
        >
        </el-input>
      </el-form-item>
      <el-form-item label="密码" prop="repassword">
        <el-input
          placeholder="确认密码"
          v-model="dataForm.repassword"
          show-password
          clearable
        >
        </el-input>
      </el-form-item> -->
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { isQQ, isMobile } from "@/utils/validate";
export default {
  props: ["userInfo"],
  watch: {
    // userInfo: function (newValue, oldValue) {
    //   this.dataForm = newValue;
    // },
  }, // 监控data中的数据变化
  data() {
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
      dataForm: {
        studentNo: null,
        studentName: "",
        className: "",
        specialtyName: "",
        qq: "",
        phone: "",
        email: ""
        // password: "",
        // newpassword: "",
        // repassword: ""
      },
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            }
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            }
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            }
          }
        ]
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
  methods: {
    init() {
      this.visible = true;
      this.getInfo();
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl("/lost_and_found/student/update"),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id,
              studentNo: this.dataForm.studentNo,
              // password: this.dataForm.password,
              studentName: this.dataForm.studentName,
              className: this.dataForm.className,
              specialtyName: this.dataForm.specialtyName,
              qq: this.dataForm.qq,
              phone: this.dataForm.phone,
              email: this.dataForm.email
            })
          }).then(({ data }) => {
            console.log(data);
            if (data && data.code === 0) {
              this.init();
              this.visible = false;
              this.$message({
                message: "修改成功",
                type: "success",
                duration: 1000,
                showClose: true,
                onClose: () => {
                  this.visible = false;
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
            }
          });
        }
      });
    },
    getInfo() {
      this.dataForm = this.userInfo;
      console.log("personal-info:", this.dataForm);
    }
  }, // 方法集合
  created() {}, // 生命周期 - 创建完成（可以访问当前this实例）
  mounted() {
    // this.dataForm = this.userInfo;
    // console.log("personal-info:", this.dataForm);
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
