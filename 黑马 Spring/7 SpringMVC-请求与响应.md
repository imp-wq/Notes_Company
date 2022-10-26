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

## 请求传参

### GET

* get请求可以直接以方法形参的形式得到。

* 形参名称与地址栏中查询字符串的名称相同。

  ```java
  @RequestMapping("/commonParams")
  @ResponseBody
  public String commmonParams(String username, String password) {
      // 普通参数，直接以形参的形式得到。
      System.out.println("username: " + username + "\tpassword: " + password);
      return "{'msg':'hello ," + username + "'}";
  }
  ```

### POST

* MVC不区分get和post请求，可以用相同方式获得post的参数。
* 编码方式使用`x-www-form-urlendocded`，形参名称与表单中的key相同。

### @RequestParam

* 默认情况下要求形参名与请求中参数名匹配。
* 在请求参数与参数名不匹配时，可以通过为形参加上`@RequestParams("请求参数名")`注解来匹配请求中的参数。
* 参数：
  * required
  * defaultValue

```java
@RequestMapping("/commonParams")
@ResponseBody
public String commmonParams(@RequestParam("name") String username, String password) {
    // 普通参数，直接以形参的形式得到。
    System.out.println("username: " + username + "\tpassword: " + password);
    return "{'msg':'hello ," + username + "'}";
}
```

### 使用过滤器

* 可以使用过滤器解决中文乱码问题，通过过滤器对中文字符进行处理。
* 在配置类ServletInitConfig中，通过getServletFilters方法设置过滤器。
* 通过CharacterEncodingFilter过滤器，设置字符集，并对请求中的中文字符进行转换。
* 注意，该过滤器只能过滤post请求体中的中文字符，无法对get查询字符串中的中文字符进行处理。

```java
@Override
protected Filter[] getServletFilters() {
    // 设置对中文字符过滤的过滤器
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    // 设置字符集编码
    characterEncodingFilter.setEncoding("UTF-8");
    return new Filter[]{characterEncodingFilter};
}
```

### 各种类型的参数

#### POJO

* 可以直接讲pojo类作为形参从请求中接收，请求的key与pojo的属性名一致，会自动生成该pojo对象。

```java
@RequestMapping("/pojoParams")
@ResponseBody
public String pojoParams(User user) {
    System.out.println("user: " + user);
    return "{'msg':'hello, " + user.getUserame() + "'}";
}
```

#### 嵌套POJO

* 在pojo中嵌套pojo类时，请求参数可以通过将参数设置成`父类.子类`的形式进行传参。

对user pojo中嵌套的的address pojo进行传参：`address.province=beijing&address.city=bj`

```java
@RequestMapping("/pojoInPojoParams")
@ResponseBody
public String pojoInPojoParams(User user) {
    System.out.println("user: " + user);
    return "{'msg':'hello, " + user.getUserame() + "'}";
}
```

#### 数组参数

* 相同名称的参数，可以使用`String[] 名称`进行接收。
* 可用于复选框。

```postman
likes:singing
likes:dancing
likes:rap
likes:basketball
```

```java
@RequestMapping("/arrayParams")
@ResponseBody
public String arrayParams(String[] likes) {
    System.out.println("likes: " + Arrays.toString(likes));
    return "{'msg':'your liks is }" + Arrays.toString(likes);
}
```

#### 集合类型

* 对于集合类型，需要加上`@RequestParam`注解，表示将请求参数作为参数传入，否则会直接将集合当成pojo类型，调用其构造器，造成错误。
* 请求方式与数组一致。

```java
@RequestMapping("/listParams")
@ResponseBody
public String listParams(@RequestParam List<String> likes) {
    System.out.println("likes: " + likes);
    return "{'msg':'your liks is " + likes + " }";
}
```

#### json格式参数

* 导入json处理lib，jackson

* 在SpringMvcConfig配置类中，增加`@EnableWebMvc`注解，开启由json数据转换为对象的功能。

  ```java
  @Configuration
  @ComponentScan("com.itniuma.controller")
  @EnableWebMvc
  public class SpringMvcConfig {
  }
  ```

* 为接收json对象的参数加上`@RequestBody`注解，访问请求体中的json数据。

  ```java
  @RequestMapping("/jsonParams")
  @ResponseBody
  public String jsonParams(@RequestBody List<String> likes) {
      System.out.println(likes);
      return "{'msg':'your liks is " + likes + " }";
  }
  ```

* json格式可以传递数组或对应pojo类型的数据。

  ```java
  @RequestMapping("/jsonPojoParams")
  @ResponseBody
  public String jsonPojoParams(@RequestBody User user) {
      System.out.println(user);
      return "{'msg':'hello, " + user.getUserame() + "'}";
  }
  ```

#### 日期格式传参

### @RequestBody

* 该注解表示将请求体中的数据传递给该形参，该注解在一个方法中只能使用一次。

* 对比`@RequestParam`与`@RequestBody`：
  * postman中
    * 如果设置request body为`x-www-form-urlendocded`，会自动添加在header中添加`Content-Type:application/x-www-form-urlencoded`
    * 如果设置request body为json，会自动添加在header中添加`Content-Type:application/json`
  * @RequestParam用于接收url和body为`application/x-www-form-urlencoded`格式的数据。
  * @RequestBody用于接收body为`application/json`格式的json数据

=======


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
