import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.lang.invoke.*;

class FakeMethod {
    private String name;
    private int timesCalled;
    private Object[] values;

    public FakeMethod (String name, Object... args) {
        this.name = name;
        this.timesCalled = 0;
        this.values = args;
    }

    public Object getVal() {
        Object v = this.values[this.timesCalled];
        this.timesCalled = (this.timesCalled + 1) % this.values.length;
        return v;
    }

    public String getName () {
        return this.name;
    }
}

public class Mocker implements InvocationHandler {
    private Class interfaceToMock;
    private List<FakeMethod> fakeMethods;

    public Mocker(Class interfaceToMock) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.interfaceToMock = interfaceToMock;
        fakeMethods = new ArrayList<>();
    }

    boolean isInInterface(String methodName) {
        return Arrays.stream(interfaceToMock.getMethods())
               .map(m -> m.getName())
               .anyMatch(m -> m.equals(methodName));
    }

    public void MethodReturns(String methodName, Object... args) throws MethodNotInInterfaceException {
        if (isInInterface(methodName))
            fakeMethods.add(new FakeMethod(methodName, args));
        else
            throw new MethodNotInInterfaceException();
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Optional<FakeMethod> o = Arrays.stream(fakeMethods.toArray(new FakeMethod[0]))
                                       .filter(fm -> fm.getName().equals(m.getName()))
                                       .findFirst();
        if (o.isPresent()) {
            return o.get().getVal();
        } else {
            throw new YouHaveNotDefinedTheMethodException();
        }
    }
}
