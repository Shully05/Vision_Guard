// utils/request.js
import axios from 'axios'
import { Message } from 'element-ui'

// 创建axios实例
const service = axios.create({
  // 开发环境下baseURL设置为空，生产环境根据实际情况设置
  baseURL: process.env.NODE_ENV === 'production' ? '' : '',
  timeout: 15000
})

// 请求拦截器 - 添加请求日志
// utils/request.js - 更通用的版本
service.interceptors.response.use(
  response => {
    const res = response.data
    
    // 如果是Result格式
    if (res && typeof res === 'object' && res.code !== undefined) {
      // 判断是否成功（支持多种成功码）
      const success = 
        res.code === 200 || res.code === '200' || 
        res.code === 0 || res.code === '0' ||
        res.code === 1000 || res.code === '1000' ||
        res.success === true || res.success === 'true'
      
      if (success) {
        return res.data || res
      } else {
        const errorMsg = res.message || res.msg || res.error || '请求失败'
        Message.error(errorMsg)
        return Promise.reject(new Error(errorMsg))
      }
    }
    
    // 不是Result格式，直接返回
    return res
  },
  error => {
    console.error('请求错误:', error)
    Message.error(error.message || '网络错误')
    return Promise.reject(error)
  }
)

export default service