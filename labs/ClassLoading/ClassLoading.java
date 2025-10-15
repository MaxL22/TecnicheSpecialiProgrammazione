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

/*
 * Exec with:
 * javac NewAndImprovedClassLoader.java && \
  java -Djava.system.class.loader=NewAndImprovedClassLoader ClassLoading.java
 */
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ClassLoading {
    public static void main (String... args) {
        try {
            // Create a temporary file
            File file = File.createTempFile("demo_", ".txt");

            // Write some info to it
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            LocalDate today = LocalDate.now();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            writer.write("Today's date is: " + fmt.format(today));
            writer.newLine();

            InetAddress addr = InetAddress.getLocalHost();
            writer.write("Host name: " + addr.getHostName());
            writer.newLine();
            writer.write("Host address: " + addr.getHostAddress());
            writer.newLine();

            writer.close();

            Arrays.stream(new int[0]);
        } catch (IOException e) {
            System.out.println("[ERROR] " + e);
        }
    }
}
