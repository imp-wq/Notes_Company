package JdkProxy;

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
