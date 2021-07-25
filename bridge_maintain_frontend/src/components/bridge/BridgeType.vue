<template>
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>桥梁资料</el-breadcrumb-item>
    <el-breadcrumb-item>监测内容</el-breadcrumb-item>
  </el-breadcrumb>

  <el-card>
    <div class="content-style">
      <el-table :data="bridgeType" stripe border fit>
        <el-table-column
          label="桥梁类型编号"
          prop="typeId"
          align="center"
        ></el-table-column>
        <el-table-column
          label="桥梁类型名"
          prop="typeName"
          align="center"
        ></el-table-column>
        <el-table-column label="部位" align="center">
          <el-tag type="success">桥面系</el-tag>
          <el-tag type="info">上部结构</el-tag>
          <el-tag type="warning">下部结构</el-tag>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
              round
              @click="handlePartWeight(scope.row)"
              >部位权重值</el-button
            >
            <el-button
              type="success"
              size="mini"
              icon="el-icon-edit"
              round
              @click="handleComponentWeight(scope.row)"
              >构件权重值</el-button
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

  <div class="special-dialog">
    <el-dialog v-model="partVisible" width="25%">
      <el-form :model="parts" label-width="100px" status-icon>
        <el-row v-for="(item, index) in parts" :key="index">
          <el-form-item :label="item.part.partName + '权重'">
            <el-input-number
              v-model="item.weight"
              :precision="2"
              :step="0.05"
              :max="1"
              size="mini"
            ></el-input-number>
          </el-form-item>
        </el-row>
        <el-form-item>
          <el-button size="mini" type="primary" @click="submitPartsForm()"
            >批量修改</el-button
          >
          <el-button size="mini" type="info" @click="resetForm()"
            >重置</el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>

  <div class="special-dialog">
    <el-dialog v-model="componentVisible" width="60%">
      <el-form :model="components" label-width="120px" status-icon>
        <el-tag type="success">桥面系</el-tag>
        <el-row :gutter="24">
          <el-col
            :span="8"
            v-for="(item, index) in components.filter(
              (e) => e.component.part.partName == '桥面系'
            )"
            :key="index"
          >
            <el-form-item :label="item.component.componentName + '权重'">
              <el-input-number
                v-model="item.weight"
                :precision="2"
                :step="0.05"
                :max="1"
                size="mini"
              ></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-tag type="info">上部结构</el-tag>
        <el-row :gutter="24">
          <el-col
            :span="8"
            v-for="(item, index) in components.filter(
              (e) => e.component.part.partName == '上部结构'
            )"
            :key="index"
          >
            <el-form-item :label="item.component.componentName + '权重'">
              <el-input-number
                v-model="item.weight"
                :precision="2"
                :step="0.05"
                :max="1"
                size="mini"
              ></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-tag type="warning">下部结构</el-tag>
        <el-row :gutter="24">
          <el-col
            :span="8"
            v-for="(item, index) in components.filter(
              (e) => e.component.part.partName == '下部结构'
            )"
            :key="index"
          >
            <el-form-item :label="item.component.componentName + '权重'">
              <el-input-number
                v-model="item.weight"
                :precision="2"
                :step="0.05"
                :max="1"
                size="mini"
              ></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item>
          <el-button size="mini" type="primary" @click="submitComponentForm()"
            >批量修改</el-button
          >
          <el-button size="mini" type="info" @click="resetForm()"
            >重置</el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { onMounted, reactive, toRefs } from "vue"
import axios from "@/api/request.js"
import { ElMessage } from "element-plus"
export default {
  setup() {
    const state = reactive({
      input: 0,
      pageNum: 1,
      pageSize: 10,
      total: 0,
      bridgeType: [],
      parts: [],
      components: [],
      partVisible: false,
      componentVisible: false,
    })

    onMounted(() => {
      getTypeList()
    })

    const getTypeList = async () => {
      const result = await axios.get(
        "/bridgeType/page?pageNum=" +
          state.pageNum +
          "&pageSize=" +
          state.pageSize +
          "&bridgeName=" +
          state.input
      )
      console.log(result)
      state.bridgeType = result.content
      state.total = result.totalElements
    }

    const handleSizeChange = (newSize) => {
      state.pageSize = newSize
      getTypeList()
    }

    const handleCurrenChange = (newPage) => {
      state.pageNum = newPage
      getTypeList()
    }

    const handlePartWeight = (info) => {
      state.partVisible = true
      state.parts = info.typePartSet
    }

    const submitPartsForm = () => {
      const sum = state.parts.reduce((pre, cur) => {
        return pre + cur.weight
      }, 0)
      if (sum != 1) return ElMessage.error("部位和不为1, 请检查权重分配!")
      axios.put("/monitor/part", state.parts)
      state.partVisible = false
    }

    const submitComponentForm = () => {
      const sum1 = state.components
        .filter((e) => e.component.part.partName == "桥面系")
        .reduce((pre, cur) => {
          return pre + cur.weight
        }, 0)
      const sum2 = state.components
        .filter((e) => e.component.part.partName == "上部结构")
        .reduce((pre, cur) => {
          return pre + cur.weight
        }, 0)
      const sum3 = state.components
        .filter((e) => e.component.part.partName == "下部结构")
        .reduce((pre, cur) => {
          return pre + cur.weight
        }, 0)
      if (sum1 != 1)
        return ElMessage.error("桥面系构件权重和不为1， 请检查权重分配!")
      if (sum2 != 1)
        return ElMessage.error("上部结构构件权重和不为1， 请检查权重分配!")
      if (sum3 != 1)
        return ElMessage.error("下部结构构件权重和不为1， 请检查权重分配!")
      axios.put("/monitor/component", state.components)
      state.componentVisible = false
    }

    const handleComponentWeight = (info) => {
      state.componentVisible = true
      state.components = info.typeComponentList
      console.log(state.components)
    }

    return {
      ...toRefs(state),
      getTypeList,
      handleSizeChange,
      handleCurrenChange,
      handlePartWeight,
      submitPartsForm,
      handleComponentWeight,
      submitComponentForm,
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

.special-dialog {
  .el-dialog {
    border-radius: 25%;
    height: 500px;
    .el-form {
      .el-tag {
        padding-left: 20px;
      }
    }
  }
}
</style>
