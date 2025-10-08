/*
 * Exercise: Testing Fields.
 *
 * Let's write a program that analyses the state of a generic class.
 * As a test case let's use the following class (code below)
 * and one instance with n=7 and val=3.14.
 * The state of the instance is defined as the collection of the values of all the instance fields
 * (both static, non static, public and private).
 * Let's change (through reflection) the value of s to testing ... passed!!!.
 */
import java.util.Date;
import java.lang.reflect.*;

public class TestingFields {
    private Double d[];
    private Date dd;
    public static final int i = 42;
    protected static String s = "testing ...";

    public TestingFields(int n, double val) {
        dd = new Date();
        d = new Double[n];
        for(int i=0; i<n; i++) {
            d[i] = i*val;
        }
    }
    // Above this is the starting code

    public static void main (String... args) {
        // Given instance
        TestingFields test = new TestingFields(7, 3.14);
        try {
            // Get the field
            Field s = test.getClass().getDeclaredField("s");
            // Set it as accessible and set the value in the instance
            s.setAccessible(true);
            s.set(test, "testing ... passed!!!");
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.toString());
        }
        // Static print, if not static use the instance (works anyway)
        System.out.println(TestingFields.s);
    }
}
