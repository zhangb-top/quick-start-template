import axios from '@/utils/request'

// 分页查询旅游策划数据
export const getPlansByPageAPI = (currentPage, pageSize) => {
  return axios.get(`/travels/${currentPage}/${pageSize}`)
}

// 根据id获取旅游策略
export const getplanByIdAPI = id => {
  return axios.get(`/travels/${id}`)
}
