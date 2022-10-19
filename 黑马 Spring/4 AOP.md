# AOP

Aspect Orient Programming，在不动原始设计的基础上对其功能进行增强，无侵入式。

## AOP核心概念

* join point：所有的方法
* point cut：要增加功能的方法
* advice：通知，共性的功能，在spring AOP中，以方法的形式呈现。
* aspect：切入点与通知之间的关系



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

  