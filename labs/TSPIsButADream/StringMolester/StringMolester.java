/*
 * Test on java.lang private fields
 * java --add-opens java.base/java.lang=ALL-UNNAMED Main.java
 */

import java.util.*;
import java.lang.invoke.*;

public class StringMolester {
    public static void printArray (Object[] a) {
        Arrays.stream(a).forEach(System.out::println);
    }
    public static void printArray (byte[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println((char) a[i]);
        }
    }

    public static void main(String[] args) {
        printArray(String.class.getDeclaredFields());
        try {
            Object hashish = MethodHandles.privateLookupIn(String.class, MethodHandles.lookup())
                             .findGetter(String.class, "value", byte[].class)
                             .invoke("ciao");
            printArray((byte[]) hashish);
        }
        catch (Throwable e) {
            System.out.println("[ERROR] " + e);
        }
    }
}
