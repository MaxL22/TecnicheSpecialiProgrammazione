/*
 * Exercise: Simple Proxy.
 *
 * Let's create a dynamic proxy that intercepts method calls to an interface.
 * Write a proxy that:
 *  - Logs every method invocation (name and arguments).
 */

/*
 * I know it's not too difficult (it literally couldn't be simpler)
 * It's more of a to-do list in order to use a Proxy
 */

import java.lang.reflect.*;

interface ITesting {
    public void smthng ();
}

class Test implements ITesting {
    public void smthng () {
        System.out.println("Smthng");
    }
}

class SimpleProxy implements InvocationHandler {
    private Object base;

    public SimpleProxy (Object o) {
        this.base = o;
    }

    public Object invoke(Object proxy, Method m, Object[] args) {
        System.out.println("Method name: " + m.getName());
        System.out.println("Declaring class: " + m.getDeclaringClass());
        try {
            return m.invoke(this.base, args);
        } catch (Exception e) {
            System.out.println("[ERROR] " + e);
            return null;
        }
    }
}

public class SimpleProxyMain {
    public static void main (String... args) {
        ITesting orig = new Test();
        ITesting proxy = (ITesting) Proxy.newProxyInstance(orig.getClass().getClassLoader(),
                         orig.getClass().getInterfaces(),
                         new SimpleProxy(orig));

        System.out.println("Original:");
        orig.smthng();
        System.out.println("");

        System.out.println("Proxy-ed:");
        proxy.smthng();

    }
}
