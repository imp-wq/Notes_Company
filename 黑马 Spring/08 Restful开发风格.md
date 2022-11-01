## Rest风格

representational state transfer，表现形式状态转换。

* 传统风格：`/getById?id=1`,`/saveUser`，在url中指定请求进行的操作。

* rest风格：url中不包含请求的行为，用请求方式来区分请求行为：

  * GET POST PUT DELETE
  * 模块名一律使用复数
  * 传参时以**路径参数**的形式传参，`/user/123`，表示id为123的用户

  ![](.\noteimages\rest风格.PNG)

* 在Spring MVC中使用rest风格：

  * `@RequestMapping`注解中，通过method属性以enum类`RequestMethod.XXX`的形式限定请求方法。
  * 在url中用大括号指定参数名，在形参中通过`@PathVariable`注解获取路径参数。
  * 1个参数一般使用path variable，多个参数封装成json格式。

  ```java
  @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
  @ResponseBody
  public String saveUsers(@PathVariable Integer id) {
      System.out.println(id);
      return "{'msg':' " + id + " book save...'}";
  }
  ```

  * @ResponseBody注解也可以加在类上，对类中所有的方法生效。
  * @Controller和@ResponseBody注解可以简化为
  * @RequestMapping注解，可以使用`@GetMapping`,` @PostMapping`等注解替代直接限定请求方式.

  ```java
  // 使用resful开发
  
  // @Controller
  // @ResponseBody
  @RestController
  @RequestMapping("/book")
  public class BookController {
  
      // @RequestMapping(method = RequestMethod.POST)
      @GetMapping
      @PostMapping
      public String save(Book book) {
          System.out.println("book save...");
          return "save success!";
      }
  
      // @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
      @DeleteMapping("/{id}")
      public String delete(@PathVariable Integer id) {
          System.out.println("delete" + id);
          return "delete" + id;
      }
  }
  ```

## 静态资源放行

* 通过添加配置类`SpringMvcSupport`继承`WebMvcConfigurationSupport`，设置放行的静态资源。

  * 通过addResourceHandler方法设置放行哪些资源。
  * 通过addResourceLocations方法设置放心资源的路径。

  ```java
  public class SpringMvcSupport extends WebMvcConfigurationSupport {
      @Override
      protected void addResourceHandlers(ResourceHandlerRegistry registry) {
          registry.addResourceHandler("/pages/** ").addResourceLocations("/pages/");
          registry.addResourceHandler("/js/** ").addResourceLocations("/js/");
          registry.addResourceHandler("/css/** ").addResourceLocations("/css/");
          registry.addResourceHandler("/plugins/** ").addResourceLocations("/plugins/");
      }
  }
  ```

