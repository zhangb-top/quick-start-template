<template>
  <div class="question-detail-content" v-infinite-scroll="getQuestionsByPage" infinite-scroll-distance='5'>
    <el-card v-for="question in questions" :key="question.id" class="item">
      <div class="el-descriptions__header">
        <div class="el-descriptions__title">问题： {{question.content}}</div>
      </div>
      <div v-if="question.answered===0" class="unreplay">我们会尽快回复哒，请耐心等待</div>
      <div v-if="question.answered===1" class="replay" @click="gotoDetail(question)">点我查看专属旅游策划</div>
    </el-card>
  </div>
</template>

<script>
import { getQuestionsByPageAPI } from '@/api/question'
export default {
  namne: 'QuestionDetail',
  data() {
    return {
      questions: [],
      userID: this.$store.state.userInfo.userID,
      pagination: {
        currentPage: 0,
        pageSize: 3,
        // 总页数
        pages: '',
        total: 0
      }
    }
  },
  methods: {
    async getQuestionsByPage() {
      // 判断还有没有数据
      if (this.pagination.pages === this.pagination.currentPage) return
      // 当前页面 +1
      this.pagination.currentPage++
      const { data: res } = await getQuestionsByPageAPI(this.userID, this.pagination.currentPage, this.pagination.pageSize)
      if (res.code !== 200) return this.$message({ message: res.msg, type: 'error' })
      this.questions.push(...res.data.plannings)
      this.pagination.total = res.data.total
      this.pagination.pages = res.data.pages
    },
    gotoDetail(question) {
      this.$router.push({ path: '/detail', query: { id: question.answer.id, title: question.answer.theme } })
    }
  },
  created() {
    this.getQuestionsByPage()
  }
}
</script>

<style lang="scss" scope>
.question-detail-content {
  overflow: auto;
  width: 80%;
  height: 90vh;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  .item {
    margin: 1.25rem;
    .replay {
      cursor: pointer;
      color: #409eff;
    }
    .unreplay {
      color: #ccc;
    }
  }
}
</style>