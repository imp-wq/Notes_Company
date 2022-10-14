## maven

### 安装

1. 下载maven，解压。
2. 添加`MAVEN_HOME`到环境变量，添加`MAVEN_HOME`到path，以便使用`mvn`命令

`mvn -version`

3. 在conf/setting.xml文件`<localrepository>`中配置maven的本地仓库。会有一个默认仓库，在`C:\Users\Administrator\.m2\repository`。

   ```xml
   <localRepository>D:\wzy\apache-maven-3.6.1\maven_repository</localRepository>

4. 在conf/settting.xml文件的`<mirrors>`中配置阿里云镜像私服。

   [仓库服务 (aliyun.com)](https://developer.aliyun.com/mvn/guide)

### maven项目结构

* 根目录
  * pom.xml：maven配置文件
  * src
    * main：源代码目录
      * java：java源码目录
      * resources：源码配置文件目录
      * webapp：Web项目目录
    * test：测试代码目录
      * java：测试代码java源码目录
      * resources：测试代码配置文件目录



### 常用命令

maven命令需要在`pom.xml`文件所在的目录中运行。

* compile：编译
  * 会自动下载所需要的依赖，并生成target目录存放编译后的文件。
* clean：清理
  * 用于删除target目录。
* test：测试
  * 自动执行test目录中的测试代码。
* package：打包
  * 打包成jar包，存放字节码。
* install：安装
  * 将当前项目打包成jar包安装到本地仓库。

* maven的生命周期：

  同一生命周期内，执行后面的命令，前面的命令都会自动执行。

  * clean：清理工作
  * default：核心工作，包括compile, test, package, install等。
  * site：生成站点，一般不会用maven来打包发布。

### 在Idea中使用maven

* Idea本身集成了maven，可以在File | Settings | Build, Execution, Deployment | Build Tools | Maven中配置本地maven，并对仓库和配置文件进行override。
* 创建maven项目：选择maven面板。
* 右边栏：
  * add maven project->选择项目的pom.xml文件。
  * LifeCycle：用于执行maven的命令。
* maven helper插件：
  * 在Run maven中快捷运行maven命令。
  * 在Debug maven中对maven进行断点调试。

### maven坐标

maven坐标是资源的唯一标识。

由这些部分组成：

* groupId
* artifactId
* version

### 依赖管理

​	用于导入第三方jar包。

* 在`<dependencies>`节点中配置依赖，通过maven坐标锁定资源。

* 添加完依赖，点刷新下载。

* 在`File | Settings | Build, Execution, Deployment | Build Tools` 

  切换为any changes， 使得添加依赖后不需要刷新自动生效。

* 可以`alt+insert`，选择dependencies，搜索需要的依赖，自动生成`<dependencies>`。

* 通过`<scope>`控制每个依赖的作用范围。

  * jar包作用范围：编译环境，测试环境，运行环境

  * scope配置项：

    * compile：默认值，在3种环境中都有效。

    * test：仅在测试环境有效，仅在test目录中可以用。

    * provided：仅在编译、测试环境有效，运行环境无效。

      常用于servlet-api。

      因为Tomcat自带了servlet jar包。

    * runtime：在测试、运行环境有效，在编译环境无效。

      比如jdbc驱动，不会直接用到，作为编译环境种用到的jar包的依赖。

    * system：编译和测试环境有效，运行环境无效。

      不太常用，一般用于运行时使用本地jar包的情况。

    * import：等maven高级了再讲。

## Tomcat

### 配置文件

​	配置文件在conf目录中

* logging.properties：可修改控制台编码为GBK，以解决中文乱码问题。

  ```properties
  java.util.logging.ConsoleHandler.level = FINE
  java.util.logging.ConsoleHandler.formatter = org.apache.juli.OneLineFormatter
  java.util.logging.ConsoleHandler.encoding = GBK
  ```

* server.xml：可在Connector节点中修改端口号。修改为80即与http协议默认端口号重合，访问时可省略端口号。

  ```xml
  <!-- A "Connector" represents an endpoint by which requests are received
           and responses are returned. Documentation at :
           Java HTTP Connector: /docs/config/http.html
           Java AJP  Connector: /docs/config/ajp.html
           APR (HTTP/AJP) Connector: /docs/apr.html
           Define a non-SSL/TLS HTTP/1.1 Connector on port 8080
      -->
  <Connector port="8080" protocol="HTTP/1.1"
             connectionTimeout="20000"
             redirectPort="8443" />
  ```

### 项目部署

* 将项目打包成war文件后，放在WEB-APP目录下，tomcat会自动对war包进行解压缩。

### 项目结构

* archetype选择：`org.apache.maven.archetypes:maven-archetype-webapp`
* pom.xml：
  * package节点：设置打包方式，jar包和war包，war包用于web项目。

### maven项目中使用Tomcat

2种方式：

* 在项目的configuration中添加Tomcat Server，手动选择本地tomcat目录。

* 在pom.xml中配置maven tomcat插件，插件只支持到Tomcat 7版本。

  ```xml
   <plugin>
       <groupId>org.apache.tomcat.maven</groupId>
       <artifactId>tomcat7-maven-plugin</artifactId>
       <version>2.2</version>
       <configuration>
           <port>80</port>
           <path>/</path>
       </configuration>
  </plugin>
  ```

## Servlet

​	servlet是java提供的一门动态web资源开发技术，是Java EE提供的13个规范（接口）之一。因此其文档要在java EE的文档中查看。

* 导入servlet坐标，注意scope必须设置为provided。

  ```xml
  <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
  </dependency>
  ```

* servlet生命周期：

  1. 加载和实例化：由容器创建Servlet实例对象。

     ​	默认在servlet第一次被访问时，可以通过`loadOnStartup`设置，该属性默认值为-1，为负整数时都默认在第一次访问时创建实例对象。

  2. 初始化：init

  3. 请求处理：service

  4. 服务终止：destroy

 

---

## cookie and session

### session

* session是基于cookie实现的。

  * 服务端使用session后，Tomcat会将session id当作cookie发送给浏览器。

  * 浏览器会通过cookie，将session id进行保存(`JSESSIONID`字段)，并在每次请求中将session id放入cookie发送给服务端。

  * 服务端通过cookie接收到session id后，通过该id来保证相同id访问相同的session对象。

    ```http
    Cookie: JSESSIONID=AD66A2A75F5CAC5E7751084460E95B9F
    ```

* session的钝化和活化：

  * 钝化：服务器如果正常关闭，Tomcat会自动将Session中的数据写入硬盘文件。
  * 活化：再次启动服务器，可以从文件中加载Session数据。

* session的销毁：

  * 默认情况下，session会在30min自动销毁。

    可以在session-config的session-timeout节点下配置session的失效时间。

    ```xml
    <session-config>
        <session-timeout>100</session-timeout>
    </session-config>
    ```

  * 调用session对象的invalidate方法，销毁该session对象。

  * 浏览器关闭，session id也就随之销毁了，重新打开浏览器会生成新的session id。



* 对比session与cookie：
  * cookie存储在客户端，session存储在服务端。
  * 因此相较于cookie，session更安全。
  * cookie大小限制3kb，session无大小限制。
  * cookie不占用服务器资源，session占用服务器资源。

## 路径问题

* 浏览器使用路径：需要加虚拟目录，即项目访问路径。

  如：

  * redirect，可以通过`request.getContextPath()+请求url`拼接来动态获取url。
  * html 标签，如a，form等

* 服务器端使用路径：无需加虚拟目录。

  如：

  * 请求转发

* 虚拟目录地址可以在pom.xml中，通过configuration标签下的path标签进行配置。

## 登录案例

* 在mybatis与servlet配合使用时，应保证sqlSessionFactory类只创建一次。

  因为每个sqlSessionFactory对象都绑定一个连接池，创建多个会造成资源的浪费。

* 解决方案：将获取sqlSessionFactory的方法封装到一个工具类的静态代码块中。

```java
public class SqlSessionFactoryUtils {
    private static SqlSessionFactory sqlSessionFactory;

