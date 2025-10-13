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
 *
 * Seconda versione (migliore tbh)
 * Si ringrazia TititinoThePrologGuy
 */

import java.lang.invoke.MethodType;
import java.lang.NoSuchMethodException;

public class Main {
    public interface Test {
        String a();
        String a(int b);
        Double b(String a, String b);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Test mock = (Test) new MockBuilder<>(Test.class)
        .methodReturns("a", MethodType.methodType(String.class), "ciao", "mamma")
        .methodReturns("a", MethodType.methodType(String.class, int.class), "miao", "bau")
        .build();

        System.out.println(mock.a());
        System.out.println(mock.a());
        System.out.println(mock.a());
        System.out.println(mock.a(1));
        System.out.println(mock.a(2));
        System.out.println(mock.a(3));
        System.out.println(mock.b("boia", "dai"));
    }
}
