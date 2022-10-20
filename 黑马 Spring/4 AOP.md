# AOP

Aspect Orient Programming，在不动原始设计的基础上对其功能进行增强，无侵入式。

[Core Technologies (spring.io)](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop)

## AOP核心概念

* join point：所有的方法，即被proxy拦截到的所有方法。
* point cut：要增加功能的方法，即选择要使用proxy进行功能增强的方法。
* advice：通知/增加，共性的功能，在spring AOP中，以方法的形式呈现。即proxy拦截到后对point cut方法进行的操作。
* aspect：切入点与通知之间的关系。
* weaving：织入，把增强应用到目标对象，创建新的代理对象的过程，spring采取动态代理织入。



* 比如在保存用户、更新用户信息、删除用户时都需要进行日志控制，需要一种方法，能够保证日志控制功能与代码不紧耦合，同时进行代码复用。
* 通过动态代理技术实现：
  * Spring通过动态代理技术动态生成代理对象，代理方法执行时增强功能介入，再通过代理去调用目标方法。
* 常用动态代理技术：
  * JDK代理：基于接口的动态代理技术。
  * cglib代理：基于父类的动态代理技术，第三方库。

### 动态代理技术

* 静态代理：即自己创建一个代理类进行代理。

* 动态代理：创建一个统一的代理类，可以生成其他类的代理对象。

* java提供了代理类：`java.lang.reflect.Proxy`

* 可以做成一个静态工具类，用于返回一个实现了特定接口对象的代理

  * newProxyInstance方法的参数：
    * classLoader
    * interfaces，即被代理对象实现的接口（数组），代理哪些方法
    * InvocationHandler接口的实现类对象，通过实现该接口的invoke方法，调用被代理对象的方法，同时可以在调用前/后进行执行一些代码逻辑。
      * invoke方法的参数：
        * proxy：代理对象，一般不用到
        * method：代理对象被调用的方法，封装成的Method对象
          * 可以通过`method.getName()`获取调用的方法名
          * 可以通过`method.getName().equals("方法名")`判断调用的是哪个方法。
        * args：代理对象被调用的方法时传递的实际参数。
      * invoke方法会通过method和args参数传入调用的方法和参数名，此时可以根据传入原对象obj、方法名、参数，通过反射的invoke方法，对原对象的方法进行调用，并将结果返回。
      * InvocationHandler只有1个方法，因此可以使用lambda表达式进行简化。
  * 使用时，因为代理实现了特定接口，因此可直接将编译类型转换成该接口，从而进行方法调用。
  * 也可以使用泛型函数，来让接口可以为任何类型生成代理。

  

* 被代理的对象需要实现特定的接口。

  ```java
  public class getProxyUtil {
      public static <T> T getProxyInstance(T obj) {
          return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
                  new InvocationHandler() {
                      @Override
                      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                          System.out.println("调用之前进行了一些操作");
                          // 通过这里来指定被代理的对象
                          Object result = method.invoke(obj, args);
                          System.out.println("调用之后进行了一些操作");
                          return result;
                      }
                  });
      }
  }
  ```

  ```java
  public class Test {
      public static void main(String[] args) {
          Star ldh = new Star("ldh");
          Skill proxy = (Skill) getProxyUtil.getProxyInstance(ldh);
          proxy.sing();
  
          Star ycy = new Star("ycy");
          Skill ycyProxy = (Skill) getProxyUtil.getProxyInstance(ycy);
          ycyProxy.sing();
      }
  }
  ```

  ```java
  public interface Skill {
      void dance();
      void sing();
  }
  ```

  ```java
  public class Star implements Skill {
  
      private String name;
  
      public Star(String name) {
          this.name = name;
      }
  
      @Override
      public void dance() {
          System.out.println(this.name + " is dancing...");
      }
  
      @Override
      public void sing() {
          System.out.println(this.name + " is singing...");
      }
  }
  ```

* 应用场景：

  * 日志记录

  * 敏感字符过滤：

    * 在filter中，对request对象，利用ServletRequest接口进行代理，代理其getParameter方法，取到的参数先进行敏感词的匹配和过滤，再将结果返回。

      将request对象的代理传到doChain方法中放行。

* 代理对象对原对象的增强：

  * 增强参数：对于传入的参数列表进行一些处理，再通过invoke传给真实对象的方法。
  * 增强返回类型：对于返回类型进行一些处理，再进行返回。
  * 增强方法体执行逻辑：在方法的执行前/执行后进行一些其他操作。

### cglib动态代理

是一个第三方动态代理框架，与jdk原生代理相比，不需实现特定接口，只需通过父类继承。

* 已继承到Spring framework中，可直接使用。
* 目标对象无需实现特定接口，生成的代理对象为目标对象的子类对象，可以直接向上转型为目标对象，并调用其方法。
* 使用步骤：
  1. 获取enhancer
  2. 通过setSuperclass方法，指定目标对象类型，将其设置为代理对象的父类。
  3. 通过setCallback方法设置回调，以反射形式调用原对象的方法，逻辑与jdk原生动态代理类似。
  4. 通过create方法创建代理对象

```java
  public static void main(String[] args) {
        SomeObject someObject = new SomeObject();
        Advice advice = new Advice();

        // 1.获取enhancer
        Enhancer enhancer = new Enhancer();
        // 2.设置目标对象类型，将目标对象设置为生成的代理对象的父类。
        enhancer.setSuperclass(SomeObject.class);

        // 3. 设置回调，创建callback接口的子接口MethodInterceptor
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                advice.before();
                method.invoke(someObject);
                advice.after();
                return null;
            }
        });

        // enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
        //
        // });

        // 4.创建代理对象。因为生成的proxy对象是原对象的子类对象，因此可以直接向上转型成原对象。
        SomeObject objectProxy = (SomeObject) enhancer.create();

        objectProxy.someOperator();
    }
```

