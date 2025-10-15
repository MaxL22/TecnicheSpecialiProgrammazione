import java.lang.reflect.Proxy;

public class Nats {

    public static Nat zero() {
        return Nat.Z.SINGLETON;
    }

    public static Nat succ(Nat prec) {
        return (Nat) Proxy.newProxyInstance(
                   prec.getClass().getClassLoader(),
                   new Class<?>[] { Nat.class },
                   new S(prec));
    }

    public static Nat fromInteger(Integer n) {
        return n == 0 ? Nats.zero() : Nats.succ(fromInteger(n - 1));
    }
}
