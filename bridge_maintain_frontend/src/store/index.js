import { createStore } from "vuex"

export default createStore({
  state: {
    rightList: JSON.parse(localStorage.getItem("rightList")),
    userId: JSON.parse(localStorage.getItem("userId")),
  },
  mutations: {
    setMenuList(state, data) {
      console.log(state.rightList)
      state.rightList = data
      localStorage.setItem("rightList", JSON.stringify(data))
    },
    setUserId(state, data) {
      state.userId = data
      localStorage.setItem("userId", JSON.stringify(data))
    },
  },
  actions: {},
  modules: {},
})
