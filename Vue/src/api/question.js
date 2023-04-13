import axios from '@/utils/request'

// 添加提问
export const addQuestionAPI = question => {
  return axios.post('/questions', question)
}

// 分页查询提问
export const getQuestionsByPageAPI = (id, currentPage, pageSize) => {
  return axios.get(`/questions/${id}/${currentPage}/${pageSize}`)
}