<template>
  <div class="blog-bottom-content">
    <div class="box">
      <p class="Copyright">Copyright © 2023 - <span>{{year}} Designed by XXX</span></p>
      <!-- <p class="record-no"><a href="http://icp.chinaz.com/home/info?host=xiongsihao.com">赣ICP备20000310号</a></p> -->
    </div>
  </div>
</template>

<script>
export default {
  name: 'BlogBottom',
  computed: {
    year() {
      return new Date().getFullYear()
    }
  }
}
</script>

<style lang='scss' scope>
.blog-bottom-content {
  position: relative;
  bottom: 0;
  width: 100%;
  height: 6.25rem;
  background-color: #596275;
  color: #ecf0f1;
  text-align: center;
  .box {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    a {
      text-decoration: none;
      color: #bdc3c7;
    }
  }
}
</style>