    // 通过工具类的静态代码块，保证sqlSessionFactory只获取一次。
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
```

### 记住用户

* 获取“记住用户”框的值，html复选框在不设置value时，默认为`"on`"`。

* 写cookie：

  * new cookie对象
  * 通过setMaxAge方法设置cookie过期时间
  * 在响应中addCookie，为响应头添加Set-Cookie字段。

  ```java
  if ("on".equals(remember)) {
      Cookie c_username = new Cookie("username", username);
      Cookie c_password = new Cookie("password", password);
  
      // 通过maxAge方法设置Cookie的存活时间
      final int EXPIRE_TIME = 60 * 60 * 24 * 7;
      c_username.setMaxAge(EXPIRE_TIME);
      c_password.setMaxAge(EXPIRE_TIME);
  
      response.addCookie(c_username);
      response.addCookie(c_password);
  }
  ```

* 获取cookie：前端从cookie中读出用户名和密码，自动填入。

### 生成验证码

* java端通过CheckCodeUtil工具类生成验证码图片，通过response的输出流传给浏览器。
* 浏览器通过点击切换img的src属性，将其查询字符串拼接一个时间戳，来更改验证码。
* 如果不拼接时间戳，会有img缓存，点击验证码不会修改。
* 验证码Servlet生成验证码后，存入session中，供注册Servlet比较使用。
