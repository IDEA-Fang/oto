# readme
## 一堆bug的SSM大学校园O2O

项目设计
##### 功能设计
![功能设计](https://github.com/IDEA-Fang/oto/blob/master/showimg/oto-project.png)

##### 角色划分：



##### 功能分析：



### 技术框架
1. SSM SpringMvc+Spring+Mybatis
2. 持久层框架Mybatis
3. 实体类使用了lombok插件，使代码简洁
4. java2json序列化工具使用FastJson
5. 验证码工具Google的Kapacha
6. 前端Jquery
7. 图片开源工具 Thumbnailator
8. 前端UI库 SUI Mobile

### 技术点
1. 使用DTO实现实体类和数据传输对象的转换
2. 使用统一的Enums来规范参数
3. 自定义错误类型
4. 使用VO定义输出对象
5. 实现表单转换成DTO功能
6. 自定义HttpRequest转换工具
7. 自定义处理上传的照片，修改名称，加水印，储存在本地的目录（根据Linux或者Windows选择）
   照片路径储存在表中
8. 根据微信的接口定义的要求，获取微信用户的信息，并在微信端访问
9. 阿里云部署，tomcat+mysql，在微信端访问

![项目文件路径](https://github.com/IDEA-Fang/oto/blob/master/showimg/oto-mulu.png)

后端开发，按照每个功能的顺序开发，想用前后端分离，但是在前端js部分卡主

开发进度：
1. 店铺注册部分，实现文件上传处理，店铺底层处理的开发，实现验证码
2. 商品类别开发
3. 微信接口的开发认证完成
4. 商品的开发
5. 所有的前端页面层没有开发
6. 后续开发
 >> * 前端展示
 >> * 支付功能
 >> * 未开发完的部分
 >> * Mysql主从复制读写分离，FastDFS图片服务器，Redis缓存
 >> * 产品的部署，tomcat部署，Nginx做负载均衡


* 底层DateObject
> 存放实体对象
* Mapper
> Mybatis处理数据库
* DAO
> 进行查询的接口
* Service
> 处理业务的逻辑
* Controller
> 转发输出到页面
* Html+JS
> 界面

2018.9.25整理
---------------

