public class Test1 extends Test0 {
    private Boolean isDone;
    public String name;

    public Test1() {
        this.isDone = false;
        this.name = "ciao";
    }

    public void stop() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return super.toString() + "isDone: " + Boolean.valueOf(this.isDone).toString() + "\n" + "name: " + this.name + "\n";
    }
}
