/*
 * Exercise: Class Loading.
 *
 * Let's extend the Java class loader so that:
 * 1. It prints how many system (those from the packages java.*)
 * and user-defined classes have been loaded.
 * Obviously the main() method of the test class must load
 * and use several classes so that the test could be considered meaningful.
 * As an extension, let's consider to print also the name of the loaded classes.
 * Note that you have to disable the pre-loading of some system classes.
 *
 * 2. It raises a RuntimeException when there is an attempt to use
 * one of the classes defined into the java.lang and java.util packages.
 * Note that the JVM pre-loads many of the classes from these packages
 * and this can distort the final result of your program:
 * let's find a way to take rid of such a behavior
 */

public class ClassLoading {
    public static void main (String... args) {

    }
}
