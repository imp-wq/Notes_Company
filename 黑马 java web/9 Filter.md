# Filter

过滤器，web三大组件（Servlet，Filter，Listener）之一。

* 过滤器用于把对资源的请求进行拦截，从而实现一些特殊功能。
* 一般完成一些通用操作，比如：权限控制，编码处理，敏感字符处理等。

### Filter interface

* 通过实现Filter interface，对其核心方法doFilter及其他方法进行重写，来配置过滤器。
* 通过`WebFilter("路径")`注解，配置Filter拦截资源的路径。
* Filter目录一般放到web目录下，因为是web的组件。

* 通过filterChain的doFilter方法放行请求。

```java
@Override
public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    filterChain.doFilter(servletRequest, servletResponse);
}
```

### Filter的执行逻辑

* filter通过`chain.doFilter`方法进行放行，执行流程：
  1. 放行前逻辑
  2. 放行，访问资源
  3. 放行后的逻辑
* 放行前：对request中的数据进行处理，比如编码。
* 放行后：对response中的数据进行处理。
* Filter拦截配置：
  * 拦截具体资源：`/index.jsp`
  * 目录拦截：`/user/*`
  * 后缀名拦截：`*.jsp`
  * 拦截所有：`/*`

### 过滤器链

一个web应用中可以配置多个过滤器，形成一个链条。

* 执行逻辑：过滤器1->过滤器2->...->web资源->...过滤器2->过滤器1
* 过滤器的顺序：默认情况下，按过滤器类名的字符串默认排序。

### 过滤器登录验证