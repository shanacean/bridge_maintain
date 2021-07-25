import { createRouter, createWebHashHistory } from "vue-router"
import Login from "../view/Login.vue"
import Home from "../components/Home.vue"
import Welcome from "../components/Welcome.vue"
import User from "../components/admin/User.vue"
import Bridge from "../components/bridge/Bridge.vue"
import BridgeType from "../components/bridge/BridgeType.vue"
import DailyCheck from "../components/check/DailyCheck.vue"
import RegularCheck from "../components/check/RegularCheck.vue"

const routes = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/home",
    component: Home,
    redirect: "/welcome",
    children: [
      {
        path: "/welcome",
        component: Welcome,
      },
      {
        path: "/user",
        component: User,
      },
      {
        path: "/bridge/info",
        component: Bridge,
      },
      {
        path: "/bridge/monitor",
        component: BridgeType,
      },
      {
        path: "/check/dailyCheck",
        component: DailyCheck,
      },
      {
        path: "/check/regularCheck",
        component: RegularCheck,
      }
    ],
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

//路由前置
router.beforeEach((to, from, next) => {
  if (to.path === "/login") return next()
  const token = window.localStorage.getItem("token")
  if (!token) {
    return next("/login")
  }
  next()
})

export default router
