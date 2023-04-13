<template>
  <div class="aside-content">
    <el-menu background-color="#576574" text-color="#fff" active-text-color="#54a0ff" :default-active="myPath" class="el-menu-vertical-demo" router unique-opened>
      <!-- <el-menu-item route="/home/view" index="/home/view">
        <i class="el-icon-s-platform" style="font-size: 24px;"></i>
        <span>视图</span>
      </el-menu-item> -->
      <el-menu-item route="/home/user" index="/home/user">
        <i class="el-icon-user" style="font-size: 24px;"></i>
        <span>用户管理</span>
      </el-menu-item>
      <el-submenu index='/home/plan'>
        <template slot="title">
          <i class="el-icon-collection" style="font-size: 24px;"></i>
          <span>策划管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item route="/home/select" index="/home/select">
            <span>查询</span>
          </el-menu-item>
          <el-menu-item route="/home/add" index="/home/add">
            <span>编辑</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item route="/home/question" index="/home/question">
        <i class="el-icon-message" style="font-size: 24px;"></i>
        <span>用户提问</span>
      </el-menu-item>
      <el-submenu index='/home/recycle'>
        <template slot="title">
          <i class="el-icon-delete" style="font-size: 24px;"></i>
          <span>回收站台</span>
        </template>
        <el-menu-item-group>
          <el-menu-item route="/home/deleteduser" index="/home/select">
            <span>用户</span>
          </el-menu-item>
          <el-menu-item route="/home/deletedplan" index="/home/add">
            <span>策划</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item route="/welcome" index="/">
        <i class="el-icon-position" style="font-size: 24px;"></i>
        <span>前往前台</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'Aside',
  data() {
    return {
      myPath: ''
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
.aside-content {
  width: 200px;
  height: 100%;
  background-color: #576574;
  color: #333;
  text-align: center;
  .el-menu {
    border: 0;
  }
}
</style>