/*
 * Exercise: Introspection.
 *
 * In order to discover how introspection works in Java,
 * let's write the class DumpMethods whose main() method gets class name from the command line through args
 * and outputs (prints) all the methods that can be called on an instance of the passed class.
 * Note that, we are looking for the public interface of the class not for its declared methods.
 */
import java.util.Arrays;

public class DumpMethods {
    public static void dump(String className) {
        try {
            // Versione "intuitiva", loop su getMethods()
            /*
            for (var m: java.lang.Class.forName(args[0]).getMethods()) {
                System.out.println(m);
            } */
            // One-liner, usa uno stream, stampa per ogni metodo dichiarato
            Arrays.stream(Class.forName(className).getMethods()).forEach(System.out::println);
        } catch (Exception e) {
            // Se non c'è la classe passata come argomento alza un'eccezione
            System.out.println("Class not found");
        }
    }

    public static void main (String... args) {
        System.out.println("The methods are: \n");
        dump(args[0]);
    }
}
