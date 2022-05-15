# Shiro
### 1、功能简介：分为两个项目
- shiro:针对于shiro的入门学习，基础的语法和基本的流程
- springboot_jsp_shiro:通过JSP+SpringBoot+mybatis+redis完成shiro的融合学习
### 2、shiro的基本使用
### 3、文件说明
>SQL文件为搭建对应的关联关系
>>用户 admin 具有 admin的角色，具有 对于 user，order的所有权限
>>
>>用户 zhangsan 具有 user的角色，没有权限，只能访问公共资源
>>
>>用户 usermanager 具有 user_manager的角色，具有 对于 user的所有权限
>>
>>用户 ordermanager 具有 order_manager的角色，具有 对于 order的所有权限
>>
>>用户 addinfomanager 具有 addinfo_manager的角色，具有 对于 user,order 的添加权限

### 
