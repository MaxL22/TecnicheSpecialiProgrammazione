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
        NestedCallsI original = new NestedCalls();
        NestedCallsI proxy = (NestedCallsI) Proxy.newProxyInstance(original.getClass().getClassLoader(), original.getClass().getInterfaces(), new NestedHandler(original));

        System.out.println(proxy.a());
    }
}

