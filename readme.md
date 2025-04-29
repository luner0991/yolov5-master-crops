# 农作物病虫害识别系统并整合 YOLOv5

> 系统架构

该系统采用前后端分离架构，前端和后端通过 HTTP 接口进行通信。系统主要由以下几个模块组成：

- 用户管理模块：用于管理系统用户，处理用户登录、注册和权限管理。
- 农作物选择模块：允许用户选择需要检测的农作物种类（如小麦、玉米、大豆、棉花等）。
- 病虫害识别模块：用户上传农作物病虫害图像，系统通过 YOLOv5 模型进行检测并返回识别结果。
- 病虫害管理模块：记录和管理所有检测的病虫害类型，包括相关的分类信息。
- 历史记录模块：记录每次识别的图片及其识别结果，用户可以进行查询、筛选、导出等操作。
  

> 环境要求

- 操作系统：Linux（CentOS 7/Ubuntu 18+）
- 后端框架：Spring Boot + Ruoyi（Java）
- 前端框架：Vue.js
- 图像识别工具：YOLOv5（Python + PyTorch）
- Web 服务器：Nginx（用于反向代理）![image-20250429105850593](C:\Users\86153\AppData\Roaming\Typora\typora-user-images\image-20250429105850593.png)



> 安装依赖

- 安装 Java 和 Maven（后端框架）

首先，确保你的服务器已经安装了 Java 和 Maven，用于构建 Spring Boot 项目。

对于 Ubuntu：

```
sudo apt update
sudo apt install openjdk-11-jdk maven
```

对于 CentOS：

```
sudo yum install java-11-openjdk-devel maven
```

- 安装 Python 和相关依赖（YOLOv5 识别）

参考我的博文~

https://blog.csdn.net/qq_73704268/article/details/146281684