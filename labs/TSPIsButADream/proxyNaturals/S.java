import java.util.Objects;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class S implements InvocationHandler {
    private final Nat prec;
    public S(Nat prec) {
        Objects.requireNonNull(prec);
        this.prec = prec;
    }

    public Object invoke(Object proxy, Method m, Object[] args) {
        if (m.getName().equals("number")) {
            return 1 + prec.number();
        } else if (m.getName().equals("add")) {
            // Sx + y = S(x + y)
            return Nats.succ(this.prec.add((Nat) args[0]));
        }

        return null;
    }
}
