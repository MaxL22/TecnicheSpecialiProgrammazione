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
import java.util.stream.IntStream;

// This tries to recognize the values and then tries to call the corresponding method
public class InvokeUnknownMethod2 {
    // Tries to recognize the type of number (just double or int)
    public static Class<?>[] recognizeTypes (String... args) {
        return Arrays.stream(args)
               .map(a -> a.contains(".") ? double.class : int.class)
               .toArray(Class[]::new);
    }
    // Converts the strings into the given arguments
    public static Object[] convertArgs (Class<?>[] types, String[] args) {
        return IntStream.range(0, types.length)
        .mapToObj(i -> {
            if (types[i].equals(int.class)) {
                return Integer.valueOf(args[i]);
            } else {
                return Double.valueOf(args[i]);
            }
        })
        .toArray(Object[]::new);
        // Ternary doesn't work because it auto-promotes everything to double. WTF
    }
    // Puts together all the steps
    public static Object callMethod (String className, String methodName, String... args) {
        try {
            // // Takes class, given types, given method
            // Class<?> cls = Class.forName(className);
            // Class<?>[] types = recognizeTypes(args);
            // Method m = cls.getDeclaredMethod(methodName, types);
            // Object[] a = convertArgs(types, args);
            // // Tries to call the method on an instance of the given class, with the given parameters, appropriately converted
            // return m.invoke(cls.getDeclaredConstructor().newInstance(), a);

            // Hellish one-liner
            return Class.forName(className).getDeclaredMethod(methodName, recognizeTypes(args))
                   .invoke(Class.forName(className).getDeclaredConstructor().newInstance(), convertArgs(recognizeTypes(args), args));
        } catch (Exception e) {
            System.out.println("[ERROR] "  + e.toString());
            return new Object();
        }
    }
    // "Dummy" main, does nothing except calling the method
    public static void main (String... args) {
        System.out.println("The result is " + callMethod(args[0], args[1], Arrays.copyOfRange(args, 2, args.length)));
    }
}
