# mySSM
## SSM怎合的后台订单项目
#### *本项目采用maven作为版本管理工具，tomcat作为服务器，mysql作为数据端，jsp来作页面展示，eclipase作为开发集成环境，SSM等整合技术开发*
- 把mybatis怎合到spring中来进行管理
  - 这个项目涉及了9张关联表
  - 用mybatis的逆向生成工程生成的sql语句XML已经能满足需要的业务
  - 常规的数据库连接池配置
- 利用了图片的格式转换工具使图片利于展示
- 多条数据采用了自写分页工具
  - 重构的时候把分页升级成了pagehelper分页工具
- 前端发送动态请求，后端springMVC接收之后业务处理之后输出到jsp，渲染之后输出到前端展示
- 使用log4j进行开发的日志信息查看

- 整个业务流程采用javaBean->Dao->service->conroller的顺序进行开发

#### java源代码包结构
  - pojo 实体类
  - mapper Mapper类
  - interceptor 拦截器
  - controller 控制层
  - service Service层
  - test 测试类
  - util 工具类
  - comparator 比较类
#### web目录
  - css css文件
  - img 图片资源
  - js js文件
  - admin 后台管理用到的jsp文件
  - fore 前台展示用到的jsp文件
  - include 被包含的jsp文件
