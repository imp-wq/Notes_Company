## Spring注解开发

[Core Technologies (spring.io)](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#spring-core)

### @Component

* 通过在配置的bean上增加`@Component("bean的id")`的方式，替代原先xml文件中的bean节点。

* 也可以直接配置`@Component`，然后通过类型查找。

* 需要在xml文件中，使用context命名空间，指定扫描的包。

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context
          https://www.springframework.org/schema/context/spring-context.xsd">
  
      <!--指定扫描的包，一般直接扫描所有包-->
      <context:component-scan base-package="com.itniuma"/>
  </beans>
  ```

* Spring为了更好的语义化，提供了`@Component`的3个衍生注解：

  * @Service：用于业务层
  * @Repository：用于数据层
  * @Controller：用于表现层

  用法与`@Component`完全一致。

  ```java
  @Configuration
  @ComponentScan("com.itniuma")
  public class SpringConfig {
  }
  ```

  

* :exclamation:注意，通过这种方式配置spring，版本不能太高，jdk 17版本会报错。

### 纯注解开发模式

* 创建配置类SpringConfig，替代xml配置文件：

  * `@Configuration`：用于指定配置类。

  * `@ComponentScan("com.itniuma")`：用于指定扫描的包，扫描所有bean所在的目录，替代xml中的。

    多个包使用数组格式，该注解只能添加一次。

    ```xml
    <context:component-scan base-package="com.itniuma"/>
    ```

* java代码中通过`AnnotationConfigApplicationContext`指定配置类SpringConfig来创建容器。

  ```java
  ApplicationContext container = new AnnotationConfigApplicationContext(SpringConfig.class);
  
  BookDao bookDao = container.getBean(BookDao.class);
  
  bookDao.save();
  ```

### bean的配置项

#### @Scope

* 替代scope节点，`@Scope("singleton")`为单例，`@Scope("prototype")`为非单例。

#### 生命周期

* 用为方法添加注解的方式，替代xml配置文件，或实现接口。

* @PostConstruct：在构造后调用

* @PreDestroy：在销毁前调用

  ```java
  @PostConstruct
  public void init() {
      System.out.println("init method is running...");
  }
  
  @PreDestroy
  public void destroy() {
      System.out.println("destroy method is running...");
  }
  ```

* troubleshooting：这2个注解需要引入javax.annotation-api后才能调用。

  ```xml
   <dependency>
       <groupId>javax.annotation</groupId>
       <artifactId>javax.annotation-api</artifactId>
       <version>1.3.2</version>
  </dependency>
  ```

### 自动装配@Autowired

通过`@Autowired`注解来为字段进行自动装配。

* 注解为了加快开发的，因此没有提供自动装配以外的方式。

* `@Autowired`注解默认按类型装配，官方推荐加在setter方法上，不推荐直接加在字段上。

  ```java
  @Service
  public class BookServiceImpl implements BookService {
      private BookDao bookDao;
  
      @Autowired
      public void setBookDao(BookDao bookDao) {
          this.bookDao = bookDao;
      }
  }
  ```

* 如果有类型重复的情况，可以通过与`@Autowired`**同时**使用`@Qualifier("bean的id")`注解，指定id注入bean。

  ```java
  @Autowired
  @Qualifier("bookDao")
  public void setBookDao(BookDao bookDao) {
      this.bookDao = bookDao;
  }
  ```

### 简单数据类型@Value

* 通过`@Value`注解，为简单数据类型注入值。

  ```java
  @Value("kobe")
  private String name;
  @Value("33")
  private int age;
  ```

* 可以通过`@PropertySource("文件路径")`注解，加载properties配置文件中的字符串到对应的bean，再在@Value注解中通过`${变量名}`的形式进行插值。

  也可以以数组的形式加载多个properties文件。

  注解无法使用通配符`*`。

  ```java
  
  @Repository("jdbc")
  @PropertySource("jdbc.properties")
  public class Jdbc {
      @Value("${jdbc.username}")
      private String username;
      @Value("${jdbc.password}")
      private String password;
      @Value("${jdbc.driver}")
      private String driver;
      @Value("${jdbc.url}")
      private String url;
  }
  ```

### 第三方bean的管理 @Bean

[Bean (Spring Framework 5.3.23 API)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Bean.html)

>## Annotation Type Bean
>
>Indicates that a method produces a bean to be managed by the Spring container.

* 第三方bean的管理，创建独立的配置类，定义一个方法，加上`@Bean`注解，返回第三方bean对象，在该方法中set该bean对象的字段。

* 在SpringConfig配置类中，加载配置类：

  * 使用`@Import({config1.class,config2.class})`注解以Class对象的形式导入配置类，多个配置类以数组的形式，推荐。

  * 为配置类增加`@Configuration`注解，再使用`@ComponentScan("com.itniuma.config")`注解，扫描配置类所在的包。

    不易看出导入了哪些配置类，不推荐。

* 对于第三方bean所需的引用类型注入依赖：
  * 简单类型：直接以成员变量的形式注入。
  * 引用类型：将依赖注册为bean，同时在`@Bean`方法中以形参的形式声明，`@Bean`注解在运行时，会按类型进行自动装配。

