<template>
  <el-dialog
    :title="formIndex == 0 ? '发布招领' : '发布寻物'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="470px"
    center
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      label-width="80px"
      label-position="right"
      @keyup.enter.native="dataFormSubmit()"
    >
      <!-- <el-form-item label="帐号" prop="studentNo">
        <el-input placeholder="输入学号" v-model="dataForm.studentNo" clearable>
        </el-input>
      </el-form-item> -->
      <el-form-item label="物品名称" prop="thingsName">
        <el-input
          placeholder="输入名称"
          v-model="dataForm.thingsName"
          clearable
        >
        </el-input>
      </el-form-item>
      <el-form-item label="寻到地点" prop="pickPlace" v-if="formIndex == 0">
        <el-input placeholder="输入地点" v-model="dataForm.pickPlace" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="寻到时间" prop="pickTime" v-if="formIndex == 0">
        <el-date-picker
          v-model="dataForm.pickTime"
          align="right"
          type="date"
          placeholder="选择日期"
          value-format="yyyy-MM-dd hh:mm:ss"
          default-time="00:00:00"
          :picker-options="dateOptions"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="丢失地点" prop="lostPlace" v-if="formIndex == 1">
        <el-input placeholder="输入地点" v-model="dataForm.lostPlace" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="丢失时间" prop="lostTime" v-if="formIndex == 1">
        <el-date-picker
          v-model="dataForm.lostTime"
          align="right"
          type="date"
          placeholder="选择日期"
          value-format="yyyy-MM-dd hh:mm:ss"
          default-time="00:00:00"
          :picker-options="dateOptions"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="物品类型" prop="thingsType">
        <el-input
          placeholder="输入物品类型"
          v-model="dataForm.thingsType"
          clearable
        >
        </el-input>
      </el-form-item>
      <el-form-item label="物品图片" prop="thingsImg">
        <!-- <el-input v-model="dataForm.thingsImg" placeholder="物品图片"></el-input> -->
        <single-upload v-model="dataForm.thingsImg"></single-upload>
      </el-form-item>
      <el-form-item label="物品描述" prop="ThingsDes">
        <el-input
          placeholder="输入物品描述"
          v-model="dataForm.ThingsDes"
          clearable
        >
        </el-input>
      </el-form-item>
      <el-form-item label="暂存地点" prop="storagePlace" v-if="formIndex == 0">
        <el-input
          placeholder="输入暂存地点"
          v-model="dataForm.storagePlace"
          clearable
        >
        </el-input>
      </el-form-item>
      <el-form-item label="联系QQ" prop="qq">
        <el-input placeholder="输入QQ" v-model="dataForm.qq" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input placeholder="输入电话" v-model="dataForm.phone" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="联系邮箱" prop="email">
        <el-input placeholder="输入邮箱" v-model="dataForm.email" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="联系人" prop="studentName">
        <el-input
          placeholder="输入姓名"
          v-model="dataForm.studentName"
          clearable
        >
        </el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import SingleUpload from "@/components/upload/singleUpload";
export default {
  inject: ["reload"],
  props: ["publishUserInfo"],
  components: { SingleUpload },
  data() {
    return {
      visible: false,
      formIndex: 0,
      dataForm: {
        ThingsName: "",
        PickPlace: "",
        PickTime: "",
        lostPlace: "",
        lostTime: "",
        ThingsType: "",
        thingsImg: "",
        StoragePlace: "",
        ThingsDes: "",
        qq: "",
        phone: "",
        email: "",
        studentName: "",
        studentNo: ""
      },
      dataRule: {
        thingsName: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        pickPlace: [
          { required: true, message: "地点不能为空", trigger: "blur" }
        ],
        pickTime: [
          { required: true, message: "时间不能为空", trigger: "blur" }
        ],
        lostPlace: [
          { required: true, message: "地点不能为空", trigger: "blur" }
        ],
        lostTime: [
          { required: true, message: "时间不能为空", trigger: "blur" }
        ],
        thingsType: [
          { required: true, message: "类型不能为空", trigger: "blur" }
        ]
      },
      dateOptions: {
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
      }
    };
  },
  computed: {}, // 存放需要实时计算数据并呈现的函数
  watch: {}, // 监控data中的数据变化
  methods: {
    init(id) {
      this.visible = true;
      this.formIndex = id || 0;
      this.getInfo();
    },
    // 提交表单
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          if (this.formIndex == 0) {
            this.$http({
              url: this.$http.adornUrl("/lost_and_found/pickthings/save"),
              method: "post",
              data: this.$http.adornData({
                thingsName: this.dataForm.thingsName,
                pickPlace: this.dataForm.pickPlace,
                pickTime: this.dataForm.pickTime,
                thingsType: this.dataForm.thingsType,
                thingsImg: this.dataForm.thingsImg,
                storagePlace: this.dataForm.storagePlace,
                thingsDes: this.dataForm.thingsDes,
                qq: this.dataForm.qq,
                phone: this.dataForm.phone,
                email: this.dataForm.email,
                studentName: this.dataForm.studentName,
                studentNo: this.dataForm.studentNo
              })
            })
              .then(({ data }) => {
                if (data && data.code === 0) {
                  this.$message({
                    message: "招领启事发布成功",
                    type: "success",
                    duration: 1000,
                    showClose: true,
                    onClose: () => {
                      this.visible = false;
                      // this.$emit("refreshDataList");
                    }
                  });
                  console.log("招领启事发布成功：", data);
                  this.visible = false;
                  this.reload();
                } else {
                  this.$message({
                    showClose: true,
                    message: data.msg,
                    duration: 1000,
                    type: "error"
                  });
                  this.reload();
                }
              })
              .catch(err => {
                console.log("出错了...", err);
                reject(false);
              });
          } else {
            this.$http({
              url: this.$http.adornUrl("/lost_and_found/lostthings/save"),
              method: "post",
              data: this.$http.adornData({
                thingsName: this.dataForm.thingsName,
                lostPlace: this.dataForm.lostPlace,
                lostTime: this.dataForm.lostTime,
                thingsType: this.dataForm.thingsType,
                thingsImg: this.dataForm.thingsImg,
                thingsDes: this.dataForm.thingsDes,
                qq: this.dataForm.qq,
                phone: this.dataForm.phone,
                email: this.dataForm.email,
                studentName: this.dataForm.studentName,
                studentNo: this.dataForm.studentNo
              })
            })
              .then(({ data }) => {
                console.log(data);
                if (data && data.code === 0) {
                  this.$message({
                    message: "寻物启事发布成功",
                    type: "success",
                    duration: 1000,
                    onClose: () => {
                      this.visible = false;
                      // this.$emit("refreshDataList");
                    }
                  });
                  console.log("寻物启事发布成功：", data);
                  this.visible = false;
                  this.reload();
                } else {
                  this.$message({
                    showClose: true,
                    message: data.msg,
                    duration: 1000,
                    type: "error"
                  });
                  this.reload();
                }
              })
              .catch(err => {
                console.log("出错了...", err);
                reject(false);
              });
          }
        }
      });
    },
    getInfo() {
      this.dataForm = this.publishUserInfo;
      console.log("personal-info:", this.dataForm);
    }
  }
};
</script>
<style lang=""></style>
