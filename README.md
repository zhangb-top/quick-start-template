# quick-start-template
快速构建一个简单的前后端分离项目，拒绝重复劳动，包含简单的Vue+Element代码和SpringBoot+MybatisPlus的基本CRUD代码，包含登录、注册、前后台页面数据展示以及增删改查。



## 项目技术栈

前端：HTML+CSS+Vue+VueRouter+Vuex+axios+Element

后端：SpringBoot+Spring+MybatisPlus+MySQL



## 项目实现功能

### 注册功能

前端对密码进行了简单的SHA256加密传输给后端，简单的保证了密码的安全。后端有验证码效果，防止机器注册

![register](./img/register.jpg)

### 登录功能

前端通过本地存储保存后端传输的token，每次请求添加请求头，把token传入

![login](./img/login.jpg)

### 首页

使用Element的Card组件简单展示基本数据，页面没有进行美化

![index](./img/index.jpg)

### 定制

允许用户传输数据给后端，后端通过后台来回复（这里没有实现）

![customize](./img/customize.jpg)

### 我的

用户资料展示，以及一些基本操作

![my](./img/my.jpg)

### 定制

这里属于业务模块，工具需要进行取舍

![question-detail](./img/question-detail.jpg)

### 详情

基本的点击展示详情数据的功能

![detail](./img/detail.jpg)

### 后台

这里只是实现了后台框架的基本搭建，以及简单的表格功能

![user](./img/user.jpg)
