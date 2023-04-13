<template>
  <div class="index-content">
    <!-- 文章列表 -->
    <plan-card :plans="plans" :currentPage="this.pagination.currentPage" :pageSize="this.pagination.pageSize" :total="this.pagination.total" v-on:current-change="handleCurrentChange"></plan-card>
  </div>
</template>

<script>
import PlanCard from './PlanCard.vue'
import { getPlansByPageAPI } from '@/api/planAPI'
export default {
  components: { PlanCard },
  name: 'BlogIndex',
  data() {
    return {
      // 旅游策划
      plans: [],
      pagination: {
        currentPage: 1,
        pageSize: 7,
        total: 0
      }
    }
  },
  methods: {
    async getPlansByPage() {
      const { data: res } = await getPlansByPageAPI(this.pagination.currentPage, this.pagination.pageSize)
      this.plans = res.data.plannings
      this.pagination.total = res.data.total
    },
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage
      this.getPlansByPage()
    }
    /* gotoDetail(article) {
      const { href } = this.$router.resolve({ path: '/detail', query: { id: article.id, title: article.title } })
      window.open(href, '_blank')
    } */
  },
  created() {
    this.getPlansByPage()
  }
}
</script>

<style lang='scss' scope>
.index-content {
  margin-top: 60px;
  display: flex;
  justify-content: space-evenly;
}
</style>