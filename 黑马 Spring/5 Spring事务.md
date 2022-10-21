## Spring事务

Spring事务是基于jdbc实现的。

### JdbcConfig-定义事务管理器

* 定义事务管理器为jdbc的事务管理器，并注册为bean，交给spring管理。
* 事务管理器需传入dataSource，使用bean形参注入的形式传入。

```java
@Bean
public PlatformTransactionManager transactionManager(DataSource dataSource) {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(dataSource);
    return transactionManager;
}
```

### SpringConfig-开启事务注解

* 通过`@EnableTransactionManagement`注解开启注解形式管理事务。

```java
@Configuration
@ComponentScan("com.itniuma")
@Import({JdbcConfig.class, MybatisConfig.class})
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
public class SpringConfig {
}
```

### 接口-@Transactional

* 在接口中需要开启事务的方法上通过`@Transactional`注解开启事务，方法的执行会同时成功或失败。
* `@Transactional`可以加在接口或者实现类的方法上，但一般加在接口上，以解耦。
* `@Transactional`注解也可以加载类或者接口上，为其中所有方法开启事务。

```java
@Transactional
public interface AccountService {
    void transfer(String out, String in, Double money);
    void showAll();
}
```

## Spring事务角色

### 事务管理员与事务协调员

* 原本的DAO层中每一条增/删/改的操作，都是一个单独的事务。
* Spring通过`@Transactional`注解开启事务后，会将DAO层单独的事务加入到Spring开启的事务中，合并成一个事务。
* 事务管理员：常指Spring中Service层开启的事务的方法，发起事务方。
* 事务协调员：常指Spring中DAO层的方法，加入事务方。

![](.\noteimages\spring transactions.PNG)

## Spring事务配置

`@Transactional`注解中可以进行一些配置，配置事务的诸如超时时间、只读属性等。

* rollbackFor：控制事务在遇到哪些异常时回滚。

  * 默认情况下，只有遇到Error或RuntimeException才会进行事务回滚，即遇到编译时异常，如IOException，不会进行事务回滚。
  * 这种时候需要手动在rollbackFor中添加异常类型。

  ```java
  @Transactional(rollbackFor = {IOException.class})
  ```

* propagation：事务传播行为，即事务协调员对于事务管理员所携带事务的处理方式。

  * 应用场景：无论事务是否成功，都记录日志，即写日志操作不与事务进行回滚。

  * 可以通过try-catch-finally块，将日志记录行为放到finally块中，保证一定执行。

  * 再通过设置`propagation = Propagation.REQUIRES_NEW`，将日志记录放到单独的事务中，避免回滚。

    ```java
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    ```

  * propagation：

    * REQUIRES：默认，管理员有事务，协调员就加入；没有就新建。

    * REQUIRES_NEW：无论管理员是否有事务，协调员都新建事务。

    * SUPPORTS：如果管理员有事务，协调员就加入；如果没有，协调员也没有。

    * NOT_SUPPORTED：无论管理员是否有事务，协调员都没有事务。

    * MANDATORY：管理员必须有事务，协调员加入；管理员没有事务则报错。

    * NEVER：管理员不能有事务，协调员也没有；管理员有事务则报错。

      

    * NESTED：设置事务的回滚点savePoint，根据事务的回滚点进行回滚。