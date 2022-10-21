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

  