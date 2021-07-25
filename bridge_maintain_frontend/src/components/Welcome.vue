<template>
  <el-card>
    <div class="bg-style">
      <span style="font-size: large; padding-bottom: 20px; padding-top: 20px">桥梁养护管理系统</span>
    </div>
    <el-carousel :interval="2000" type="card" height="350px">
      <el-carousel-item v-for="item in imgList" :key="item">
        <img :src="item.img" style="height:100%;width:100%;" />
      </el-carousel-item>
    </el-carousel>
  </el-card>
</template>

<script>
import { reactive, toRefs } from "@vue/reactivity"
export default {
  setup() {
    const state = reactive({
      imgList: [
        {
          img: require("../assets/mylogo.png"),
        },
        {
          img: require("../assets/login.png"),
        },
        {
          img: require("../assets/bg1.png"),
        },
        {
          img: require("../assets/bg2.png"),
        },
      ],
    })
    return {
      ...toRefs(state),
    }
  },
}
</script>

<style lang="less" scoped>
.bg-style {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
//   .span{
//       padding
//   }
}
</style>