### AOP的工作流程

1. Spring容器启动。
2. 读取所有切面配置的切入点，只读取配置的切入点。
3. 初始化bean，判断bean对应的类中的方法是否匹配到任意切入点。
   * 如果匹配不到，创建bean对象
   * 如果匹配成功，创建bean的代理对象
4. 获取bean的执行方法
   * 如果获取的bean是代理对象，会通过代理对象的运行模式，运行advice与原始方法的内容。

AOP是基于代理模式的。

* 因此使用了aop的对象，使用时生成的是其代理对象，通过getClass方法可以看到。

  Spring会重写代理的toString方法，因此toString方法打印出的与原对象相同。

## AOP基本使用

### 坐标

* Sprint-aop包已作为依赖默认导入。

* 导入aspectj坐标

  ```xml
   <dependency>
       <groupId>org.aspectj</groupId>
       <artifactId>aspectjweaver</artifactId>
       <version>1.9.4</version>
  </dependency>
  ```

### AOP模块

* 在该文件夹中建立aop的类。

* 通过`@Component`注解将其注册为Spring的bean，再通过`@Aspect`指定其为AOP类。

* 创建切入点方法pointCut，通过`@Pointcut`注解指定切入点表达式。

* 为advice方法指定通知类型。

  ```java
  @Component
  @Aspect
  public class MyAdvice {
  
      @Pointcut("execution(void com.itniuma.dao.BookDao.save())")
      private void pointCut() {
      }
  
      @Before("pointCut()")
      public void before() {
  
      }
  }
  ```

### SpringConfig

* 通过`@EnableAspectJAutoProxy`注解开启项目中以注解形式配置AOP功能。

```java
@Configuration
@ComponentScan("com.itniuma")
@EnableAspectJAutoProxy
public class SpringConfig {
}
```



## 切入点表达式

* 切入点表达式格式：

  `动作关键字(【访问修饰符】 返回值 包名.类/接口名.方法名(参数) 【异常名】)`

  `execution(void com.itniuma.dao.BookDao.save())`

  * 动作关键字：一般为execution，执行指定切入点。

  * 包名.类/接口名.方法名：可以通过接口指定方法，也可以通过实现类指定方法。

    * 通配符：

      用于简化书写

      * *：单独任意个符号，可用于指定包/方法/返回类型/参数类型等为任意。
        * 在参数中，`(*)`表示1个任意参数。
        * 在函数名中，可以用`find*`表示匹配所有以find开头的方法。
      * `..`：多个连续任意符号，可以用`com..UserService`简化包名，或在参数列表`(..)`表示任意参数类型。
      * +：用于匹配子类类型。

* 规范和技巧：

  * 描述切入点通常描述到接口，不是实现类，避免紧耦合。
  * 增删改的返回类型一般使用精准类型加快匹配速度，查询类的匹配类型使用*。
  * 包名一般不用..匹配，效率很低，一般用*匹配单个包。
  * 接口名常使用`*模块`的方式进行匹配，比如`*Service`绑定业务层接口名。
  * 方法名常以`动词*`进行匹配，比如用`getBy*`,`selectBy*`等。

## 通知类型

* 通知类型：

  * @Before

  * @After

    * `@Around("pointCut()")`：表示环绕原始方法。

      * 需要通过ProceedingJoinPoint参数的proceed方法来调用原始方法。如果不进行调用，原始方法不会执行。

        可以进行一些权限校验。

      * 如果原始方法有返回类型，则需要获取并进行返回，否则会抛出异常。

        因此Around方法的返回类型应当指定为Object。

      * 会抛出异常，以便抛出原始方法的异常。

      * `Signature signature = proceedingJoinPoint.getSignature();`，可以得到签名信息对象，获取调用的方法名、类名等信息。

        ```java
        @Around("pointCut()")
            public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
                System.out.println("around before advice");
                // 调用原始方法。
                Object result = proceedingJoinPoint.proceed();
                System.out.println("around after advice");
                return result;
            }
        ```

  * @AfterReturning：在原始方法成功执行（没有抛出异常）后执行。

  * @AfterThrowing：

* 数据获取：

  * 只有@Around和@AfterReturning能拿到返回值信息。

  * 可以通过以参数形式传入JoinPoint对象，通过其args方法以`Object[]`的方式获取到方法执行的参数。

    * 所有类型的通知都可以通过该方法，获取到实参数组。
    * Around的ProceedingJoinPoint是该类型的子类，因此使用方法相同。
    * Around的ProceedingJoinPoint对象的proceed方法有2个重载，无参重载会自动传入参数，有参重载可以在对参数处理后再传入。

  * @AfterReturning的数据获取：

    * 通过在注解中`returning = "形参参数名"`的形式，以形参的方式获取到原方法返回值。
    * 如果同时要使用joinPoint和原方法的返回值，JoinPoint必须是第一个参数，即必须是`JoinPoint joinPoint, Object result`的顺序。
    * @AfterThrowing方法获取异常的方式类似，以形参形式，在注解中以`throwing= "形参参数名"`的方式指定。

    ```java
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("args: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("执行before方法");
    }
    ```

    ```java
    // proceedingJoinPoint.proceed方法的重载
    Object proceed() throws Throwable;
    Object proceed(Object[] var1) throws Throwable;
    ```

    ```java
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around before advice");
        Object[] args = proceedingJoinPoint.getArgs();
        args[0]+=",cba,nba";
        // 调用原始方法。
        Object result = proceedingJoinPoint.proceed(args);
        System.out.println("around after advice");
        return result;
    }
    ```

    ```java
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("----after returning-----");
        System.out.println("args: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("result: " + result);
        System.out.println("---------");
    }
    ```

    