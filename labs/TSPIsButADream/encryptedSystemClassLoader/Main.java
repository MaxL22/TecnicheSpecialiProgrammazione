/*
 * This is an example of a Class Loader able to decrypt classes at runtime
 *
 * Il file `Test1.nidki` è ottenuto nel seguente modo: dato un file chiamato `Test1.java` con il seguente contenuto

    public class Test {
      public String message() {
        return "Ciao mondo!";
      }
    }
 * questo va compilato e poi va eseguito
 * $ java Encrypt <chiave> Test
 * la chiave di default utilizzata nel main per decrittare è 10.
 *
 * Per usarlo come system class loader:
 * $ java -Djava.system.class.loader=EncryptedClassLoader Main
 *
 * Again, Thanks to TititinoThePrologGuy
 */

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("ClassLoader is: " + ClassLoader.getSystemClassLoader());
            // Test1 test1 = new Test1(); // encrypted class
            Test2 test2 = new Test2(); // normal class
            // System.out.println(test1.message());
            System.out.println(test2.message());
            return;
        } catch (Throwable e) {
            System.out.println(e);
        }
    }

}
