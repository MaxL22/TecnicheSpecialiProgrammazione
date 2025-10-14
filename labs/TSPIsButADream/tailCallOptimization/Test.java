import java.lang.StackWalker;

public class Test {
    public Integer recursive(Integer i) {
        return recursive(i, 0);
    }

    private static void printStackHeight() {
        // System.out.println("Stack height: " + StackWalker.getInstance().walk(s -> s.count()).toString());
    }

    public Integer recursive(Integer i, Integer acc) {
        printStackHeight();
        if (i == 0)
            return acc;
        else
            return recursive(i - 1, acc + i);
    }
}
