<template>
  <div class="App">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang='scss' scope>
.App,
html,
body {
  overflow: auto;
  margin: 0;
  width: 100%;
  height: 100%;
  scroll-behavior: smooth;
  scroll-padding-top: 80px;
  font-family: 'sans', 'serif', 'monospace';
  .el-message {
    margin-top: 70px;
  }
}
// 手机端适配
@media screen and (max-width: 750px) {
  .App {
    -webkit-tap-highlight-color: transparent;
  }
  body {
    .el-notification {
      display: none;
    }
  }
}
::-webkit-scrollbar {
  display: none;
}
</style>
