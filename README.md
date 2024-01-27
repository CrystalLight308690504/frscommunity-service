# FRSCommunityServer

# 介绍
这是本人的一个关于轮滑移动平台的博客后台服务器，实现从0到1
## 用户模块：使用用户角色设计方案设计用户权限，使用shiro作为权限控制，配合redis实现分布式权限控制，实现单点登录。开发用户相关操作后台接口接口。使用redis缓存用户暂时的信息。
## 博客模块：使用springdataJpa实现博客的CRUD等相关接口,使用FastDFC作为分布式文件存储，完成文件的上传、下载等接口，使用MQ缓存实现博客的流量削峰，使用rediss实现博客的分布式写锁。实现博客的定时发布功能
## 搜索模块：使用elasticsearch作为搜索缓存、加快搜索效率、使用canal框架监听mysql的日志，实现elasticsearch的更新。
## 管理模块：开发对博客的审核、显示撤销等后台接口、用户权限的授权和撤销等用户管理相关接口，

## 项目技术：
Spring、SpringdataJpa、SpringMvc、Springboot、SpringCloud、RabbitMQ 、Shiro、nginx、Mysql、redis、elasticsearch、Maven、FastDFC





#### 客户端地址
https://github.com/CrystalLight308690504/frscommunity-client
