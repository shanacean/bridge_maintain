<template>
  <!-- 面包屑导航 -->
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>权限管理</el-breadcrumb-item>
    <el-breadcrumb-item>用户列表</el-breadcrumb-item>
  </el-breadcrumb>
  <el-card>
    <!-- 搜索区域 -->
    <el-row :gutter="25">
      <el-col :span="10">
        <el-input
          v-model="input"
          placeholder="请输入内容"
          suffix-icon="el-icon-search"
          clearable
          @change="getUserList"
          @clear="getUserList"
          @keydown.enter="getUserList"
        />
      </el-col>
      <el-col :span="4">
        <el-button type="primary" @click="dialogVisible = true"
          >添加用户</el-button
        >
      </el-col>
    </el-row>

    <div class="content-style">
      <!-- 列表区域 -->
      <el-table :data="users" stripe border fit>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="用户名" prop="username"></el-table-column>
        <el-table-column label="密码" prop="password"></el-table-column>
        <el-table-column label="角色" prop="role.roleName"> </el-table-column>
        <el-table-column label="状态" prop="state">
          <template #default="scope">
            <el-switch
              v-model="scope.row.state"
              @change="handleStateChange(scope.row)"
            ></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="240">
          <template #default="scope">
            <!-- 修改按钮 -->
            <el-button
              size="mini"
              type="primary"
              icon="el-icon-edit"
              @click="handleEditUser(scope.row)"
            ></el-button>
            <!-- 删除按钮 -->
            <el-button
              size="mini"
              type="danger"
              icon="el-icon-delete"
              @click="handleUserDelete(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrenChange"
        :current-page="currentPage"
        :page-size="pageSize"
        :page-sizes="[5, 10, 20]"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        background
      >
      </el-pagination>
    </div>
  </el-card>

  <!-- 添加修改用户表单 -->
  <el-dialog
    title="添加用户"
    v-model="dialogVisible"
    @close="handleDialogClose"
  >
    <el-form
      :model="userForm"
      :rules="rules"
      :ref="setFormRef"
      label-width="100px"
      status-icon
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="userForm.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          v-model="userForm.password"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="角色" prop="role">
        <!-- <el-input v-model="userForm.role" autocomplete="off"></el-input> -->
        <el-select
          v-model.number="userForm.role"
          value-key="value"
          placeholder="请选择角色"
        >
          <el-option
            v-for="item in roles"
            :key="item.roleId"
            :label="item.roleName"
            :value="item.roleId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()">提交</el-button>
        <el-button @click="resetForm()">重置</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import { reactive, ref, onMounted, toRefs } from "vue"
import { ElMessage, ElMessageBox } from "element-plus"
import axios from "@/api/request.js"
export default {
  setup() {
    const userList = reactive({
      roles: [],
      users: [],
      pageSize: 5,
      currentPage: 1,
      total: 0,
    })

    onMounted(() => {
      getUserList()
      roleFocus()
    })

    // 表单及其验证
    let userForm = reactive({
      id: 0,
      username: "",
      password: "",
      role: {
        roleId: undefined,
      },
      state: false,
    })

    const rules = {
      username: [{ required: true, message: "请输入账号", trigger: "blur" }],
      password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      role: [{ required: true, message: "请输入密码", trigger: "blur" }],
    }

    const formRef = ref(null)

    const setFormRef = (form) => {
      formRef.value = form
    }

    const dialogVisible = ref(false)

    //用户表单
    function handleDialogClose() {
      formRef.value.resetFields()
      userForm.role = null
      for (let key in userForm.value) {
        userForm[key] = ""
      }
    }

    //表单提交
    const roleFocus = async () => {
      const result = await axios.get("/user/role")
      userList.roles = result
    }

    async function submitForm() {
      formRef.value.validate((valid) => {
        if (!valid) return ElMessage.error("表单验证失败!")
        axios.post("/user", userForm).then(() => {
          userForm.id = 0
          userForm.username = ""
          userForm.password = ""
          userForm.state = false
          userForm.role.roleId = undefined
          getUserList()
        })
        dialogVisible.value = false
      })
    }

    function resetForm() {
      formRef.value.resetFields()
    }
    //搜索栏内容
    const input = ref("")

    async function getUserList() {
      const result = await axios.get(
        "/user?pageNum=" +
          userList.currentPage +
          "&pageSize=" +
          userList.pageSize +
          "&username=" +
          input.value
      )
      userList.users = result.content
      userList.total = result.totalElements
    }

    function handleSizeChange(newSize) {
      userList.pageSize = newSize
      getUserList()
    }

    function handleCurrenChange(newPage) {
      userList.currentPage = newPage
      getUserList()
    }

    //修改用户状态
    async function handleStateChange(info) {
      axios.put("/user?id=" + info.id + "&state=" + info.state)
    }

    //修改用户信息
    function handleEditUser(info) {
      userForm.id = info.id
      userForm.username = info.username
      userForm.password = info.password
      userForm.role = info.role
      userForm.state = info.state
      dialogVisible.value = true
    }

    //删除用户
    function handleUserDelete(info) {
      ElMessageBox.confirm("是否删除该用户", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios.delete("/user?id=" + info.id).then(() => {
            getUserList()
          })
          ElMessage.success("删除成功!")
        })
        .catch(() => {
          ElMessage.info({
            message: "已取消删除",
          })
        })
    }

    return {
      input,
      ...toRefs(userList),
      dialogVisible,
      userForm,
      rules,
      setFormRef,
      roleFocus,
      submitForm,
      resetForm,
      getUserList,
      handleSizeChange,
      handleCurrenChange,
      handleStateChange,
      handleEditUser,
      handleUserDelete,
      handleDialogClose,
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
