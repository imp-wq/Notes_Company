package JdkProxy;

public class Test {
    public static void main(String[] args) {
        Star ldh = new Star("ldh");
        Skill proxy = getProxyUtil.getProxyInstance(ldh);
        proxy.sing();

        Star ycy = new Star("ycy");
        Skill ycyProxy = (Skill) getProxyUtil.getProxyInstance(ycy);
        ycyProxy.sing();
    }
}
