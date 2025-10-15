public class Main {
    public static void main(String[] args) {
        Test1 target = new Test1();

        System.out.println("*** prima");
        System.out.println(target);

        Test1 copy = DeepCopy.deepCopy(target);

        target.change();
        target.stop();

        Test1 copy2 = DeepCopy.deepCopy(target);
        System.out.println("*** dopo");
        System.out.println("*** target");
        System.out.println(target);
        System.out.println("*** copy");
        System.out.println(copy);
        System.out.println("*** copy2");
        System.out.println(copy2);
        return;
    }

}
