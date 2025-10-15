/*
 * Exercise: Nested Calls
 *
 * Let's write a meta-object (through the Proxy class)
 * to link to the instances of the below class
 * that shows the nesting level at each method invocation.
 * Hint: inheritance and polymorphism are your friends.
 */

import java.lang.reflect.Proxy;

public class NestedCalls implements NestedCallsI {
    private int i = 0;

    public int a() {
        return b(i++);
    }
    public int b(int a) {
        return (i < 42) ? c(b(a())) : 1;
    }
    public int c(int a) {
        return --a;
    }

    public static void main(String... args) {
        NestedCallsI orig = new NestedCalls();
        NestedCallsI p = (NestedCallsI) Proxy.newProxyInstance(NestedCallsI.class.getClassLoader(),
                         new Class[] {NestedCallsI.class},
                         new NestedHandler());

        System.out.println("Normal call:");
        System.out.println("a(): " + orig.a());
        System.out.println("b(a()): " + orig.b(orig.a()));
        System.out.println("c(b(a())): " + orig.c(orig.b(orig.a())));
        System.out.println();
        System.out.println("Proxy call:");
        System.out.println("a(): " + p.a());
        System.out.println("b(a()): " + p.b(orig.a()));
        System.out.println("c(b(a())): " + p.c(orig.b(orig.a())));
    }
}

