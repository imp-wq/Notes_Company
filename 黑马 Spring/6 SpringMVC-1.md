# Spring MVC

* Spring MVC于servlet技术功能等同，均属于表现层（web层）开发技术。

##  基本使用

### 坐标

* 需要导入servlet，作为依赖。
* Spring Framework已作为spring-webmvc的依赖被导入，无需手动导入。

```xml
 <!--spring mvc与serlvet坐标-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.10.RELEASE</version>
        </dependency>
```

### Controller

* 通过`@Controller`注解注册为Spring的bean。

* @RequestMapping("/url")：设置匹配的url。

* @ResponseBody：设置将返回类型作为响应内容。

  ```java
  @Controller
  public class UserController {
      @RequestMapping("/save")
      @ResponseBody
      public String save() {
          System.out.println("user save...");
          return "{'msg':'hello spring mvc!'}";
      }
  }
  ```

### SpringMvcConfig

Spring MVC的配置类。

```java
@Configuration
@ComponentScan("com.itniuma.controller")
public class SpringMvcConfig {}
```

### ServletContainerInitConfig

Tomcat servlet容器启动的配置类，通过该类加载Spring的配置。

* 需要继承`AbstractDispatcherServletInitializer`类，实现其方法。
  * WebApplicationContext：加载SpringMVC容器配置。
    * 加载Spring mvc的容器
    * register(Spring mvc配置类)，加载spring mvc配置类
  * getServletMappings：设置哪些请求归属SpringMVC处理。
    * 通过返回值进行设置
  * createRootApplicationContext：加载Spring容器配置。

```java

public class ServletContainerInitConfig extends AbstractDispatcherServletInitializer {
    //加载SpringMVC容器配置
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        // 加载Spring MVC容器。
        AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
        // 加载SpringMVC配置类
        container.register(SpringMvcConfig.class);
        return container;
    }

    //设置哪些请求归属SpringMVC处理
    @Override
    protected String[] getServletMappings() {
        // 设置所有请求归Spring mvc处理
        return new String[]{"/"};
    }

    //加载Spring容器配置
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        // 暂时不用管
        return null;
    }
}
```

## Spring MVC工作流程

### 启动流程

1. 服务器启动，加载Servlet容器配置类ServletContainerInitConfig，初始化web容器。
2. 执行ServletContainerInitConfig.createServletApplicationContext方法，创建容器对象，容器对象类型为WebApplicationContext，加载到ServletContext中。
3.  执行container.register(SpringMvcConfig.class)，加载SpringMvcConfig。
4. 根据SpringMvcConfig的`@ComponentScan("com.itniuma.controller")`注解，扫描包，加载具有`@Controller`注解对应的bean。
5. 收集Controller中通过`@RequestMapping("/url")`注解配置的映射，将所有映射放在一起统一管理。
6. 执行ServletContainerInitConfig.getServletMappings方法，定义所有请求都归Spring mvc处理。

### 请求流程

1. web容器拦截到请求。
2. web容器发现，已配置所有请求归spring mvc处理，将请求交给spring mvc。
3. 解析请求路径。
4. 由映射关系，得到请求路径对应的处理方法。
5. 执行处理方法。
6. 检测到`@ResponseBody`注解，将处理方法的返回值作为响应体返回。

## bean加载控制

* spring mvc和spring应当分别只加载各自控制的bean：
  * spring mvc：controller
  * spring:
    * Service层bean
    * 功能bean，DataSource等

* spring mvc加载时，设置只扫描controller包；spring加载时，应排除掉controller包。

  两种解决方式：

  * spring扫描也设置精准范围，具体到service, dao等多个具体的包，以数组形式。

    ```java
    @ComponentScan({"com.itniuma.service","com.itniuma.dao"})
    ```

    * dao层的bean由mybatis提供，因此也可以不将dao层加入spring扫描的范围。
    * 但如果不使用mybatis的mapper代理开发方式，则必须扫描dao包，因此不扫描dao包通用性比较差。

  * spring扫描整个项目，排除掉controller包中的bean。

    * 通过@ComponentScan注解的excludeFilters属性配置过滤规则。

    * 指定过滤规则为按注解过滤，过滤掉所有带`@Controller`注解的bean。

    * 但由于SpringMvcConfig配置类具有`@Configuration`注解，因此仍会加载controller。

      解决该问题，可以将SpringMvcConfig配置类单独放到一个包中。

    * 比较麻烦，因此实际还是第一种方式用的比较多。

    ```java
    @ComponentScan(value = "com.itniuma", excludeFilters = @ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            classes = Controller.class
    ))
    ```

## Spring mvc加载spring

* 通过createRootApplicationContext方法加载Spring。

  ```java
  
  public class ServletContainerInitConfig extends AbstractDispatcherServletInitializer {
      //加载SpringMVC容器配置
      @Override
      protected WebApplicationContext createServletApplicationContext() {
          // 加载Spring MVC容器。
          AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
          // 加载SpringMVC配置类
          container.register(SpringMvcConfig.class);
          return container;
      }
  
      //设置哪些请求归属SpringMVC处理
      @Override
      protected String[] getServletMappings() {
          // 设置所有请求归Spring mvc处理
          return new String[]{"/"};
      }
  
      //加载Spring容器配置
      //代码逻辑与加载spring mvc相同，指定的配置类不同。
      @Override
      protected WebApplicationContext createRootApplicationContext() {
          AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
          container.register(SpringConfig.class);
          return container;
      }
  }
  
  ```

* 在加载spring mvc和spring容器时，因为只有指定的配置类不一样，因此可以通过继承`AbstractAnnotationConfigDispatcherServletInitializer`类，只传入相应配置类的Class对象，简化开发。

  ```java
  public class ServletContainerInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
  
      @Override
      protected Class<?>[] getRootConfigClasses() {
          return new Class[]{SpringConfig.class};
      }
  
      @Override
      protected Class<?>[] getServletConfigClasses() {
          return new Class[]{SpringMvcConfig.class};
      }
  
      @Override
      protected String[] getServletMappings() {
          return new String[]{"/"};
      }
  }
  ```

  