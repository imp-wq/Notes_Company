package cglibProxy;

public class Advice {
    public void before() {
        System.out.println("执行一些前置操作...");
    }

    public void after() {
        System.out.println("执行一些后置操作");
    }
}
