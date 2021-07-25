<template>
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>检测记录</el-breadcrumb-item>
    <el-breadcrumb-item>定期检测</el-breadcrumb-item>
  </el-breadcrumb>

  <el-card>
    <el-row :gutter="25">
      <el-col :span="10">
        <el-input
          v-model="input"
          placeholder="请输入内容"
          suffix-icon="el-icon-search"
          clearable
          @change="getRegularChecks"
          @clear="getRegularChecks"
          @keydown.enter="getRegularChecks"
        />
      </el-col>
      <el-col :span="4">
        <el-button type="success" @click="formVisible = true"
          >添加定期检测单</el-button
        >
      </el-col>
    </el-row>

    <!-- 表格 -->
    <div class="content-style">
      <el-table :data="regularChecks" stripe border fit>
        <el-table-column
          label="定期检测编号"
          prop="regularId"
        ></el-table-column>
        <el-table-column label="检测日期" prop="regularDate"></el-table-column>
        <el-table-column label="BSI值" prop="bsiValue"></el-table-column>
        <el-table-column label="BCI值" prop="bciValue"></el-table-column>
        <el-table-column label="巡检员" prop="user.username"></el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button
              size="mini"
              type="primary"
              icon="el-icon-document"
              @click="handleDetailInfo(scope.row)"
              >详情</el-button
            >
            <el-button
              size="mini"
              type="danger"
              icon="el-icon-delete"
              @click="handleRegularDelete(scope.row)"
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
        :page-sizes="[10, 20]"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        background
      ></el-pagination>
    </div>
  </el-card>

  <el-dialog v-model="formVisible" title="添加定期检测信息" width="50%">
    <el-form v-model="regularForm">
      <el-form-item label="选择桥梁" prop="bridge">
        <el-select
          v-model.number="regularForm.bridge"
          @focus="bridgeFocus"
          @change="handleBridgeChange"
          value-key="value"
        >
          <el-option
            v-for="item in bridges"
            :key="item.bridgeId"
            :label="item.bridgeName"
            :value="item.bridgeId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-row :gutter="24">
          <el-col
            :span="6"
            v-for="(item, index) in regularForm.regularComponentList"
            :key="index"
          >
            <el-form-item
              :label="item.typeComponent.component.componentName + '扣分值'"
            >
              <el-input-number
                v-model="item.value"
                :step="5"
                :min="0"
                :max="100"
                size="mini"
              ></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item>
        <el-button size="mini" type="primary" @click="submitForm()"
          >批量添加</el-button
        >
        <el-button size="mini" type="info" @click="resetForm()">重置</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <el-dialog v-model="detailVisible">
    <el-row v-for="(item, index) in details" :key="index">
      <div class="row-style">
        <span>{{ item.typeComponent.component.componentName }}扣分值</span>
        <span>{{ item.value }}</span>
      </div>
    </el-row>
  </el-dialog>
</template>

<script>
import { onMounted, reactive, toRefs } from "vue"
import axios from "@/api/request.js"
import { useStore } from "vuex"
import { ElMessage, ElMessageBox } from "element-plus"
export default {
  setup() {
    const state = reactive({
      total: 0,
      input: "",
      pageNum: 1,
      pageSize: 10,
      regularChecks: [],
      bridges: [],
      formVisible: false,
      detailVisible: false,
      formItems: [],
      regularForm: {
        regularId: undefined,
        bridge: {
          bridgeId: undefined,
        },
        user: {
          id: undefined,
        },
        regularComponentList: [],
      },
      items: [],
      details: [],
    })

    const store = useStore()

    onMounted(() => {
      getRegularChecks()
    })
    // 表格
    const getRegularChecks = async () => {
      const result = await axios.get(
        "/regular/page?pageNum=" +
          state.pageNum +
          "&pageSize=" +
          state.pageSize +
          "&regularId" +
          state.input
      )
      state.regularChecks = result.content
      state.total = result.total
    }

    const handleSizeChange = (newSize) => {
      state.pageSize = newSize
      getRegularChecks()
    }

    const handleCurrentChange = (newPage) => {
      state.pageNum = newPage
      getRegularChecks()
    }

    // 表单
    const bridgeFocus = async () => {
      const result = await axios.get("/bridge")
      state.bridges = result
    }

    const handleBridgeChange = async (info) => {
      const result = await axios.get("/bridge/?bridgeId=" + info)
      console.log(result)
      state.formItems = result.bridgeType.typeComponentList
      state.formItems.forEach((element) => {
        state.regularForm.regularComponentList.push({
          regularComponentId: undefined,
          regualrId: undefined,
          value: 5,
          typeComponent: element,
        })
      })
    }

    const submitForm = async () => {
      state.regularForm.user.id = store.state.userId
      const result = await axios.post("/regular", state.regularForm)
      state.regularForm.regularComponentList.forEach((e) => {
        state.items.push({
          regularComponentId: undefined,
          typeComponent: {
            typeComponentId: e.typeComponent.typeComponentId,
          },
          value: e.value,
          regularCheck: {
            regularId: result.regularId,
          },
        })
      })
      axios.post("/regular/items", state.items).then(() => {
        axios
          .get("/regular/computed?regularId=" + result.regularId)
          .then(() => {
            getRegularChecks()
            state.formVisible = false
          })
      })
    }

    const handleRegularDelete = (info) => {
      ElMessageBox.confirm("是否删除该检测项", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          console.log(info.regularId)
          axios.delete("/regular?regularId=" + info.regularId).then(() => {
            getRegularChecks()
            ElMessage.success("删除成功!")
          })
        })
        .catch(() => {
          ElMessage.info({
            message: "已取消删除",
          })
        })
    }

    const handleDetailInfo = (info) => {
      state.detailVisible = true
      state.details = info.regularComponentList
      console.log(info)
    }

    return {
      ...toRefs(state),
      getRegularChecks,
      handleSizeChange,
      handleCurrentChange,
      bridgeFocus,
      handleBridgeChange,
      submitForm,
      handleRegularDelete,
      handleDetailInfo,
    }
  },
}
</script>

<style lang="less" scoped>
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
