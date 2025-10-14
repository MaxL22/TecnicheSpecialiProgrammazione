/*
 * Tail Recursion: if recursion is detected,
 * collapse the stack then proceed with the recursion
 *
 * Once again, thanks TititinoThePrologGuy
 */

public class Main {
    public static void main(String[] args) {
        Test t2 = new TestTCO();
        Test t1 = new Test();

        try {
            System.out.println("Without TCO: ");
            System.out.println(t1.recursive(10000));
        } catch (StackOverflowError e) { // Believe me, it's gonna happen
            System.out.println(e);
        }

        System.out.println("With TCO: ");
        System.out.println(t2.recursive(10000));

        return;
    }
}

