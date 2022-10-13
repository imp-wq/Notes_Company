## MVC架构

* MVC是一种分层开发模式：
  * Model：业务模型
  * View：试图，页面展示
  * Controller：控制器，处理请求，调用模型和视图

* 三层架构：MVC是一种开发模式，而三层架构是更具体的软件开发架构。
  * 表现层：接收请求，封装数据，调用业务逻辑层，响应数据。
    * web/controller
    * Srping MVC
  * 业务逻辑层：对业务逻辑进行封装，组合数据访问层中的基本功能，形成业务逻辑。
    * service
    * Spring
  * 数据访问层：持久层，对数据库进行CRUD基本操作。
    * dao/mapper, Data Access Object
    * Mybatis

##  异步 AJAX

* 异步指，browser发起ajax请求时，无需等待服务端。

## JSON

Javascript Object Notation，网络传输中的数据载体。

* JSON与java对象的相互转换。

jackson库

### FastJson库

阿里巴巴的，用于将java对象转换为json

```java
// 1.java对象转化为json
String jsonString = JSON.toJSONString(user);
System.out.println(jsonString);

// 2.JSON字符串转java对象
User user1 = JSON.parseObject("{\"id\":1,\"password\":\"1234\",\"username\":\"zhangsan\"}", User.class);
System.out.println(user1);
```

