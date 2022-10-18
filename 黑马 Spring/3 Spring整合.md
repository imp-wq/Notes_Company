## Spring整合Druid

## Spring整合MyBatis

* MyBatis的SqlSessionFactory核心对象需要交给Spring管理。

* pom.xml中添加配置：

  ```xml
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.6</version>
  </dependency>
  
  <!--  新增配置  -->
  <!--spring操作jdbc的包-->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.2.10.RELEASE</version>
  </dependency>
  <!--mybatis整合到spring的包，版本需要与mybatis版本匹配-->
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.3.0</version>
  </dependency>
  </dependencies>
  ```

#### 整合jdbc

* 创建JdbcConfig类用于配置JDBC，将字段写入jdbc.properties配置文件。

  ```java
  
  public class JdbcConfig {
      @Value("${jdbc.driver}")
      private String driver;
      @Value("${jdbc.url}")
      private String url;
      @Value("${jdbc.username}")
      private String username;
      @Value("${jdbc.password}")
      private String password;
  
      public DataSource dataSource() {
          DruidDataSource dataSource = new DruidDataSource();
          dataSource.setDriverClassName(driver);
          dataSource.setUrl(url);
          dataSource.setUsername(username);
          dataSource.setPassword(password);
          return dataSource;
      }
  }
  ```

* 在SpringConfig类中，引入配置文件jdbc.properties，和JdbcConfig

  ```java
  @Configuration
  @ComponentScan("com.itniuma")
  @Import(JdbcConfig.class)
  @PropertySource("jdbc.properties")
  public class SpringConfig {}
  ```

#### MybatisConfig

接下来编写mybatis的配置类MybatisConfig。

* 编写方法，通过返回一个`SqlSessionFactoryBean`类型的对象，来对mybatis的SqlSessionFactory对象进行配置。

  * 该类由mybatis-spring包提供，是mybatis用于在spring中进行配置的类。

  * 该类实现了FactoryBean接口，用于获取SqlSessionFactory对象。

    可以通过其getObject方法获取SqlSessionFactory对象。

  ```java
  public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>,...
  ```

  

