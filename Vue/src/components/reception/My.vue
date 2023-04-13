<template>
  <div class="my-content">
    <!-- 信息部分 -->
    <el-card class="user-info">
      <el-descriptions class="margin-top" title="我的信息" :column="3" border>
        <template slot="extra">
          <el-button type="primary" size="small" @click="updateUserInfo">修改</el-button>
        </template>
        <el-descriptions-item>
          <template slot="label"><i class="el-icon-user"></i>用户名</template>
          {{userInfo.username}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"><i class="el-icon-mobile-phone"></i>手机号</template>
          功能开发中...
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"><i class="el-icon-location-outline"></i>居住地</template>
          功能开发中...
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"><i class="el-icon-tickets"></i>身份</template>
          <el-tag size="small">{{userInfo.identity}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"><i class="el-icon-office-building"></i>联系地址</template>
          功能开发中...
        </el-descriptions-item>
      </el-descriptions>
    </el-card>
    <!-- 操作部分 -->
    <el-card class="edit">
      <div class="el-descriptions__header">
        <div class="el-descriptions__title">操作</div>
      </div>
      <div class="main">
        <el-tag class="item" @click="logout">退出登录</el-tag>
        <el-tag class="item" @click="toQuestionDetail">定制</el-tag>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'My',
  data() {
    return {
      userInfo: this.$store.state.userInfo
    }
  },
  methods: {
    // 修改用户信息
    updateUserInfo() {
      // TODO后面实现
      this.$message('功能开发中，敬请期待...')
    },
    // 去用户的提问详情页
    toQuestionDetail() {
      const { href } = this.$router.resolve('/questiondetail')
      window.open(href, '_blank')
    },
    // 退出登录
    logout() {
      localStorage.setItem('state', JSON.stringify({}))
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="scss" scope>
.my-content {
  margin-top: 60px;
  .user-info,
  .edit {
    width: 80%;
    margin: 0.9375rem auto;
    .item {
      cursor: pointer;
      margin: 0 0.625rem;
    }
  }
}
</style>