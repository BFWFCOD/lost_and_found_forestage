<template>
  <el-dialog
    :title="showType == 0 ? '认领人信息' : '寻到人信息'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="370px"
    append-to-body
    center
  >
    <el-form :model="dataForm" ref="dataForm">
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
        @click="
          visible = false;
          dataForm = {};
        "
        >确认</el-button
      >
    </span>
  </el-dialog>
</template>

<script>
export default {
  // components: { SingleUpload },
  inject: ["reload"],
  data() {
    return {
      visible: false,
      thingId: 0,
      showType: 0,
      dataForm: {
        studentNo: null,
        studentName: "",
        className: "",
        specialtyName: "",
        qq: "",
        phone: "",
        email: ""
      }
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
    init(thingId, showType) {
      this.visible = true;
      this.thingId = thingId || 0;
      this.showType = showType || 0;
      this.getDataList();
    },
    // 获取数据
    getDataList() {
      this.$http({
        url: this.$http.adornUrl(
          `/lost_and_found/${
            this.showType == 0 ? "pickthingsrelation" : "lostthingsrelation"
          }/userInfo/${this.thingId}`
        ),
        method: "get",
        params: this.$http.adornParams()
      })
        .then(({ data }) => {
          if (data && data.code === 0) {
            this.dataForm = data.student;
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
        })
        .catch(err => {
          console.log("出错了...", err);
        });
    }
  }
};
</script>
