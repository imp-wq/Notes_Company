package cglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Test {
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
}
