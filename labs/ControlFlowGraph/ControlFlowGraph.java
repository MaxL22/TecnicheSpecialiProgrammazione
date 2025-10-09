import java.lang.reflect.*;
import java.util.Arrays;

public class ControlFlowGraph {
    record Edge (String origClass, String destClass, String methodName) {}

    Class<?>[] nodes;
    Edge[] edging;

    public ControlFlowGraph (Class<?>[] cls) {
        this.nodes = cls;
        this.edging = Arrays.stream(this.nodes)
                      .flatMap((Class<?> c) -> Arrays.stream(c.getDeclaredMethods()))
                      .filter((Method m) -> m.isAnnotationPresent(Invocations.class))
                      .flatMap((Method m) -> Arrays.stream(m.getAnnotation(Invocations.class).value())
                               .map((Invokes ann )-> new Edge(m.getDeclaringClass().getName(), ann.calledClass(), ann.calledMethod())))
                      .toArray(Edge[]::new);

    }

    public String toDot() {
        StringBuilder s = new StringBuilder();
        s.append("digraph G {");
        Arrays.stream(this.edging)
              .forEach(edge -> s.append(edge.origClass + " -> " + edge.destClass + " [label=" + edge.methodName + "];"));
        s.append("}");

        return s.toString();
    }

}
