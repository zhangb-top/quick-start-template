import axios from '@/utils/request'
import CryptoJS from 'crypto-js'

// 登录
export const loginAPI = user => {
  return axios.post('/users/login', user)
}

// 注册
export const registerAPI = user => {
  user.password = CryptoJS.SHA256(user.password).toString()
  return axios.post('/users/register', user)
}

// 分页查询用户
export const getUsersByPageAPI = (currentPage, pageSize, searchData) => {
  return axios.post(`/users/${currentPage}/${pageSize}`, searchData)
}