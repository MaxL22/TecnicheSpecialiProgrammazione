/*
 * The objective is to write the easiest possible annotation processor,
 * logging to screen the annotations found
 *
 * Compile the processor, then to use it:
 * > javac -processor SimpleProcessor Main.java
 */
import java.util.Arrays;

public class Main {
    public static void main (String... args) {
        // Refletion on the annotaions to show them at runtime
        Arrays.stream(Main.class.getMethods())
              .flatMap(m -> Arrays.stream(m.getDeclaredAnnotations()))
              .forEach(System.out::println);
    }

    @MyAnnotation("First Method")
    public static void m1 () {}

    @MyAnnotation("Second Method")
    @MyAnnotation("Still the second method")
    public static void m2 () {}

    @MyAnnotation("Third Method")
    public static void m3 () {}
}
