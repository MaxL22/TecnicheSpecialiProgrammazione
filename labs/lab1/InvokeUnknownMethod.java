/*
 * Exercise: Intercession.
 *
 * Let's write the class InvokeUnknownMethod that
 * invokes a method whose identity is unknown at compile-time through reflection.
 * Let's suppose that our program would like to invoke one of the methods of the class Calculator (check file)
 * without knowing which one until the main is executed;
 * i.e., the method name, the number and type of its arguments should be inferred from the input.
 * An example of activation will be:
 * java InvokeUnknownMethod Calculator add 7 25
 */
import java.util.Arrays;
import java.lang.reflect.*;

// First try, recognizes the values based on the method, not too pretty overall
public class InvokeUnknownMethod {
    public static void main (String[] args) {
        try {
            // Get the method, from the class get the first method that has the name equal to the given parameter
            Method A = Arrays.stream(Class.forName(args[0]).getMethods()).filter((Method m) -> m.getName().equals(args[1])).findFirst().get();
            // Parameter types for the method
            Class[] p = A.getParameterTypes();
            // Calculator takes only numbers
            Number[] val = new Number[2];

            // Parses as int if int is required, double otherwise
            for (int i = 0; i < p.length; i++) {
                if (p[i].equals(int.class)) {
                    val[i] = Integer.parseInt(args[i+2]);
                }
                if (p[i].equals(double.class)) {
                    val[i] = Double.parseDouble(args[i+2]);
                }
            }

            // Negation only has one parameter
            if (A.getParameterCount() == 1) {
                // Print the invocation of the method, on an instance of the class, with the specified parameters
                System.out.println(A.invoke(Class.forName(args[0]).getConstructor().newInstance(), val[0]));
            } else {
                System.out.println(A.invoke(Class.forName(args[0]).getConstructor().newInstance(), val[0], val[1]));
            }

        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }
}
