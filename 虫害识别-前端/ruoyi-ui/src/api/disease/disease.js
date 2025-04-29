import request from '@/utils/request'

// 查询病虫害管理列表
export function listDisease(query) {
  return request({
    url: '/disease/disease/list',
    method: 'get',
    params: query
  })
}

// 查询病虫害管理详细
export function getDisease(diseaseId) {
  return request({
    url: '/disease/disease/' + diseaseId,
    method: 'get'
  })
}

// 新增病虫害管理
export function addDisease(data) {
  return request({
    url: '/disease/disease',
    method: 'post',
    data: data
  })
}

// 修改病虫害管理
export function updateDisease(data) {
  return request({
    url: '/disease/disease',
    method: 'put',
    data: data
  })
}

// 删除病虫害管理
export function delDisease(diseaseId) {
  return request({
    url: '/disease/disease/' + diseaseId,
    method: 'delete'
  })
}
