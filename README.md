# 基于javaEE后台的微信小程序

本项目包含了多种基于 JavaScript JAVA SQL的程序设计。

## 主要文件包说明
 
`B` - 小程序客户端, `A` - JAVA服务端
task
* `B` image   资源文件
* `B` pages   页面文件
* `B` plugins 插件
* `B` utils   组件
tkSystem
* `A` controller   控制器
* `A` dao          数据逻辑
* `A` service      服务层
* `A` system       系统核心组件
* `A` test         测试模块
* `A` tool         工具类


## 项目环境

openJDK1.8.0_162 ，mysql5.6，tomcat8 , 微信公众平台小程序开发 ， eclipse/Oxygen

### 功能特点

*  **二维码扫描添加信息**
  * zxing扫描二维码和识别图片
*  **https请求加持**
  * Tomcat配置SSL证书
