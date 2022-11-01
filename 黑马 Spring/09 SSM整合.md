# SSM整合

## 基本配置

### 目录结构

### Config-Spring整合MyBatis

#### SpringConfig

* 设置spring扫描bean的包，dao层和service层
* 导入JdbcConfig和MyBatisConfig配置类
* 读取jdbc.properties文件
* 开启jdbc事务，`@EnableTransactionManagement`

#### JdbcConfig

* 根据jdbc.properties用的信息获取数据源。

* 设置jdbc transaction manager

  ```java
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
      DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
      transactionManager.setDataSource(dataSource);
      return transactionManager;
  }
  ```

  

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

### Service Interface

* `@Transactional`,开启事务。
* @Service注解只能加载实现类上，加在接口上会报错。

### Controller

* @RestController，@RequestMapping，设置bean和，处理请求和响应。

### Dao Interface

* 只有接口，没有实现类，通过mabatis注解开发。

## 测试

* 一般在2个环节需要进行测试：

  1. 在业务层接口开发完后，使用junit进行测试。
  2. 表现层接口开发完后，使用postman进行表现层接口测试。


