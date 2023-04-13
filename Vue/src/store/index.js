import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 初始的 state 对象
let initState = {
  // token 的信息对象
  tokenInfo: '',
  // 用户信息
  userInfo: {
    userID: '',
    username: '',
    identity: ''
  }
}
const stateStr = localStorage.getItem('state')
if (stateStr) {
  // 加载本地的数据
  initState = JSON.parse(stateStr)
}
export default new Vuex.Store({
  state: initState,
  getters: {
  },
  mutations: {
    // 更新 tokenInfo 数据的方法
    updateTokenInfo(state, payload) {
      // 把提交过来的 payload 对象，作为 tokenInfo 的值
      state.tokenInfo = payload
      this.commit('saveStateToStorage')
    },
    // 更新用户数据的方法
    updateUserInfo(state, payload) {
      // 把提交过来的 payload 对象，作为 userInfo 的值
      state.userInfo = payload;
      this.commit('saveStateToStorage')
    },
    // 将 state 持久化存储到本地
    saveStateToStorage(state) {
      localStorage.setItem('state', JSON.stringify(state))
    }
  },
  actions: {
  },
  modules: {
  }
})
