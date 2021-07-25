<template>
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>检测记录</el-breadcrumb-item>
    <el-breadcrumb-item>日常检测</el-breadcrumb-item>
  </el-breadcrumb>

  <el-card>
    <el-row :gutter="25">
      <el-col :span="10">
        <el-input
          v-model="input"
          placeholder="请输入内容"
          suffix-icon="el-icon-search"
          clearable
          @change="getDailyChecks"
          @clear="getDailyChecks"
          @keydown.enter="getDailyChecks"
        />
      </el-col>
      <el-col :span="4">
        <el-button type="success" @click="formVisible = true"
          >添加日常检测信息</el-button
        >
      </el-col>
    </el-row>

    <div class="content-style">
      <el-table :data="dailyChecks">
        <el-table-column label="检测编号" prop="checkId"></el-table-column>
        <el-table-column label="检测日期" prop="dailyDate"></el-table-column>
        <el-table-column
          label="桥梁名称"
          prop="bridge.bridgeName"
        ></el-table-column>
        <el-table-column label="巡检员" prop="user.username"></el-table-column>
        <el-table-column label="备注" prop="comments"></el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="handleDailyDelete(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrenChange"
        :current-page="pageNum"
        :page-size="pageSize"
        :page-sizes="[10, 20]"
        :total="total"
        layout="total, sizes, prev, pager, next"
        background
      >
      </el-pagination>
    </div>
  </el-card>

  <el-dialog v-model="formVisible">
    <el-form v-model="dailyForm">
      <el-row v-for="item in items" :key="item.itemId">
        <el-form-item :label="item.itemName">
          <!-- <el-input :></el-input> -->
        </el-form-item>
      </el-row>
    </el-form>
  </el-dialog>
</template>

<script>
import { reactive, toRefs, onMounted } from "vue"
import axios from "@/api/request.js"
import { ElMessageBox, ElMessage } from "element-plus"
export default {
  setup() {
    const state = reactive({
      input: "",
      total: 0,
      pageNum: 1,
      pageSize: 10,
      dailyChecks: [],
      detailVisible: false,
      formVisible: false,
      items: [],
      dailyForm: {},
    })

    onMounted(() => {
      getDailyChecks()
      getItems()
    })

    const getDailyChecks = async () => {
      const result = await axios.get(
        "/dailyCheck/page?pageNum=" +
          state.pageNum +
          "&pageSize=" +
          state.pageSize +
          "&checkId=" +
          state.input
      )
      state.dailyChecks = result.content
      state.total = result.totalElements
      console.log(result)
    }

    const handleSizeChange = (newSize) => {
      state.pageSize = newSize
      getDailyChecks()
    }

    const handleCurrentChange = (newPage) => {
      state.pageNum = newPage
      getDailyChecks()
    }

    const handleDailyDelete = async (info) => {
      ElMessageBox.confirm("是否删除该日常检测信息", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios.delete("/dailyCheck?checkId=" + info.checkId).then(() => {
            getDailyChecks()
          })
          ElMessage.success("删除成功!")
        })
        .catch(() => {
          ElMessage.info({
            message: "已取消删除",
          })
        })
    }

    const getItems = async () => {
      const result = await axios.get("/dailyCheck/checkItem")
      state.items = result
      console.log(state.items)
    }

    return {
      ...toRefs(state),
      getDailyChecks,
      handleSizeChange,
      handleCurrentChange,
      handleDailyDelete,
      getItems,
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
