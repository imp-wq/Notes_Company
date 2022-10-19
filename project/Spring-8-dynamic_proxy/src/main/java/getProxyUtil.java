import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
