<template>
  <div class="plan-content" @click="gotoDetail">
    <div class="theme">主题：{{plan.theme}}</div>
    <div class="location">地点：{{plan.location}}</div>
    <div class="budget">预算：{{plan.budget}}</div>
    <div class="itinerary">行程安排：{{plan.itinerary}}</div>
  </div>
</template>

<script>
export default {
  name: 'plan',
  props: {
    plan: {
      required: true,
      type: Object
    }
  },
  methods: {
    gotoDetail() {
      const { href } = this.$router.resolve({ path: '/detail', query: { id: this.plan.id, title: this.plan.theme } })
      window.open(href, '_blank')
    }
  }
}
</script>

<style lang='scss' scope>
.plan-content {
  cursor: pointer;
  padding: 15px;
  .theme {
    font-size: 1.25rem;
    font-weight: 700;
    margin: 10px 0 20px 0;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
  }
  .budget {
    font-size: 1rem;
    margin: 10px 0;
  }
  .itinerary {
    font-size: 1rem;
    margin: 10px 0;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
  }
}
</style>