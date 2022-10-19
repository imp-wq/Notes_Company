## Spring整合Druid

## Spring整合MyBatis

* MyBatis的SqlSessionFactory核心对象需要交给Spring管理。

### 导包

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

### JdbcConfig

用于整合JDBC和Druid的配置类。

* 创建JdbcConfig类用于配置JDBC，将配置字段放入jdbc.properties配置文件，在SpringConfig类中对该文件进行加载。

* 通过`@Bean`注解将dataSource注册为bean，供MyBatis使用。

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
  
      @Bean
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

### MybatisConfig

接下来编写mybatis的配置类MybatisConfig。

#### sqlSessionFactory

* 该方法通过将一个`SqlSessionFactoryBean`类型的对象注册为bean，来对mybatis的SqlSessionFactory对象进行配置。

  * 该类由mybatis-spring包提供，是mybatis用于在spring中进行配置的类。


  * 该类实现了FactoryBean接口，用于获取SqlSessionFactory对象。

    可以通过其getObject方法获取SqlSessionFactory对象。

  * 该方法通过形参注入形式，通过`@Bean`的自动装配获取到JdbcConfig类提供的dataSource bean对象，为Mybatis注入dataSource对象依赖，同时设置Mybatis数据库对应的java类（即POJO）。


```java
public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>,...
```

#### MapperScannerConfigurer

* 该方法用于设置MyBatis用到的mapper接口。

```java

public class MybatisConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // 设置typealias，设置MyBatis对应的java类，以及用于给mybatis对应的java类起别名的。
        sqlSessionFactoryBean.setTypeAliasesPackage("com.itniuma.domain");
        // 以形参形式传入dataSource，自动装配，注入jdbc dataSource对象，即driver, url, username, password。
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    // 创建Mapper对应的类。
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // 设置mapper接口所在的包
        mapperScannerConfigurer.setBasePackage("com.itniuma.dao");
        return mapperScannerConfigurer;
    }
}
```

### Service层

service层调用Dao层提供的方法

* 记得打@Service注解，供其他类通过getBean获取bean对象使用。
* 通过@Autowired自动装配dao层，需要提供dao层的setter。

```java
@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void showAllUser() {
        System.out.println(userDao.selectAll());
    }
}
```

### Dao层

即原先的mapper，定义Mybatis的接口

* 只需提供接口，以及通过annotation或xml提供sql语句，实现类由Mybatis提供。
* 无需@Repository注解，mybatis提供的bean对象已在其配置类中注册为bean。
* 其bean对象通过setter注入的形式供Service层使用，一般情况下其他类通过service层调用其方法。

```java
public interface UserDao {
    @Select("select * from tb_user;")
    List<User> selectAll();
}
```

## Spring整合JUnit

### 导包

* junit
* spring-test，spring用于整合junit的包

```xml
 <!--Spring整合junit-->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
<!--  Spring用于整合junit的包  -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>5.2.11.RELEASE</version>
</dependency>
```

### 编写测试类

* 通过注解指定类运行器和Spring配置类。
* 将被测试的类注册为测试类的属性，设置setter，通过setter注入自动装配获取。
* 编写测试方法。

#### @RunWith

指定测试类

#### @ContextConfiguration

通过classes指定Spring配置类

#### @Test

在测试方法上需要加上@Test注解

```java
// 设置类运行器
@RunWith(SpringJUnit4ClassRunner.class)
// 指定Spring配置类
@ContextConfiguration(classes = SpringConfig.class)
public class UserServiceTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testShowAll() {
        userService.showAllUser();
    }
}
```

