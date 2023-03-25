# FRSCommunityServer

#### 介绍
这是本人的一个关于轮滑移动平台的博客后台服务器
#### 软件架构
使用微服务分布方式设计后台系统、每个微服务使用MVC架构设计
Model模块：存放共同使用的pojo类
Common模块：存放共同的类。例如shir使用的baseRealm

#### 使用技术
1.	使用Springboot、SpringCloud、springdataJpa搭建后台服务器
2.	使用Eureka作为服务注册中心，管理注册下的微服务 Feign 负责服务之间的调用
3.	使用Shiro、redis实现单点登录和实现权限控制，保证系统访问安全。
4.	使用redis作为缓存数据库，将用户信息、博客等相关信息缓存存入Redis，减轻压力
5.	使用nginx作为反向服务器代理
6.	使用docker部署后台服务


#### 客户端地址
https://github.com/CrystalLight308690504/frscommunity-client