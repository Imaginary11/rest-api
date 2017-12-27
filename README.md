（项目重构、之前的工程DELETE了,近期会补充完整）
--- 
# API规范
REST 风格,格式 {模块}/{版本}/{资源} 如

| 接口名称      | 定义       | 协议  |
| ------------- |:-------------:| -----:|
| 查询用户信息列表      |  /account/v1/user | GET
| 查询用户详情      |  /account/v1/user/{user_id} | GET| 
| 查询用户地址信息      |  /account/v1/user/{user_id}/address | GET| 
| 新增用户信息 | /account/v1/user    |    POST |
| 修改用户信息 | /account/v1/user/{user_id}    |    PUT |
| 删除用户信息 | /account/v1/user/{user_id}    |    DELETE |





# 概述
  基于SpringBoot的web框架，整个了常用的组件如mysql、redis、mongodb、elasticsearch、rabbitmq、quartz、freemaker、jsonrpc、ehcache等，提供基于centos6.x的自动化安装部署脚本、服务健康检查脚本，脚本实现技术为Makefile、shell、python2.x。
  
# 基础环境 
- Springboot 1.59

- JDK 9

- WEB容器 Undertow

# 组件支持 
- Cache
  - Redis
  - Ehcache
- NC
  - Redis
  - RabbitMQ    
- DB
  - Mybatis
  - MongoDB
- Template
  - Freemaker
- Schedule Job
  - Quartz
- Some Others
  - Interceptor
  - Logback
  - Auto Install And Deploy Script
  - And So on
  - Keep updating ... 
 
