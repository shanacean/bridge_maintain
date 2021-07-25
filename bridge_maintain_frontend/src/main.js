import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/css/global.css'
import  { components, plugins } from './plugins/element.js'
import "element-plus/lib/theme-chalk/index.css"
import './assets/font/iconfont.css'
import store from './store'


const app = createApp(App)

components.forEach((component) => {
  app.component(component.name, component)
})

plugins.forEach((plugin) => {
  app.use(plugin)
})
app.use(router).use(store).mount('#app')