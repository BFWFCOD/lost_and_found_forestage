<template>
  <el-dialog
    title="查看修改"
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="470px"
    append-to-body
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="80px"
    >
      <el-form-item label="物品名称" prop="thingsName">
        <el-input
          v-model="dataForm.thingsName"
          placeholder="物品名称"
        ></el-input>
      </el-form-item>
      <el-form-item
        :label="showType === 0 ? '寻到地点' : '丢失地点'"
        :prop="showType === 0 ? 'pickPlace' : 'lostPlace'"
      >
        <el-input
          v-model="dataForm.pickPlace"
          v-if="showType === 0"
          placeholder="寻到地点"
        ></el-input>
        <el-input
          v-model="dataForm.lostPlace"
          v-if="showType === 1"
          placeholder="丢失地点"
        ></el-input>
      </el-form-item>
      <el-form-item
        :label="showType === 0 ? '寻到时间' : '丢失时间'"
        :prop="showType === 0 ? 'pickTime' : 'lostTime'"
      >
        <el-date-picker
          v-model="dataForm.pickTime"
          v-if="showType === 0"
          align="right"
          type="date"
          placeholder="选择日期"
          value-format="yyyy-MM-dd hh:mm:ss"
          default-time="00:00:00"
          :picker-options="pickerOptions"
        >
        </el-date-picker>
        <el-date-picker
          v-model="dataForm.lostTime"
          v-if="showType === 1"
          align="right"
          type="date"
          placeholder="选择日期"
          value-format="yyyy-MM-dd hh:mm:ss"
          default-time="00:00:00"
          :picker-options="pickerOptions"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="物品类型" prop="thingsType">
        <el-input
          v-model="dataForm.thingsType"
          placeholder="物品类型"
        ></el-input>
      </el-form-item>
      <el-form-item label="物品图片(可空)" prop="thingsImg">
        <single-upload v-model="dataForm.thingsImg"></single-upload>
      </el-form-item>
      <el-form-item label="物品描述" prop="thingsDes">
        <el-input
          v-model="dataForm.thingsDes"
          placeholder="物品描述"
        ></el-input>
      </el-form-item>
      <el-form-item label="暂存地点" prop="storagePlace" v-if="showType == 0">
        <el-input
          v-model="dataForm.storagePlace"
          placeholder="暂存地点"
        ></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input v-model="dataForm.phone" placeholder="联系电话"></el-input>
      </el-form-item>
      <el-form-item label="qq" prop="qq">
        <el-input v-model="dataForm.qq" placeholder="qq"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item label="发布时间" prop="publishTime">
        <el-input
          v-model="dataForm.publishTime"
          placeholder="发布时间"
          :disabled="true"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-tag
          :effect="dataForm.status === 0 ? 'dark' : 'plain'"
          :type="dataForm.status === 0 ? 'success' : 'info'"
          disable-transitions
          >{{
            showType == 0
              ? dataForm.status == 0
                ? `已认领`
                : `未认领`
              : dataForm.status == 0
              ? `已寻到`
              : `未寻到`
          }}
        </el-tag>
        <el-button
          v-if="dataForm.status == 0"
          type="text"
          style="margin-left: 10px"
          @click="lookInfoClick(dataForm.id, showType)"
          >查看{{ showType === 0 ? "认领" : "寻到" }}人信息
        </el-button>
      </el-form-item>
      <el-form-item label="联系人" prop="studentName">
        <el-input
          v-model="dataForm.studentName"
          placeholder="联系人"
        ></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
    <!-- 弹窗, 新增 / 修改 -->
        <look-relation-userinfo
          v-if="lookRelationUserinfoVisible"
          ref="lookRelationUserinfo"
          @refreshDataList="getDataList"
        ></look-relation-userinfo>
  </el-dialog>
</template>

