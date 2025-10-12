/*
 * Compose multiple proxy layers
 * e.g., logging and caching
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

interface ITest {
    public int getValue();
    public void smthng();
}

class Test implements ITest {
    private int v = 42;

    public int getValue () {
        return v;
    }
    public void smthng() {
        System.out.println(this.getClass());
    }
}

class LogProxy implements InvocationHandler {
    Object base;

    public LogProxy (Object b) {
        this.base = b;
    }

    public Object invoke (Object proxy, Method m, Object[] args) throws IllegalAccessException, InvocationTargetException {
        System.out.println("Before " + m);
        Object r = m.invoke(this.base, args);
        System.out.println("After " + m);
        return r;
    }
}

// Caches the getters
class CacheProxy implements InvocationHandler {
    private Object base;
    private HashMap<String, Object> cache = new HashMap<>();

    public CacheProxy (Object b) {
        this.base = b;
    }

    public Object invoke (Object proxy, Method m, Object[] args) throws IllegalAccessException, InvocationTargetException {
        if (!m.getName().startsWith("get")) {
            return m.invoke(this.base, args);
        }

        String key = m.getName();
        if (cache.containsKey(key)) {
            System.out.println("[CACHE] Returning cached value.");
            return cache.get(key);
        }

        Object r = m.invoke(this.base, args);
        cache.put(key, r);
        return r;
    }
}

public class ProxyDaisyChain {
    public static void main (String... args) {
        ITest orig = new Test();
        ITest proxy = (ITest) Proxy.newProxyInstance(orig.getClass().getClassLoader()
                      ,orig.getClass().getInterfaces(),
                      new LogProxy((ITest) Proxy.newProxyInstance(orig.getClass().getClassLoader(),
                                   orig.getClass().getInterfaces(),
                                   new CacheProxy(orig))));

        System.out.println("Original:");
        orig.smthng();
        System.out.println(orig.getValue());
        System.out.println(orig.getValue());
        System.out.println();

        System.out.println("Proxy:");
        proxy.smthng();
        System.out.println(proxy.getValue());
        System.out.println(proxy.getValue());
    }
}
