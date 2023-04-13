<template>
  <div class="home-content">
    <!-- 头部区域组件 -->
    <my-header></my-header>

    <!-- 下部区域 -->
    <div class="home-main-box">
      <!-- 左侧边栏组件 -->
      <div>
        <my-aside></my-aside>
      </div>
      <!-- 右侧主体组件 -->
      <el-main class="home-main-body">
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </el-main>
    </div>
  </div>
</template>

<script>
import MyHeader from '@/components/system/Header.vue'
import MyAside from '@/components/system/Aside.vue'
export default {
  name: 'Home',
  components: {
    'my-header': MyHeader,
    'my-aside': MyAside
  }
}
</script>

<style lang="scss" scope>
.home-content {
  height: 100%;
  display: flex;
  flex-direction: column;
  .home-main-box {
    height: 100%;
    display: flex;
    .home-main-body {
      padding: 15px;
      flex: 1;
      .top {
        margin-bottom: 1.875rem;
      }
      .el-pagination {
        margin-top: 0.625rem;
      }
    }
  }
  ::-webkit-scrollbar {
    display: none;
  }
}
</style>