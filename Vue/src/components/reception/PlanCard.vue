<template>
  <div :style="{width:width}" class="plans-card-content">
    <el-card v-if="plans.length!==0" class="plans-card">
      <div v-for='item in plans' :key="item.id" class="plan-item">
        <plan :plan="item"></plan>
      </div>
      <div class="bottom">
        <el-pagination @current-change="currentChange" :current-page="currentPage" :page-size="pageSize" :total="total" class="pagination" background layout="prev, next">
        </el-pagination>
      </div>
    </el-card>
    <el-card v-else class="plans-card">
      <el-empty description="暂无数据"></el-empty>
    </el-card>
  </div>
</template>

<script>
import Plan from './Plan.vue'
export default {
  components: { Plan },
  name: 'plansCard',
  props: {
    plans: {
      required: true,
      type: Array
    },
    total: {
      required: true,
      type: Number
    },
    currentPage: {
      required: true,
      type: Number
    },
    pageSize: {
      required: true,
      type: Number
    },
    width: {
      default: '80%',
      type: String
    }
  },
  methods: {
    currentChange(currentPage) {
      this.$emit('current-change', currentPage)
    }
  }
}
</script>

<style lang='scss' scope>
.plans-card {
  .bottom {
    height: 2.1875rem;
    position: relative;
    .pagination {
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translateX(-50%);
    }
  }
}
</style>