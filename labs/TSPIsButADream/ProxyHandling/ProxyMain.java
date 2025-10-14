import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.*;
import java.util.Arrays;

public class ProxyMain implements InvocationHandler {
    private Object b;

    public ProxyMain(Object base) {
        this.b = base;
    }

    public static void fieldPrint (Object o) throws IllegalAccessException {
        Arrays.stream(o.getClass().getDeclaredFields())
        .forEach((Field f) -> {
            try {
                // These do the same thing, but one does a private lookup,
                // the other unreflectes the field, with access given
                System.out.println(MethodHandles.privateLookupIn(ProxyTestingFields.class, MethodHandles.lookup())
                                   .findGetter(ProxyTestingFields.class, f.getName(), f.getType())
                                   .invoke(o));
                f.setAccessible(true);
                System.out.println(MethodHandles.lookup()
                                   .unreflectGetter(f)
                                   .invoke(o));
            }
            catch (Throwable e) {
                System.out.println("[ERROR] " + e);
            }
        });
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            System.out.println("Before");
            fieldPrint(this.b);

            Object v = method.invoke(this.b, args);

            System.out.println("After");
            fieldPrint(this.b);

            return v;
        } catch (Exception e) {
            System.out.println("[ERROR] " + e);
            return null;
        }
    }

    public static void main (String... args) {
        ITestingFields p = new ProxyTestingFields(1, 2);
        ITestingFields pp = (ITestingFields) Proxy.newProxyInstance(p.getClass().getClassLoader(),
                            p.getClass().getInterfaces(),
                            new ProxyMain(p));

        System.out.println("Normal call");
        p.message();

        System.out.println();
        System.out.println("Proxy call");
        pp.message();

    }
}
