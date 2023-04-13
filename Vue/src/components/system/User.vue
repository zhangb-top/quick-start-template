<template>
  <div class="comment-content">
    <el-breadcrumb class="top" separator="/">
      <el-breadcrumb-item>后台</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 查询 -->
    <el-form :model="searchData" :inline="true">
      <el-form-item label="用户名">
        <el-input v-model="searchData.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="身份">
        <el-select v-model="searchData.identity" placeholder="请选择用户身份">
          <el-option label="管理员" :value="1"></el-option>
          <el-option label="普通用户" :value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getUsersByPage">查 询</el-button>
        <el-button @click="resetForm">重 置</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table border ref="table" :data="users" highlight-current-row style="width: 100%">
      <el-table-column type="index" width="50" label="#" align="center">
      </el-table-column>
      <el-table-column property="username" label="用户名" align="center" prop="username">
      </el-table-column>
      <el-table-column property="identity" label="身份" align="center" prop="identity">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.identity===2" type="success" disable-transitions>普通用户</el-tag>
          <el-tag v-else type="primary" disable-transitions>管理员</el-tag>
        </template>
      </el-table-column>
      <el-table-column property="caozuo" label="操作" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删 除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页器 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="this.pagination.currentPage" :page-sizes="this.pagination.pageSizes" :page-size="this.pagination.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="this.pagination.total">
    </el-pagination>
  </div>
</template>

<script>
import { getUsersByPageAPI } from '@/api/userAPI'
export default {
  name: 'User',
  data() {
    return {
      users: [],
      pagination: {
        pageSizes: [6, 10, 14],
        pageSize: 6,
        currentPage: 1,
        total: 0
      },
      searchData: {
        username: '',
        identity: ''
      }
    }
  },
  methods: {
    // 获取数据
    async getUsersByPage() {
      const { data: res } = await getUsersByPageAPI(this.pagination.currentPage, this.pagination.pageSize, this.searchData)
      if (res.code !== 200) return this.$message({ message: res.msg, type: 'error' })
      this.users = res.data.users
      this.pagination.total = res.data.total
    },
    // 页面数据量变化
    handleSizeChange(pageSize) {
      this.pagination.pageSize = pageSize
      this.getUsersByPage()
    },
    // 当前页面变化
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage
      this.getUsersByPage()
    },
    // 删除
    async handleDelete(id) {
      const { data: res } = await deleteCommentByIdAPI(id)
      if (res.code !== 200) return this.$message({ message: res.msg, type: 'error' })
      this.$message({ message: res.msg, type: 'success' })
      this.getUsersByPage()
    },
    // 重置表单
    resetForm() {
      this.searchData.username = ''
      this.searchData.identity = ''
      this.getUsersByPage()
    }
  },
  created() {
    this.getUsersByPage()
  }
}
</script>

<style lang='scss' scope>
</style>