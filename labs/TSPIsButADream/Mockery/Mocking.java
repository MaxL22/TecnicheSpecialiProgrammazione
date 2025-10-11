/*
 * Un mock è un oggetto sostanzialmente vuoto
 * che appare come avente una certa classe (nel nostro caso interfaccia).
 *
 * Scrivere una classe che data una interfaccia (o, se vuoi, una lista di interfacce)
 * ritorni un oggetto che le impersona,
 * si deve essere in grado di hardcodare il comportamento dei metodi del mock
 * ad esempio specificando una lista di valori che dovranno ritornare.
 * Alla chiamata n deve tornare il valore fornito in posizione n mod len(valori).
 *
 * Un esempio in pseudocodice può essere
 *     a = new Mock(<interfaccia>)
           .methodReturns("a", 1, 2, 3)
           .methodReturns("b", true, false);
 *
 * a(10)
 *  > 1
 * a(20)
 *  > 2
 * b()
 *  > true
 * ...
 */

import java.lang.reflect.*;

class Testing implements ITest {
    public int method1 () {
        return 1;
    }
    public int method2 () {
        return 2;
    }
    public int method3 () {
        return 3;
    }
}

public class Mocking {
    static public void main (String... args) {
        try {

            ITest orig = new Testing();

            Mocker m = new Mocker(orig.getClass().getInterfaces()[0]);
            m.MethodReturns(ITest.class.getMethods()[0].getName(), 2, 3);
            ITest proxy = (ITest) Proxy.newProxyInstance(orig.getClass().getClassLoader(),
                          orig.getClass().getInterfaces(),
                          m);

            System.out.println("Original:");
            System.out.println("Method 1 " + orig.method1());
            System.out.println("Method 1 " + orig.method1());
            System.out.println("Method 2 " + orig.method2());

            System.out.println();
            System.out.println("Mocked:");
            System.out.println("Method 1 " + proxy.method1());
            System.out.println("Method 1 " + proxy.method1());
            System.out.println("Method 2 " + proxy.method2());

        }
        catch (UndeclaredThrowableException e) {
            Throwable cause = e.getUndeclaredThrowable();
            cause.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("[ERROR] " + e);
            throw new RuntimeException();
        }
    }
}
