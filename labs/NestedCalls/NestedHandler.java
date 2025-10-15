import java.lang.invoke.*;
import java.lang.reflect.*;

public class NestedHandler extends NestedCalls implements InvocationHandler {
    private NestedCallsI proxy;
    private int counter = 0;

    public NestedHandler() {
        this.proxy = (NestedCallsI) Proxy.newProxyInstance(NestedCallsI.class.getClassLoader(),
                     new Class[] {NestedCallsI.class},
                     this);
    }

    public Object invoke(Object proxy, Method m, Object[] args) {
        Object ret = null;
        try {
            counter++;
            System.out.println("Level: " + (new StringBuilder()).repeat("#", counter));
            ret = MethodHandles.lookup()
                  .unreflectSpecial(NestedCalls.class.getDeclaredMethod(m.getName(), m.getParameterTypes()), getClass())
                  .bindTo(this)
                  .invokeWithArguments(args);
            counter--;
        } catch (Throwable e) {
            System.out.println("[ERROR] " + e);
        }

        return ret;
    }

    public int a() {
        return proxy.a();
    }
    public int b(int a) {
        return proxy.b(a);
    }
    public int c(int a) {
        return proxy.c(a);
    }

}
