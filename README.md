![Licence](https://img.shields.io/badge/licence-none-green.svg)

## 下载地址
https://gitee.com/fdz/mlink_basic_framework.git

## 简介
Spring Boot API Project Seed 是一个基于Spring Boot & MyBatis的脚手架，用于快速构建中小型API、RESTful API项目，该脚手架已经有过多个真实项目的实践，稳定、简单、快速，使我们摆脱那些重复劳动，专注于业务代码的编写，减少加班。


## 特征&提供
- 统一响应结果封装及生成工具
- 统一异常处理
- 简单的接口签名认证
- 常用基础方法抽象封装
- 使用Druid Spring Boot Starter 集成Druid数据库连接池与监控
- 使用FastJsonHttpMessageConverter，提高JSON序列化速度
- 集成MyBatis、通用Mapper插件、PageHelper分页插件，实现单表业务零SQL
- 提供代码生成器根据表名生成对应的 Controller、Service 、Mapper、MapperXML、entity、等基础代码 Mapper、MapperXML统一放到Mapper文件夹下，省去ServiceImpl文件。
- Controller模板默认提供POST和RESTful两套，根据需求在```CodeGenerator.genCode(String tableName,String describe)```方法中自己选择，默认使用POST模板。代码模板可根据实际项目的需求来扩展，由于每个公司业务都不太一样，所以只提供了一些比较基础、通用的模板，主要是提供一个思路来减少重复代码的编写，我在实际项目的使用中，其实根据公司业务的抽象编写了大量的模板。另外，使用模板也有助于保持团队代码风格的统一
- src/test/CodeGenerator.java  配置好需要生成的表信息，运行它生成 Controller、Service 、Mapper、MapperXML、entity代码。
- 集成swagger 自动生成后的代码自动带上了swagger api。对mybatis-generator-core进行了改造，entity 生成也会自带swagger注释。启动后输入http://localhost:8080/swagger-ui.html.查看api文档

## 快速开始
1. 克隆项目 <br>
2. idea 直接打开下载的源码，配置springboot 启动<br>
3. 配置 application.properties 数据库配置 <br>
   spring.datasource.url       ：数据库url<br>
   spring.datasource.username  ：数据库用户名<br>
   spring.datasource.password  ：数据库密码<br>
   spring.datasource.driver-class-name=com.mysql.jdbc.Driver<br>

4. 配置代码生成工具 src/test/CodeGenerator.java。JDBC_URL：数据库url  JDBC_USERNAME ：数据库用户名  JDBC_PASSWORD ：数据库密码。 配置包名：BASE_PACKAGE ="com.mlink.base"<br>
5. 输入表名，运行```CodeGenerator.main()```方法，生成基础代码（可能需要刷新项目目录才会出来）<br>
6. 根据业务在基础代码上进行扩展<br>
7. 对开发环境配置文件```application-dev.properties```进行配置，启动项目，Have Fun！<br>
 
## 开发建议
- 表名，多个单词使用下划线拼接mlink_user, 注意：表名是一个单词用小写如 user
- 类名，建议使用大写开头，MlinkUser
- 建议业务失败直接使用```ServiceException("message")```抛出，由统一异常处理器来封装业务失败的响应结果，比如```throw new ServiceException("该手机号已被注册")```，会直接被封装为```{"code":400,"message":"该手机号已被注册"}```返回，无需自己处理，尽情抛出
- 需要工具类的话建议先从```apache-commons-*```和```guava```中找，实在没有再造轮子或引入类库，尽量精简项目
- 开发规范建议遵循阿里巴巴Java开发手册（[最新版下载](https://github.com/lihengming/java-codes/blob/master/shared-resources/%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8CV1.3.0.pdf))
- 建议在公司内部使用[ShowDoc](https://github.com/star7th/showdoc)、[SpringFox-Swagger2](https://github.com/springfox/springfox) 、[RAP](https://github.com/thx/RAP)等开源项目来编写、管理API文档

## 部署

- maven打包后会生成 com.mlink.base.zip 和 com.mlink.base.jar  传到服务器解压zip 运行start 即可运行。下次更新只需要更新com.mlink.base.jar即可。
- 如修改包名请记得修改
    <groupId>com.mlink.video</groupId>
    <artifactId>com.mlink.video</artifactId>
    <version>1.0-SNAPSHOT</version>
  否则打包出错
## 技术选型&文档
- Spring Boot（[查看Spring Boot学习&使用指南](http://www.jianshu.com/p/1a9fd8936bd8)）
- Spring Boot MyBatis（[查看官方中文文档](http://www.mybatis.org/mybatis-3/zh/index.html)）
- MyBatisb通用Mapper插件（[查看官方中文文档](https://mapperhelper.github.io/docs/)）
- MyBatis PageHelper分页插件（[查看官方中文文档](https://pagehelper.github.io/)）
- Druid Spring Boot Starter（[查看官方中文文档](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter/)）
- Fastjson（[查看官方中文文档](https://github.com/Alibaba/fastjson/wiki/%E9%A6%96%E9%A1%B5)）
- logback 
- swagger
- 其他略

## License
无，纯粹开源分享，感谢大家 [Star](https://github.com/lihengming/spring-boot-api-project-seed/stargazers) & [Fork](https://github.com/lihengming/spring-boot-api-project-seed/network/members) 的支持。
