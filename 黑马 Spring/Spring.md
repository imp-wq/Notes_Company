# Spring

[Spring | Home](https://spring.io/)

* Spring的作用：
  * 简化开发：Ioc、AoP->事务处理
  * 框架整合：MyBatis, MyBatis-plus, structs

* Spring家族，Spring生态圈。
  * Spring Framework: 底层框架，所有Spring技术全部依赖于该Framework。
  * Spring Boot: 加速开发，简化代码。
  * Spring Cloud: 分布式系统的开发。

* Spring系统架构：
  * Core container: 核心容器，装对象的。
  * AOP: Aspect Oriented Programming, 面向切面编程。
  * Data Access/Integration: 数据访问/集成。
  * Web: Spring MVC
  * Test: 单元测试与集成测试。

## IoC与DI

​	Inversion of Control, 控制权反转。指对象的控制权由程序转移到外部，不在程序中使用new创建对象，由外部提供对象。

* Spring提供了IoC容器，用来当作提供对象的”外部“。
* Bean：由IoC容器管理（负责创建）的对象。
* Dependency Injection: DI, 依赖注入，在容器中建立bean与bean之间依赖关系的过程。
* 由主动new对象->由IoC容器提供对象。

## IoC入门案例

1. 导入Spring坐标

2. resource目录中创建Spring xml配置文件，选择Spring Config，命名为applicationContext.xml

3. 在配置文件中创建bean，指定id和对应的类

   ```xml
   <bean id="bookDao" class="com.itniuma.dao.impl.BookDaoImpl"/>
   <bean id="bookService" class="com.itniuma.service.impl.BookServiceImpl"/>
   ```

4. 拿到容器对象，通过getBean方法传入id,获取相应的bean对象。

   ```java
   ApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");
   // 获取bean
   BookService bookService = (BookService) container.getBean("bookService");
   bookService.save();
   ```

### DI的使用

解决bean之间的依赖问题。

1. 在bean中用到的其他bean，为其添加set方法

   ```java
   public class BookServiceImpl implements BookService {
       private BookDao bookDao;
   
       public void save() {
           System.out.println("service save running...");
           bookDao.save();
       }
   
       public void setBookDao(BookDaoImpl bookDao) {
           this.bookDao = bookDao;
       }
   
   }
   ```

2. 在applicationConfig.xml中，为bean配置property，选择对应的name和类，完成dependency inject。

   * name为setter方法`setXXX`的XXX，应与属性名称相同。
   * ref为引用的类。

   ```xml
   <bean id="bookService" class="com.itniuma.service.impl.BookServiceImpl">
       <property name="bookDao" ref="bookDao"/>
   </bean>
   ```

## bean的配置

### name-别名

* 可以通过name为bean起多个别名，用逗号/分号/空格分隔。

* bean不存在时会抛出异常

  ```bash
  NoSuchBeanDefinitionException: No bean named 'service23333' available
  ```

### scope

* singleton：默认值，通过getBean获取的bean为单例的，即多次获取的都是同一个对象。
* prototype：每次获取创建的一个新的bean对象。

* bean默认是单例的：为了节省容器中的空间。

  * 适合交给bean单例管理的对象：
    * 表现层对象
    * 业务层对象
    * 数据层对象
    * 工具对象

  * 不适合交给bean单例管理的对象：
    * 封装实体的域对象

## bean对象的创建

* spring通过反射调用bean类的**无参**构造器来创建bean对象。

  因此即使constructor为private也能调用到，但必须有无参constructor（即java bean应当有无参构造器），否则会抛出BeanCreationException。

* spring的报错：从下往上看，越下面越具体。

### 使用静态工厂创建对象

对于使用静态工程创建实例对象的类进行配置。

1. xml中配置该bean的工厂类，配置factory-method attribute，指定工厂类中创建该bean的实例对象的静态方法。

   ```xml
   <bean id="bookDaoFromFactory" class="com.itniuma.factory.BookDaoFactory" factory-method="getBookDao"/>
   ```

2. 可以使用该静态工厂来获取bean的实例对象

   ```java
   // 获取IoC容器
   ApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");
   // 获取bean
   // BookService bookService = (BookService) container.getBean("bookService");
   // BookService bookService = (BookService) container.getBean("service2");
   
   BookDao bookDao = (BookDao) container.getBean("bookDaoFromFactory");
   bookDao.save();
   ```

### 使用实例工厂创建对象

* 即工厂需要实例化，使用工厂对象创建bean对象。

  1. 在xml中进行配置：

     * 配置实例工厂的bean
     * 配置该实例对象的bean，通过factory-method attribute指定实例工厂中制造对象的方法，通过factory-bean指定实例工厂的bean

     ```xml
      <bean id="bookInstanceFactory" class="com.itniuma.factory.BookDaoInstanceFactory"/>
     <bean id="bookDaoFromInstanceFactory" factory-bean="bookInstanceFactory" factory-method="getBookDao"/>
     ```

  2. 在java代码中通过bean获取对象。

     ```java
     ApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");
             // 获取bean
     BookDao bookDao = (BookDao) container.getBean("bookDaoFromInstanceFactory");
     bookDao.save();
     ```

* 可以对上述方法进行简化：

  1. 创建接口`FactoryBean<T>`的实现类，泛型为创建的类型。

  2. 接口的getObject方法返回bean实例对象，getObjectType方法返回bean实例对象的类型（Class对象）。

     通过可选的方法isSingleton选择是否为单例的（return true为单例，return false为不是单例）。

     ```java
     public class BookDaoFactoryBean implements FactoryBean<BookDao> {
         // 使用该方法创建对象
         @Override
         public BookDao getObject() throws Exception {
             return new BookDaoImpl();
         }
     
         // 使用该方法指定对象类型
         @Override
         public Class<?> getObjectType() {
             return BookDao.class;
         }
     }
     ```

  3. 在xml中配置factoryBean类

     ```xml
     <bean id="bookDaoFromFactoryBean" class="com.itniuma.factory.BookDaoFactoryBean"/>
     ```

## bean的生命周期

### init和destroy

* 在xml文件中通过`init-method`和`destroy-method` attribute指定初始化生命周期方法和结束生命周期方法。

  ```xml
  <bean id="bookDao" class="com.itniuma.dao.impl.BookDaoImpl" init-method="init" destroy-method="destroy"/>
  ```

  ```java
  public class BookDaoImpl implements BookDao {
      public void save() {
          System.out.println("run save method...");
      }
  
      public void init() {
          System.out.println("bean init...");
      }
  
      public void destroy() {
          System.out.println("bean destroy...");
      }
  }
  ```

* destroy生命周期一般情况下不会执行，需要手动调用close方法关闭容器，或者通过`registerShutdownHook`方法注册关闭hook。

  这2个方法不能通过`ApplicationContext`接口调用，需要通过实现类`ClassPathXmlApplicationContext`调用。

  ```java
   public static void main(String[] args) {
          // 获取IoC容器
          ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");
          container.registerShutdownHook();
          // 获取bean
          BookDao bookDao = (BookDao) container.getBean("bookDao");
  
          bookDao.save();
      }
  ```

### 通过接口控制生命周期

```java
public class BookDaoImpl implements BookDao, InitializingBean, DisposableBean {
    public void destroy() {
        System.out.println("bean destroy...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("after properties set");
    }
}
```

* 可以通过实现InitializingBean接口的afterPropertiesSet方法，DisposableBean接口的destroy方法来控制生命周期，无需在xml中进行配置。

* afterPropertiesSet方法会在DI进行后，即通过setXXX注入依赖后，执行。

  afterPropertiesSet周期在init周期之前。

* bean的初始化流程：

  1. 创建对象，分配内存
  2. 执行对象的构造方法
  3. 执行属性注入，properties set
  4. 执行bean的初始化方法

## 依赖注入

* 向一个类中传递数据的方式：
  * setter
  * constructor
* bean依赖的数据类型：
  * 引用类型，对象
  * 基本数据类型与String，比如数据库连接池的max数量

### 对简单类型进行setter注入

简单类型即基本数据类型+String类型

1. 在类中对于简单类型字段提供setter

   ```java
   public class BookDaoImpl implements BookDao {
       private int connectionNum;
       private String databaseName;
   
       public void setConnectionNum(int connectionNum) {
           this.connectionNum = connectionNum;
       }
   
       public void setDatabaseName(String databaseName) {
           this.databaseName = databaseName;
       }
   
   
       public void save() {
           System.out.println("run save method..." + this.databaseName + " " + this.connectionNum);
       }
   }
   ```

2. 在xml中，通过bean标签的value attribute，来为基本类型指定值。

   数字类型会自动被Spring转换。

   ```xml
   <bean id="bookDao" class="com.itniuma.dao.impl.BookDaoImpl" >
       <property name="connectionNum" value="233"/>
       <property name="databaseName" value="mysql"/>
   </bean>
   ```

### 构造器注入

创建有参构造器，进行参数注入。

* 在java中，为bean指定带参构造器。
* 在xml中，通过`constructor-arg`标签指定构造器的形参并进行赋值。
  * 通过ref attribute为引用类型指定值，value attribute为简单类型指定值。
  * 可以通过这些方式来与构造器中的形参进行匹配：
    * name：形参名称
    * type：形参类型
    * index：形参序号
* 一般认为构造器更加严谨，框架使用居多。自己开发推荐用setter。

```java
public class BookDaoImpl implements BookDao {
    private int connectionNum;
    private String databaseName;

    public BookDaoImpl(int connectionNum, String databaseName) {
        this.connectionNum = connectionNum;
        this.databaseName = databaseName;
    }

    public void save() {
        System.out.println("run save method..." + this.databaseName + " " + this.connectionNum);
    }
}
```

```xml
<bean id="bookDao" class="com.itniuma.dao.impl.BookDaoImpl">
        <!--  构造器注入  -->
        <constructor-arg name="connectionNum" value="666"/>
        <constructor-arg name="databaseName" value="MySQL"/>
</bean>
```

### 自动装配

在bean中使用autowired attribute进行自动装配。

* 在java中，bean类提供相应的setter。

  ```java
  // 自动装配BookDao对象
  public class BookServiceImpl implements BookService {
      private BookDao bookDao;
      public void save() {
          System.out.println("service save running...");
          bookDao.save();
      }
      public void setBookDao(BookDaoImpl bookDao) {
          this.bookDao = bookDao;
      }
  }
  ```

* 在xml文件中，设置`autowired="装配方式"`，会自动获取需要的bean，进行装配。

  如果`autowired="byType"`，则被装配的bean无需配置id。

* 自动装配无法用于装配简单类型，只能用于引用类型。

  ```xml
  <bean class="com.itniuma.dao.impl.BookDaoImpl"/>
  <bean id="bookService" class="com.itniuma.service.impl.BookServiceImpl" autowire="byType"/>
  
  ```

* 使用最多的是`autowired="byType"`，类型不能重复。

* 自动装配优先级低于构造器注入和setter注入，如果配置了构造器注入和setter注入，自动装配失效。

