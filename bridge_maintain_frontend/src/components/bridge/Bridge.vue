<template>
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>桥梁管理</el-breadcrumb-item>
    <el-breadcrumb-item>桥梁列表</el-breadcrumb-item>
  </el-breadcrumb>

  <el-card>
    <el-row :gutter="25">
      <el-col :span="10">
        <el-input
          v-model="input"
          placeholder="请输入内容"
          suffix-icon="el-icon-search"
          clearable
          @change="getBridgeList"
          @clear="getBridgeList"
          @keydown.enter="getBridgeList"
        />
      </el-col>
      <el-col :span="4">
        <el-button type="primary" @click="dialogVisible = true"
          >添加桥梁</el-button
        >
      </el-col>
    </el-row>

    <div class="content-style">
      <!-- 桥梁资料卡 -->
      <el-table :data="bridges" stripe border fit>
        <el-table-column type="index"></el-table-column>
        <el-table-column
          label="桥梁编号"
          prop="bridgeId"
          align="center"
          style="font-size: larger"
        ></el-table-column>
        <el-table-column
          label="桥梁名称"
          prop="bridgeName"
          align="center"
        ></el-table-column>
        <el-table-column
          label="桥梁类型"
          prop="bridgeType.typeName"
          align="center"
        ></el-table-column>
        <el-table-column label="操作" width="360px" align="center">
          <template #default="scope">
            <el-button
              type="info"
              size="mini"
              icon="el-icon-document"
              round
              @click="handleDetailShow(scope.row)"
              >详细信息</el-button
            >
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
              round
              @click="handleEditBridge(scope.row)"
              >修改</el-button
            >
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-delete"
              round
              @click="handleDeleteBridge(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-size="pageSize"
        :page-sizes="[5, 10, 20]"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        background
        >></el-pagination
      >
    </div>
  </el-card>

  <div class="special-dialog">
    <el-dialog v-model="detailVisible">
      <el-descriptions title="桥梁详细信息" :column="2">
        <el-descriptions-item label="桥梁名称:">{{
          detailBridge.bridgeName
        }}</el-descriptions-item>
        <el-descriptions-item label="桥梁所在路名:">{{
          detailBridge.roadName
        }}</el-descriptions-item>
      </el-descriptions>

      <el-descriptions :column="3" border>
        <el-descriptions-item label="管理单位">{{
          detailBridge.manageUnit
        }}</el-descriptions-item>
        <el-descriptions-item label="养护单位">{{
          detailBridge.maintainUnit
        }}</el-descriptions-item>
        <el-descriptions-item label="监理单位">{{
          detailBridge.watchUnit
        }}</el-descriptions-item>
        <el-descriptions-item label="施工单位">{{
          detailBridge.buildUnit
        }}</el-descriptions-item>
        <el-descriptions-item label="建成年月">{{
          detailBridge.buildYear
        }}</el-descriptions-item>
        <el-descriptions-item label="养护类别">{{
          detailBridge.maintainType
        }}</el-descriptions-item>
        <el-descriptions-item label="养护等级">{{
          detailBridge.maintainClass
        }}</el-descriptions-item>
        <el-descriptions-item label="设计载荷">{{
          detailBridge.load
        }}</el-descriptions-item>

        <el-descriptions-item label="桥梁总长">{{
          detailBridge.length
        }}</el-descriptions-item>
        <el-descriptions-item label="桥梁总宽">{{
          detailBridge.width
        }}</el-descriptions-item>
        <el-descriptions-item label="桥梁跨数">{{
          detailBridge.spanNum
        }}</el-descriptions-item>

        <el-descriptions-item label="桥墩数量">{{
          detailBridge.pierNum
        }}</el-descriptions-item>
        <el-descriptions-item label="桥台数量">{{
          detailBridge.abutmentNum
        }}</el-descriptions-item>
        <el-descriptions-item label="车行道净宽">{{
          detailBridge.roadWidth
        }}</el-descriptions-item>
        <el-descriptions-item label="人行道净宽">{{
          detailBridge.sideWalkWidth
        }}</el-descriptions-item>
        <el-descriptions-item label="最高水位">{{
          detailBridge.maxWaterline
        }}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>

  <div class="special-dialog">
    <el-dialog
      v-model="dialogVisible"
      title="添加桥梁信息"
      @close="handleDialogClose"
    >
      <el-form
        :model="bridgeForm"
        :rules="rules"
        :ref="setFormRef"
        label-width="100px"
        status-icon
      >
        <el-form-item label="桥梁名" prop="bridgeName">
          <el-input
            v-model="bridgeForm.bridgeName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="桥梁类型" prop="bridgeType">
          <el-select
            v-model.number="bridgeForm.bridgeType"
            placeholder="选择桥梁类型"
            value-key="value"
            @focus="bridgeTypeFocus"
          >
            <el-option
              v-for="item in bridgeTypes"
              :key="item.typeId"
              :label="item.typeName"
              :value="item.typeId"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="所在路名" prop="roadName">
          <el-input v-model="bridgeForm.roadName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="管理单位" prop="manageUnit">
          <el-input
            v-model="bridgeForm.manageUnit"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="养护单位" prop="maintainUnit">
          <el-input
            v-model="bridgeForm.maintainUnit"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="监管单位" prop="watchUnit">
          <el-input
            v-model="bridgeForm.watchUnit"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="施工单位" prop="buildUnit">
          <el-input
            v-model="bridgeForm.buildUnit"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="建成年月" prop="buildYear">
          <el-date-picker
            v-model="bridgeForm.buildYear"
            type="date"
            placeholder="选择日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="养护类别" prop="maintainType">
          <el-input
            v-model="bridgeForm.maintainType"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="养护等级" prop="maintainClass">
          <el-input
            v-model="bridgeForm.maintainClass"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="设计载荷" prop="load">
          <el-input v-model="bridgeForm.load" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="桥梁总长" prop="length">
          <el-input
            v-model.number="bridgeForm.length"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="桥梁总宽" prop="width">
          <el-input v-model="bridgeForm.width" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="桥梁跨数" prop="spanNum">
          <el-input v-model="bridgeForm.spanNum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="桥墩数量" prop="pierNum">
          <el-input v-model="bridgeForm.pierNum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="桥台数量" prop="abutmentNum">
          <el-input
            v-model="bridgeForm.abutmentNum"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="车行道净宽" prop="roadWidth">
          <el-input
            v-model="bridgeForm.roadWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="人行道净宽" prop="sideWalkWidth">
          <el-input
            v-model="bridgeForm.sideWalkWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="最高水位" prop="maxWaterline">
          <el-input
            v-model="bridgeForm.maxWaterline"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm()">提交</el-button>
          <el-button @click="resetForm()">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { reactive, toRefs, onMounted, onUpdated } from "vue"
