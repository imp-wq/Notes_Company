## 请求路径前缀

* 为避免模块间出现路径冲突，实际访问路径应设置成`/模块名/访问路径`的形式。

* 可以将`@RequestMapping("/模块访问前缀")`加到整个Controller类上，从而为整个类设置访问路径前缀。

  ```java
  @Controller
  @RequestMapping("/book")
  public class BookController {
  
      @RequestMapping("/save")
      @ResponseBody
      public String save() {
          return "{'msg':'book save...'}";
      }
  
      @RequestMapping("/delete")
      @ResponseBody
      public String delete() {
          return "{'msg':'book delete...'}";
      }
  }
  ```




---

### date型参数

* 日期时间类型的参数可以直接使用Date对象进行接收：

  * `yyyy/mm/dd`：斜线分隔，默认可以直接转换为Date对象。

  * 使用其他格式的日期需要使用`@DateTimeFormat`注解，通过其pattern属性指定字符串的格式。

    `@DateTimeFormat(pattern = "yyyy-MM-dd")`

  * 注意，`@DateTimeFormat`注解应该也是通过过滤器对参数进行转换，因此使用时需在SprintMvcConfig配置类中加上`@EnableWebMvc`注解，开启根据类型匹配对应的类型转换器。

  * 通过Converter接口的实现类，将字符串参数转化为Date对象。

```java
@RequestMapping("/dateParams")
@ResponseBody
public String dateParams(Date date,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1) {
    System.out.println(date);
    System.out.println(date1);
    return "{'msg':'date: " + date +
        "\tdate1: " + date1 + "'}";

}
```

## 响应

* jsp：响应页面

* 响应数据：

  * json数据：通过加上`@ResponseBody`注解，将处理函数的返回类型设置为pojo类型，添加jackson坐标，自动将pojo类型转换为json。

    ```xml
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.9.0</version>
    </dependency>
    ```
  
    ```java
    @RequestMapping("/pojoJson")
    @ResponseBody
    public Account pojoJson() {
        Account account = new Account();
        account.setName("zhangsan");
        account.setId(123123);
        return account;
    }
    ```
  
  * json数组形式：
  
    ```java
    @RequestMapping("/pojoList")
    @ResponseBody
    public List<Account> pojoList() {
        Account account1 = new Account();
        account1.setName("zhangsan");
        account1.setId(1);
        Account account2 = new Account();
        account2.setName("lisi");
        account2.setId(2);
        return Arrays.asList(account1, account2);
    }
    ```

### @ResponseBody注解

* 设置当前控制器方法的返回值作为响应体：
  * 返回类型为String：直接作为响应体。
  * 返回类型为pojo类型、`List<pojo类型>`：转换成json字符串
* 通过HttpMessageConverter接口的实现类进行转换。