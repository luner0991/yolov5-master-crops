import request from '@/utils/request'

// import axios from 'axios';
// axios.defaults.timeout = 60000; // 设置全局的请求超时时间为 60 秒

import { Loading } from 'element-ui';

export function getList(data) {

  return request({
    url: '/pest_identify/pest/list',
    method: 'get',
    params: data
  })
}

export function cropList() {
  return request({
    url: '/pest_identify/pest/cropList',
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: '/pest_identify/pest/add',
    method: 'post',
    data,
    timeout: 60000  // 设置请求超时时间为 60 秒
  })
}

export function chart() {
  return request({
    url: '/pest_identify/pest/chart',
    method: 'get',
  })
}
