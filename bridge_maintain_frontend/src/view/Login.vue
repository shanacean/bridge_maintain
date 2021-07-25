<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 图标 -->
      <div class="web_symbol">
        <img src="../assets/mylogo.png" />
      </div>
      <!-- 表单 -->
      <el-form
        class="login-form"
        :ref="setloginFormRef"
        :model="loginForm"
        :rules="rules"
      >
        <el-form-item class="login_input" prop="username">
          <el-input
            prefix-icon="el-icon-user-solid"
            v-model="loginForm.username"
          ></el-input>
        </el-form-item>
        <el-form-item class="login_input" prop="password">
          <el-input
            prefix-icon="el-icon-lock"
            v-model="loginForm.password"
            type="password"
            @keydown.enter="login"
          ></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="login"
            >登录</el-button
          >
          <el-button type="info" @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { reactive, ref } from "vue"
import { useRouter } from "vue-router"
import axios from "../api/request.js"
import { useStore } from "vuex"
import { ElMessage } from 'element-plus'
export default {
  name: "Login",
  setup() {
    const loginForm = reactive({
      username: "",
      password: "",
    })

    const rules = {
      username: [{ required: true, message: "请输入账号", trigger: "blur" }],
      password: [{ required: true, message: "请输入密码", trigger: "blur" }],
    }

    const loginFormRef = ref()

    const router = useRouter()

    const store = useStore()

    // 获取ref
    const setloginFormRef = (elForm) => {
      loginFormRef.value = elForm
    }

    //重置表单
    const resetForm = () => {
      loginFormRef.value.resetFields()
    }

    const login = () => {
      loginFormRef.value.validate(async (valid) => {
        if (!valid) return ElMessage.error("登录验证失败")
        const result = await axios.post("/user/login", loginForm)
        window.localStorage.setItem("token", result.token)
        window.localStorage.setItem("username", result.username)
        window.localStorage.setItem("userId", result.userId)
        store.commit("setMenuList", result.rights.mainMenus)
        store.commit("setUserId", result.userId)
        router.push("/home")
      })
    }
    return { loginForm, rules, setloginFormRef, resetForm, login }
  },
}
</script>

<style lang="less" scoped>
.login_container {
  background: url("../assets/login.png") no-repeat;
  background-size: cover;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.login_box {
  width: 450px;
  height: 300px;
  background-color: whitesmoke;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.web_symbol {
  height: 75px;
  width: 75px;
  border-radius: 50%;
  padding: 10px;
  margin: 0 auto;
  img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    background-color: whitesmoke;
  }
}
.login_input {
  padding: 2px 20px;
}
.btns {
  display: flex;
  justify-content: center;
}
</style>
