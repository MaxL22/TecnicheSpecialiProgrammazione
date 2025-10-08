/*
 * Exercise: Recognizing Elements.
 *
 * Let's consider a collection of Java classes and two string arrays.
 * The first array contains the names of the Java classes;
 * the second array contains the names of all the fields and all the methods declared in the classes
 * whose names are stored in the first array.
 * There is no relationship between the two arrays,
 * i.e., we don't know whether and in which class are declared
 * the names stored in the second array, nor if they represent a field or a method.
 * Let's write a program that
 *  i) checks that the names in the second array are all declared in one of the classes in the first array,
 *  ii) outputs, for each name where it is declared,
 *  iii) outputs whether it is a method or a field and
 *  iv) the full signature included types, visibility and scope.
 */
import java.util.stream.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.lang.reflect.*;

public class RecognizingElements {
    record tuplez (Class classe, Optional<? extends Member> optMember) {}

    public static tuplez findMember (String m, Class cls) {
        return new tuplez(cls, Stream.iterate(cls, c -> c.getSuperclass())
                          .takeWhile(Objects::nonNull)
                          .flatMap(mem -> Stream.concat(Arrays.stream(mem.getDeclaredMethods()), Arrays.stream(mem.getDeclaredFields())))
                          .filter(a -> a.getName().equals(m))
                          .findFirst());
    }

    public static void printMember (tuplez t) {
        System.out.println();
        System.out.println("Member " + t.optMember().get() + " is declared in " + t.classe());
        System.out.println("It's a " + ((t.optMember().get() instanceof Field) ? "Field" : "Method"));
        System.out.println("Signature:");
        System.out.println(t.optMember().get());
    }

    public static void main () {
        // Possible input
        String[] classNames = {
            String.class.getName(),
            ArrayList.class.getName(),
            HashMap.class.getName()
        };
        String[] memberNames = {
            // String methods
            "length",
            "charAt",
            "substring",
            "isEmpty",
            "toLowerCase",
            // String fields
            "CASE_INSENSITIVE_ORDER",
            // ArrayList methods
            "add",
            "remove",
            "get",
            "size",
            "clear",
            "ensureCapacity",
            // ArrayList fields
            "modCount",   // protected field in AbstractList
            "elementData", // protected transient Object[] in ArrayList
            // HashMap methods
            "put",
            "get",
            "remove",
            "size",
            "clear",
            "containsKey",
            // HashMap fields
            "DEFAULT_INITIAL_CAPACITY",
            "loadFactor",
            // Non-existent member for testing
            "nonexistentMember"
        };
        // Find the method/field
        Arrays.stream(memberNames)
              .map(member -> Arrays.stream(classNames)
        .map(cls -> {try {
            return findMember(member, Class.forName(cls));
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
                               })
        .filter(o -> o.optMember().isPresent())
        .findFirst())
        .filter(c -> c.isPresent())
        .forEach(c -> printMember(c.get()));
    }
}
