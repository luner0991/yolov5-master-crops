import request from '@/utils/request'

// 查询识别记录列表
export function listResult(query) {
  return request({
    url: '/result/result/list',
    method: 'get',
    params: query
  })
}

// 查询识别记录详细
export function getResult(resultId) {
  return request({
    url: '/result/result/' + resultId,
    method: 'get'
  })
}

// 新增识别记录
export function addResult(data) {
  return request({
    url: '/result/result',
    method: 'post',
    data: data
  })
}

// 修改识别记录
export function updateResult(data) {
  return request({
    url: '/result/result',
    method: 'put',
    data: data
  })
}

// 删除识别记录
export function delResult(resultId) {
  return request({
    url: '/result/result/' + resultId,
    method: 'delete'
  })
}
