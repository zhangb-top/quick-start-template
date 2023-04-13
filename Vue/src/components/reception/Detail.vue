<template>
  <div class="detail-content" v-title :data-title='this.$route.query.title'>
    <!-- 内容 -->
    <el-card class="plans-box">
      <div class="plan-theme-box" id="theme">{{plan.theme}}</div>
      <div class="plan-location-box item">地点：{{plan.location}}</div>
      <div class="plan-hotel-box item">酒店：{{plan.hotel}}</div>
      <div class="plan-budget-box item">预算：{{plan.budget}}</div>
      <hr>
      <div class="plan-itinerary-box item" ref="plan">{{plan.itinerary}}</div>
    </el-card>
    <!-- 跳转 -->
    <a href="#theme" class="top">↑</a>
  </div>
</template>

<script>
import { getplanByIdAPI } from '@/api/planAPI'
export default {
  name: 'Detail',
  data() {
    return {
      plan: {}
    }
  },
  methods: {
    async getplanById(id) {
      const { data: res } = await getplanByIdAPI(id)
      this.plan = res.data
    }
  },
  created() {
    this.getplanById(this.$route.query.id)
  }
}
</script>

<style lang='scss' scope>
.detail-content {
  margin-top: 60px;
  word-wrap: break-word;
  .plans-box {
    width: 80%;
    margin: auto;
    .plan-theme-box {
      text-align: center;
      padding: 20px;
      font-size: 26px;
      color: #ee5253;
    }
    .item {
      padding: 0.625rem;
      font-size: 1.125rem;
    }
  }
  /* img {
    max-width: 100%;
    border-radius: 0.3125rem;
  } */
  .top {
    position: fixed;
    bottom: 50px;
    right: 50px;
    display: block;
    width: 3.125rem;
    height: 3.125rem;
    background-color: rgba($color: #1e293b, $alpha: 0.8);
    text-align: center;
    line-height: 3.125rem;
    border-radius: 50%;
    text-decoration: none;
    color: #fff;
  }
  a {
    color: #fff;
    font-weight: 700;
  }
}
</style>