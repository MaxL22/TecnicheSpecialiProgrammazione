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

// Interface to mimic
interface ITesting {
    public void smthng ();
}

// Concrete class implementing the interface
class Test implements ITesting {
    public void smthng () {
        System.out.println("Smthng");
    }
}

// Proxy class, implements the invoke()
class SimpleProxy implements InvocationHandler {
    private Object base;

    public SimpleProxy (Object o) {
        this.base = o;
    }

    public Object invoke(Object proxy, Method m, Object[] args) {
        Object ret = null;
        System.out.println("Method name: " + m.getName());
        System.out.println("Declaring class: " + m.getDeclaringClass());
        try {
            ret = m.invoke(this.base, args);
        } catch (Exception e) {
            System.out.println("[ERROR] " + e);
        }
        return ret;
    }
}

/*
 * The proxy has to be created through
 * Proxy.newProxyInstance(ClassLoader, Interfaces, Handler), where
 *  - ClassLoader is a class loader (just use the one of a base object)
 *  - Interfaces is a Class<?>[] containing all interfaces to be mimicked
 *  - Handler is an instance of the Proxy class
 *
 * The proxy can then use all the methods of the interface mimicked,
 * returning the value given by the defined invoke()
 */
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