<script>
import SingleUpload from "@/components/upload/singleUpload";
import lookRelationUserinfo from "./look-relation-userinfo";
export default {
  components: { SingleUpload,lookRelationUserinfo },
  data() {
    return {
      visible: false,
      showType: 0,
      lookRelationUserinfoVisible:false,
      dataForm: {
        id: 0,
        thingsName: "",
        pickPlace: "",
        pickTime: "",
        lostPlace: "",
        lostTime: "",
        thingsType: "",
        thingsImg: "",
        thingsDes: "",
        storagePlace: "",
        phone: "",
        qq: "",
        email: "",
        publishTime: new Date().format("yyyy-MM-dd hh:mm:ss"),
        studentName: "",
        studentNo: ""
      },
      dataRule: {
        thingsName: [
          { required: true, message: "物品名称不能为空", trigger: "blur" }
        ],
        pickPlace: [
          { required: true, message: "寻到地点不能为空", trigger: "blur" }
        ],
        pickTime: [
          { required: true, message: "寻到时间不能为空", trigger: "blur" }
        ],
        lostPlace: [
          { required: true, message: "丢失地点不能为空", trigger: "blur" }
        ],
        lostTime: [
          { required: true, message: "丢失时间不能为空", trigger: "blur" }
        ],
        thingsType: [
          { required: true, message: "物品类型不能为空", trigger: "blur" }
        ],
        studentName: [
          { required: true, message: "联系人不能为空", trigger: "blur" }
        ]
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
      }
    };
  },
  methods: {
    init(showType, id) {
      this.visible = true;
      this.showType = showType;
      this.dataForm.id = id || 0;
      console.log(this.dataForm.id);
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.id != 0) {
          this.getDataList();
        }
      });
    },
    // 获取数据
    getDataList() {
      this.$http({
        url: this.$http.adornUrl(
          `/lost_and_found/${
            this.showType == 0 ? "pickthings" : "lostthings"
          }/info/${this.dataForm.id}`
        ),
        method: "get",
        params: this.$http.adornParams()
      })
        .then(({ data }) => {
          if (data && data.code === 0) {
            this.dataForm =
              this.showType == 0 ? data.pickThings : data.lostThings;
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
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/lost_and_found/${
                this.showType == 0 ? "pickthings" : "lostthings"
              }/update
              `
            ),
            method: "post",
            data: this.$http.adornData(
              this.showType == 0
                ? {
                    id: this.dataForm.id,
                    thingsName: this.dataForm.thingsName,
                    pickPlace: this.dataForm.pickPlace,
                    pickTime: this.dataForm.pickTime,
                    thingsType: this.dataForm.thingsType,
                    thingsImg: this.dataForm.thingsImg,
                    thingsDes: this.dataForm.thingsDes,
                    storagePlace: this.dataForm.storagePlace,
                    phone: this.dataForm.phone,
                    qq: this.dataForm.qq,
                    email: this.dataForm.email,
                    publishTime: this.dataForm.publishTime,
                    status: this.dataForm.status,
                    studentName: this.dataForm.studentName,
                    studentNo: this.dataForm.studentNo
                  }
                : {
                    id: this.dataForm.id,
                    thingsName: this.dataForm.thingsName,
                    lostPlace: this.dataForm.pickPlace,
                    lostTime: this.dataForm.pickTime,
                    thingsType: this.dataForm.thingsType,
                    thingsImg: this.dataForm.thingsImg,
                    thingsDes: this.dataForm.thingsDes,
                    phone: this.dataForm.phone,
                    qq: this.dataForm.qq,
                    email: this.dataForm.email,
                    publishTime: this.dataForm.publishTime,
                    status: this.dataForm.status,
                    studentName: this.dataForm.studentName,
                    studentNo: this.dataForm.studentNo
                  }
            )
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1000,
                onClose: () => {
                  this.visible = false;
                  this.$emit("refreshDataList");
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
    lookInfoClick(id, showType) {
      this.lookRelationUserinfoVisible = true;
      this.$nextTick(() => {
        this.$refs.lookRelationUserinfo.init(id,showType);
      });
    }
  }
};
</script>
