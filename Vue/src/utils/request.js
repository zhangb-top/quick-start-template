// 导入axios包 
import axios from "axios";
import { Loading } from 'element-ui'
import store from '@/store/index'
import router from '@/router'

// 创建axios对象，配置请求的根路径
const instance = axios.create({
  // baseURL: 'http://8.130.94.28:6060/api'
  baseURL: 'http://localhost:8888'
})

let loading = null;
// 添加请求拦截器
instance.interceptors.request.use(
  config => {
    loading = Loading.service({
      lock: true,
      text: '加载中...',
      spinner: 'el-icon-loading',
      background: 'rgba(0, 0, 0, 0.7)'
    });
    // 1. 获取 token 值
    const tokenStr = store.state.tokenInfo
    // 2. 判断 tokenStr 的值是否为空
    if (tokenStr) {
      // 3. 添加身份认证字段
      config.headers.Authorization = `Bearer ${tokenStr}`
      config.headers.router = router.history.current.fullPath.slice(1, 5)
    }
    return config
  },
  function (error) {
    // 对请求错误做些什么
    return Promise.reject(error)
  }
)

// 响应拦截器（注意：响应拦截器也应该绑定给 instance 实例）
instance.interceptors.response.use(
  response => {
    // 隐藏 loading 效果
    loading.close();
    return response
  },
  error => {
    return Promise.reject(error)
  }
)

export default instance