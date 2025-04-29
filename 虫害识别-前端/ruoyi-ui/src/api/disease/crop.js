import request from '@/utils/request'

// 查询农作物管理列表
export function listCrop(query) {
  return request({
    url: '/disease/crop/list',
    method: 'get',
    params: query
  })
}

// 查询农作物管理详细
export function getCrop(cropId) {
  return request({
    url: '/disease/crop/' + cropId,
    method: 'get'
  })
}

// 新增农作物管理
export function addCrop(data) {
  return request({
    url: '/disease/crop',
    method: 'post',
    data: data
  })
}

// 修改农作物管理
export function updateCrop(data) {
  return request({
    url: '/disease/crop',
    method: 'put',
    data: data
  })
}

// 删除农作物管理
export function delCrop(cropId) {
  return request({
    url: '/disease/crop/' + cropId,
    method: 'delete'
  })
}
