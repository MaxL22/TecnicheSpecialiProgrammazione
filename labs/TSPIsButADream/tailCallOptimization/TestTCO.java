import java.util.Arrays;

public class TestTCO extends Test {
    private static boolean isRecursiveCall() {
        return StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
               .walk(s -> s.skip(2).anyMatch(x -> x.getDeclaringClass().equals(TestTCO.class)));
    }

    @Override
    public Integer recursive(Integer i, Integer acc) {
        if (isRecursiveCall())
            throw new SendBackArguments(new Object[] {i, acc});

        Boolean keepGoing = true;
        Integer result = null;
        Integer argI = i;
        Integer argAcc = acc;

        while (keepGoing) {
            try {
                keepGoing = false;
                result = super.recursive(argI, argAcc);
            } catch (SendBackArguments e) {
                keepGoing = true;
                argI = (Integer) e.args[0];
                argAcc = (Integer) e.args[1];
            }
        }

        return result;
    }
}
