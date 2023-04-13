<template>
  <div class="top-content">
    <el-menu router :default-active="myPath" class="el-menu-welcome" mode="horizontal" background-color="#596275" text-color="#fff" active-text-color="#ffd04b">
      <div class="logo" @click="gotoWelcome">XXX</div>
      <el-menu-item route="/welcome/index" index="/welcome/index" class="item">首页</el-menu-item>
      <el-menu-item route="/welcome/customize" index="/welcome/customize" class="item">定制</el-menu-item>
      <el-menu-item route="/welcome/my" index="/welcome/my" class="item">我的</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'BlogAside',
  data() {
    return {
      myPath: '/welcome/index',
      isShow: true
    }
  },
  methods: {
    // 去首页
    gotoWelcome() {
      this.$router.push({ path: '/welcome' })
    }
  },
  watch: {
    $route: function () {
      this.myPath = this.$route.matched[1] ? this.$route.matched[1].path : this.$route.matched[0].path
    }
  },
  created() {
    this.myPath = this.$route.matched[1] ? this.$route.matched[1].path : this.$route.matched[0].path
  }
}
</script>

<style lang='scss' scope>
.el-menu-welcome {
  border-bottom: none !important;
  z-index: 9999;
  width: 100%;
  position: fixed !important;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 60px;
  .logo {
    font-family: '华文行楷';
    cursor: pointer;
    position: absolute;
    color: #48dbfb;
    left: 6%;
    font-size: 2.25rem;
  }
  .system {
    cursor: pointer;
    position: absolute;
    color: #fff;
    right: 5%;
    font-size: 1.125rem;
  }
}
</style>