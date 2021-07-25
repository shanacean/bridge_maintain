import axios from "axios"
import { ElMessage } from "element-plus"
axios.defaults.baseURL = process.env.VUE_APP_SERVER_URL
axios.defaults.timeout = 5000

axios.interceptors.request.use(
  (config) => {
    let token = window.localStorage.getItem("token")
    if (token) config.headers["Authorization"] = token
    return config
  },
  (error) => {
    ElMessage.error(error)
    return Promise.reject(error)
  }
)

axios.interceptors.response.use((response) => {
  if (response.data.code === 200) {
    return response.data.result
  } else {
    if (response.data.code === 500) {
      ElMessage.error(response.data.message)
      window.localStorage.clear
      window.location.href = "/#/login"
    } else ElMessage.error(response.data.message)
    return Promise.reject(response)
  }
})
export default axios