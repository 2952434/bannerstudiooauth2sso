# BannerStudioOauth2SSO

#### 介绍
BannerStudio统一授权中心<br/>
开发框架 SpringBoot+Mybatis Plus+Spring Security<br/>
@author Ben 刘泽权 李君祥<br/>

#### 授权服务器
/oauth/authorize：授权端点<br/>
/oauth/token：令牌端点<br/>
/oauth/confirm_access：用户确认授权提交端点<br/>
/oauth/error：授权服务错误信息端点<br/>
/oauth/check_token：用于资源服务访问的令牌解析端点<br/>
/oauth/token_key：提供公有密匙的端点<br/>

#### 资源服务器
工作室内部成员数据<br/>
用户数据和用户角色<br/>

#### 使用说明
使用时配置好自己的授权id和授权地址访问<br/>
例如:https://oauth.bannerstudio.club/oauth/authorize?client_id=bannerstudioofficialwebsite&response_type=code&scope=all&redirect_uri=https://www.bannerstudio.club/callback<br/>

#### 项目部署地址 
http://82.157.145.197:8081/<br/>
