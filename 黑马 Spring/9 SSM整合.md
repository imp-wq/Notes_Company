# SSM整合

## 基本配置

### 目录结构

### Config-Spring整合MyBatis

#### SpringConfig

* 设置spring扫描bean的包，dao层和service层
* 导入JdbcConfig和MyBatisConfig配置类
* 读取jdbc.properties文件

#### JdbcConfig

* 根据jdbc.properties用的信息获取数据源。

#### MyBatisConfig

* sqlSessionFactorybean
  * 注入jdbc的data source
  * 扫描domain包，获取与数据库中匹配的pojo类型
* mapperScannerConfigurer
  * 设置扫描的mapper包

### Config-Spring整合Spring MVC

#### ServletConfig

继承AbstractAnnotationConfigDispatcherServletInitializer，实现abstract方法

* 整合SpringConfig
* 整合SpringMvcConfig
* 设置拦截所有请求
* 设置过滤器，比如处理中文字符的过滤器。
* 通过该方法造出的Spring MVC容器和Spring容器是2个容器，Spring MVC容器可以访问Spring容器，Spring容器不能访问Spring MVC容器，因为Spring是父容器。

#### SpringMvcConfig

* 设置扫描controller包
* @EnableWebMvc，设置开启一些对请求和响应进行转换的功能。