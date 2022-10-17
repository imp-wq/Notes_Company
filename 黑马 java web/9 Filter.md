## Filter

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

* 登陆后将用户存储在session中。
* 请求需要权限的资源时，从session中取出user对象。
  * 如果为null，则代表未登录，请求失败，跳转到登录页面。
  * 如果成功取出user对象，则代表已登录，允许访问资源。
* 放行登录、注册、验证码等相关逻辑：
  * 通过注册一个urls白名单，判断请求的url是否属于白名单内容，进行放行。

```java
@WebFilter("/*") // 注解：拦截所有资源 

@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        // 放行登录和注册相关的资源
        // 定义相关资源的白名单，html/css等静态资源，相关servlet
        String[] urls = {"/login.html", "/imgs/", "/css/", "/loginServlet", "/register.html", "registerServlet", "checkCodeServlet"};
        String url = req.getRequestURL().toString();
        for (String u : urls) {
            // 判断访问的url，是否在白名单中
            if (url.contains(u)) {
                // 放行
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if (user != null) {
            // 登录成功，放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 登录失败，使用请求转发，跳转至登录界面
            req.setAttribute("login_msg", "您尚未登录！");
            req.getRequestDispatcher("/login.html").forward(servletRequest, servletResponse);
        }

    }
```

## Listner

监听器，是java web三大组件之一，现在用的并不多。

* 可以监听application, session, request这三个对象的创建、销毁、set/get attribute事件，从而自动执行某些代码逻辑。
* 共有8个监听器：
  * ServletContext监听：
    * ServletContextListener：创建、销毁的监听
    * ServletContextAttributeListener：属性增删改的监听
  * Session监听：
    * HttpSessionListener
    * HttpSessionAttributeListener
    * HttpSessionBindingListener：监听对于Session的绑定和解除
    * HttpSessionActivationListener：监听对于session数据的钝化和活化
  * Request监听：
    * ServletRequestListener
    * ServletRequestAttributeListener