/*
 * Just a CL Test
 * What does the CL actually do?
 * Why are the classes printed different by removing the second part of the class loader?
 */
public class EmptyProgram {
    public static void main(String[] args) {
        System.out.println("Class loader is: " + ClassLoader.getSystemClassLoader());
    }
}
