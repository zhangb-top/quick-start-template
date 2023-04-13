<template>
  <div class="header-content">
    <div class="des">后台管理系统</div>
    <el-button @click="outlogin" size="small">退出登录</el-button>
  </div>
</template>

<script>
export default {
  name: 'Header',
  methods: {
    outlogin() {
      // 删除token
      localStorage.setItem('state', {})
      // 跳页面
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="scss" scope>
.header-content {
  padding: 0 10px;
  background-color: #8395a7;
  line-height: 60px;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>