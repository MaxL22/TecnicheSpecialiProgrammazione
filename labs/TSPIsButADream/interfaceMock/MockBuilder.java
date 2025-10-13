import java.lang.invoke.MethodType;
import java.lang.invoke.WrongMethodTypeException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import java.lang.NoSuchMethodException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MockBuilder<T> {
    private final Class<T> inter;
    private final Map<Method, Iterator<Object>> methodReturnValues;

    private class MockHandler implements InvocationHandler {
        private final Map<Method, Iterator<Object>> methodReturnValues;

        public MockHandler(Map<Method, Iterator<Object>> methodReturnValues) {
            this.methodReturnValues = methodReturnValues;
        }

        public Object invoke(Object proxy, Method m, Object[] args) {
            Iterator<Object> it = this.methodReturnValues.get(m);
            if (Objects.isNull(it) || ! it.hasNext())
                return null;
            else
                return it.next();
        }
    }

    public MockBuilder(Class<T> inter) {
        Objects.requireNonNull(inter);
        if (! inter.isInterface())
            throw new IllegalArgumentException("Argument must be an interface");
        this.inter = inter;
        this.methodReturnValues = new HashMap<>();
    }

    private Method extractMethod(String name, MethodType mt) throws NoSuchMethodException {
        return this.inter.getMethod(name, mt.parameterArray());
    }

    private Method extractMethod(String name) throws NoSuchMethodException  {
        List<Method> methods = Arrays.stream(this.inter.getMethods())
                                     .filter((Method m) -> m.getName().equals(name))
                                     .limit(2)
                                     .collect(Collectors.toList());
        switch (methods.size()) {
        case 0:
            throw new NoSuchMethodException();
        case 1:
            return methods.get(0);
        default:
            throw new WrongMethodTypeException("Multiple available methods for \"" + name + "\" in interface \"" + this.inter.getName() + "\"");
        }
    }

    public MockBuilder methodReturns(String name, Iterator<Object> values) throws NoSuchMethodException {
        Method m = extractMethod(name);
        this.methodReturnValues.put(m, values);
        return this;
    }

    public MockBuilder methodReturns(String name, MethodType mt, Object... values) throws NoSuchMethodException {
        return this.methodReturns(name, mt, Arrays.stream(values).iterator());
    }

    public MockBuilder methodReturns(String name, Object... values) throws NoSuchMethodException {
        return this.methodReturns(name, Arrays.stream(values).iterator());
    }

    public MockBuilder methodReturns(String name, MethodType mt, Iterator<Object> values) throws NoSuchMethodException {
        Method m = extractMethod(name, mt);
        this.methodReturnValues.put(m, values);
        return this;
    }

    public T build() {
        return this.inter.cast(Proxy.newProxyInstance(
                                   this.inter.getClassLoader(),
                                   new Class<?>[] { this.inter },
                                   new MockHandler(this.methodReturnValues)));
    }
}
