import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

public class ControlFlowGraph {
    Class originalClass;
    Method[] nodes;
    boolean[][] edges;

    public ControlFlowGraph (Class cls) {
        this.originalClass = cls;
        this.nodes = cls.getDeclaredMethods();

        // TODO: For each class, get the annotations, populate the matrix with the corresponding values
        this.edges = new boolean[nodes.length][nodes.length];
    }

}
