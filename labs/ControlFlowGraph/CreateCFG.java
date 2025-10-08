/*
 * Exercise: Control Flow Graph.
 *
 * Let's write an application that can build the control flow graph of the calls of another application.
 * A control flow graph is a directed cyclic graph whose nodes represent the application's classes
 * and the edges represent a call from an instance of the class represented by the source node
 * to an instance of the class represented by the destination node of the edge;
 * the edge is labeled with the method name.
 *
 * The code of the application under analysis is decorated so that
 * all the needed data to build the control from graph is available from the annotations.
 * In particular, each method will be annotated with the data about the methods it invokes and the pertaining classes.
 *
 * The application we have to write will receive from the command line all the names of the classes composing the application under analysis.
 * The application will analyze the annotation of the application under analysis
 * and it will build the control flow graph of all calls independently that these are effectively invoked.
 * The graph will show only the dependencies that can be detected statically.
 * To define the needed annotations and decorate the application under analysis is part of the exercise.
 *
 * As per the output you can either run a depth-first visit and output it on the terminal as a list of arcs,
 * output a dot file (from GraphWiz) and process it with dot to generate a pic.
 */
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Optional;

public class CreateCFG {
    public static void main (String... args) {
        ControlFlowGraph[] graphs;
        graphs = Arrays.stream(Arrays.stream(args)
        .map(c -> {
            try {
                return Optional.of(Class.forName(c));
            }
            catch (ClassNotFoundException e) {
                System.out.println("Class " + c + " not found.");
                return Optional.empty();
            }
            catch (Exception e) {
                System.out.println("[ERROR] " + e);
                throw new RuntimeException();
            }
        })
        .filter(o -> o.isPresent())
        .map(o -> o.get())
        .toArray(Class[]::new))
        .map(c -> new ControlFlowGraph(c))
        .toArray(ControlFlowGraph[]::new);

        // TODO: output
    }
}
