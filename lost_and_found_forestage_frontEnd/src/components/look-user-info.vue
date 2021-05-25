<template>
  <el-dialog
    :title="formIndex == 0 ? '招领启事' : '寻物启事'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="370px"
    center
  >
    <el-form
      :model="dataForm"
      ref="dataForm"
      @keyup.enter.native="confirmHandle()"
    >
      <el-form-item label="" prop="studentName">
        <el-input
          placeholder="姓名"
          v-model="dataForm.studentName"
          show-password
        >
          <template slot="prepend">姓名</template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="qq">
        <el-input placeholder="QQ" v-model="dataForm.qq" show-password>
          <template slot="prepend">Q Q</template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="phone">
        <el-input placeholder="电话" v-model="dataForm.phone" show-password>
          <template slot="prepend">电话</template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="email">
        <el-input placeholder="邮箱" v-model="dataForm.email" show-password>
          <template slot="prepend">邮箱</template>
        </el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button
        @click="
          visible = false;
          dataForm = {};
        "
        >取消</el-button
      >
      <el-button
        type="primary"
        @click="confirmHandle()"
        :disabled="confirmDisabled"
        >{{ formIndex == 0 ? "认领" : "寻到" }}</el-button
      >
    </span>
  </el-dialog>
</template>

<script>
// import SingleUpload from "@/components/upload/singleUpload";
export default {
  // components: { SingleUpload },
  inject: ["reload"],
  props: ["clickedUserData", "click_pickthings_data", "click_lostthings_data"],
  data() {
    return {
      visible: false,
      formIndex: 0,
      confirmDisabled: true,
      dataForm: {}
    };
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
        console.log("studentNo:"+this.$store.state.user.studentNo);
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
  watch: {
    clickedUserData: function(newValue, oldValue) {
      this.dataForm = newValue;
      // console.log("子组件：dataForm:", this.dataForm);
    },
    click_pickthings_data: function(newValue, oldValue) {
      this.dataForm = newValue;
      // console.log("子组件：click_pickthings_data:", this.dataForm);
    },
    click_lostthings_data: function(newValue, oldValue) {
      this.dataForm = newValue;
      // console.log("子组件：click_lostthings_data:", this.dataForm);
    }
  }, // 监控data中的数据变化
  methods: {
    init(id) {
      this.visible = true;
      this.formIndex = id || 0;
      this.dataForm =
        this.clickedUserData ||
        this.click_pickthings_data ||
        this.click_lostthings_data;
      this.confirmDisabled=(this.dataForm.status==0 || this.dataForm.studentNo==this.studentNo);
      // console.log("this.dataForm.status==0",this.dataForm.status==0);
      // console.log("this.dataForm.studentNo",this.dataForm.studentNo==this.studentNo);
      console.log(
        "子组件：init:",
        this.clickedUserData ||
          this.click_pickthings_data ||
          this.click_lostthings_data
      );
    },
    confirmHandle() {
      this.$confirm(`确定${this.formIndex == 0 ? "认领" : "寻到"}？`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      })
        .then(() => {
          console.log(this.userId, this.userName, this.dataForm);
          this.$confirm(`Are you really, really, really sure ?`, "提示", {
            confirmButtonText: "yep",
            cancelButtonText: "no",
            type: "warning"
          })
            .then(() => {
              this.$http({
                url: this.$http.adornUrl(
                  `/lost_and_found/${
                    this.formIndex == 0
                      ? "pickthingsrelation"
                      : "lostthingsrelation"
                  }/save`
                ),
                method: "post",
                data: this.$http.adornData(
                  this.formIndex == 0
                    ? {
                        pickthingsId: this.dataForm.id,
                        pickthingsName: this.dataForm.thingsName,
                        studentId: this.userId,
                        studentName: this.userName
                      }
                    : {
                        lostthingsId: this.dataForm.id,
                        lostthingsName: this.dataForm.thingsName,
                        studentId: this.userId,
                        studentName: this.userName
                      }
                )
              }).then(({ data }) => {
                if (data && data.code === 0) {
                  this.$message({
                    type: "success",
                    showClose: true,
                    duration: 1000,
                    message: "状态更新成功"
                  });
                  this.visible = false;
                  // this.$router.push({ name: "domain" });
                  this.reload();
                  // this.$http({
                  //   url: this.$http.adornUrl(
                  //     `/lost_and_found/${
                  //       this.formIndex == 0 ? "pickthings" : "lostthings"
                  //     }/update/status`
                  //   ),
                  //   method: "post",
                  //   data: this.$http.adornData({
                  //     id: this.dataForm.id,
                  //     status: 0
                  //   })
                  // }).then(({ data }) => {
                  //   if (data && data.code === 0) {
                  //     this.$message({
                  //       type: "success",
                  //       showClose: true,
                  //       duration: 1000,
                  //       message: "状态更新成功"
                  //     });
                  //     this.visible = false;
                  //     this.$router.push({ name: "domain" });
                  //   } else {
                  //     this.$message({
                  //       showClose: true,
                  //       message: data.msg,
                  //       duration: 1000,
                  //       type: "error"
                  //     });
                  //   }
                  // });
                } else if (data && data.code === 707) {
                  this.$message({
                    showClose: true,
                    message: data.msg,
                    duration: 1000,
                    type: "error"
                  });
                } else {
                  this.$message({
                    showClose: true,
                    message: data.msg,
                    duration: 1000,
                    type: "error"
                  });
                }
                this.reload();
              });
            })
            .catch(() => {});
        })
        .catch(() => {});
    }
  }
};
</script>
