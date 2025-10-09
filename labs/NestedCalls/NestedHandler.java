import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class NestedHandler implements InvocationHandler {
    private int counter = 0;
    private Object base;

    public NestedHandler(Object b) {
        this.base = b;
    }

    public Object invoke(Object proxy, Method m, Object[] args) {


        return new Object();
    }
}
