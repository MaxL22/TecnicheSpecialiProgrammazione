/*
 * Simple guide to using MethodHandles
 * (Not made by me)
 */

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Main {

    public static class Test {
        public Integer publicTargetField;
        private Boolean privateTargetField;

        public Test(Integer i) {
            this.publicTargetField = i;
            this.privateTargetField = i == 69;
        }

        public String targetMethod(Integer i) {
            return "ciao";
        }

        public static Integer targetStaticMethod(Boolean i, Double k) {
            return 42;
        }
    }

    private static void simpleFieldReadExample() throws NoSuchFieldException, IllegalAccessException, Throwable {
        // Per prima cosa bisogna creare un handle
        MethodHandle targetField =
        // classe per fare il lookup
        MethodHandles.lookup()
                     // `findGetter` è per ottenere una reference readonly ad un campo
                     // gli argomenti sono: classe di appartenza, nome, tipo
                     .findGetter(Test.class, "publicTargetField", Integer.class);

        // Istanziamo l'oggetto su cui chiamare il getter
        Test subject = new Main.Test(10);

        // Infine possiamo invocarlo (in questo caso senza argomenti
        // visto che è un getter di un parametro
        System.out.println("simpleFieldReadExample: " + targetField.invoke(subject).toString());
    }

    private static void privateFieldReadExample() throws NoSuchFieldException, IllegalAccessException, Throwable {
        // Il procedimento è uguale per campi privati
        MethodHandle targetField = MethodHandles
        .lookup()
        .findGetter(Test.class, "privateTargetField", Boolean.class);

        Test subject = new Main.Test(10);

        System.out.println("privateFieldReadExample: " + targetField.invoke(subject).toString());
    }

    private static void privateFieldWriteExample() throws NoSuchFieldException, IllegalAccessException, Throwable {
        // Il procedimento è simile per cambiare il valore

        // Si istanzia un MethodHandle per il setter
        // che servirà a cambiare il valore
        MethodHandle targetFieldSetter = MethodHandles
        .lookup()
        .findSetter(Test.class, "privateTargetField", Boolean.class);
        // Si istanzia un MethodHandle per il getter
        // che servirà a leggere e stampare il valore
        MethodHandle targetFieldGetter = MethodHandles
        .lookup()
        .findGetter(Test.class, "privateTargetField", Boolean.class);

        Test subject = new Main.Test(10);

        // Si invoca il setter con argomenti oggetto e nuovo valore
        targetFieldSetter.invoke(subject, true);
        System.out.println("privateFieldWriteExample: " + targetFieldGetter.invoke(subject).toString());
    }

    private static void methodExample() throws NoSuchFieldException, IllegalAccessException, Throwable {
        // Anche per i metodi il procedimento è simile

        // Però serve un MethodType
        // questo si può costruire con il metodo statico `methodType`
        // che tra le altre cose può essere chiamato con argomenti
        // tipo di ritorno, tipi dei parametri
        MethodType type = MethodType.methodType(String.class, Integer.class);
        MethodHandle targetField =
        MethodHandles.lookup()
                     // `findVirtual` è per ottenere un handle ad un metodo dinamico
                     // gli argomenti sono: classe di appartenza, nome, tipo
                     .findVirtual(Test.class, "targetMethod", type);

        // Istanziamo l'oggetto su cui chiamare il getter
        Test subject = new Main.Test(10);

        // Infine possiamo invocarlo, come al solito passando
        // come primo argomento l'istanza di Test
        System.out.println("methodExample: " + targetField.invoke(subject, 10).toString());
    }

    private static void staticMethodExample() throws NoSuchFieldException, IllegalAccessException, Throwable {
        // Infine per metodi (e campi) statici la questione è
        // leggermente diversa, infatti non è più necessario passare
        // riferimenti all'istanza

        MethodType type = MethodType.methodType(Integer.class, Boolean.class, Double.class);
        MethodHandle targetField =
        MethodHandles.lookup()
                     // `findStatic` è per ottenere un handle ad un metodo dinamico
                     // `findStaticGetter` e `findStaticSetter` sono le versioni per
                     // i campi
                     // gli argomenti sono i soliti: classe di appartenza, nome, tipo
                     .findStatic(Test.class, "targetStaticMethod", type);

        System.out.println("staticMethodExample: " + targetField.invoke(true, 10.5).toString());
    }

    public static void main(String[] args) {
        try {
            simpleFieldReadExample();
            privateFieldReadExample();
            privateFieldWriteExample();
            methodExample();
            staticMethodExample();
        } catch (Throwable e) {
            System.out.println(e);
        }
        finally {
            return;
        }
    }
}