import axios from "@/api/request.js"
import useBridgeForm from "./useForm.js"
import { ElMessage, ElMessageBox } from "element-plus"
export default {
  setup() {
    const state = reactive({
      input: "",
      pageNum: 1,
      pageSize: 5,
      total: 0,
      bridges: [],
      detailBridge: {},
      detailVisible: false,
    })

    onMounted(() => {
      getBridgeList()
    })

    onUpdated(() => {
      getBridgeList()
    })

    const getBridgeList = async () => {
      const result = await axios.get("/bridge/page", {
        params: {
          pageNum: state.pageNum,
          pageSize: state.pageSize,
          bridgeName: state.input,
        },
      })
      state.bridges = result.content
      state.total = result.totalElements
    }

    const handleSizeChange = (newSize) => {
      state.pageSize = newSize
      getBridgeList()
    }

    const handleCurrentChange = (newPage) => {
      state.pageNum = newPage
      getBridgeList()
    }

    const handleDetailShow = (info) => {
      state.detailBridge = info
      state.detailVisible = true
    }

    const handleDeleteBridge = (info) => {
      axios.delete("/bridge?bridgeId=" + info.bridgeId)

      ElMessageBox.confirm("是否删除该桥梁资料卡", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios.delete("/bridge?bridgeId=" + info.id).then(() => {
            ElMessage.success("删除成功!")
            getBridgeList()
          })
        })
        .catch(() => {
          ElMessage.info({
            message: "已取消删除",
          })
        })
    }

    let {
      dialogVisible,
      bridgeForm,
      bridgeTypes,
      bridgeTypeFocus,
      rules,
      setFormRef,
      submitForm,
      resetForm,
      handleDialogClose,
      handleEditBridge,
    } = useBridgeForm()

    return {
      ...toRefs(state),
      getBridgeList,
      handleSizeChange,
      handleCurrentChange,
      handleDetailShow,
      handleDeleteBridge,
      dialogVisible,
      bridgeForm,
      bridgeTypes,
      bridgeTypeFocus,
      rules,
      setFormRef,
      submitForm,
      resetForm,
      handleDialogClose,
      handleEditBridge,
    }
  },
}
</script>

<style lang="less">
.special-dialog {
  .el-dialog {
    width: 600px;
    border-radius: 25px;
  }
}

.content-style {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  .el-pagination {
    padding-top: 20px;
  }
}
</style>
