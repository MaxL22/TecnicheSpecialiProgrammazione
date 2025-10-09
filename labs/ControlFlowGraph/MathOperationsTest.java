public class MathOperationsTest {
    public MathOperationsTest () {
    }

    // testCalculate() calls computeSum() and computeAverage()
    @Invokes(calledMethod = "computeSum", calledClass = "MathOperationsTest")
    @Invokes(calledMethod = "computeAverage", calledClass = "MathOperationsTest")
    public void testCalculate() {
        computeSum();
        computeAverage();
    }

    // computeAverage() calls computeSum() and countElements()
    @Invokes(calledMethod = "computeSum", calledClass = "MathOperationsTest")
    @Invokes(calledMethod = "countElements", calledClass = "MathOperationsTest")
    public void computeAverage() {
        computeSum();
        countElements();
    }

    public void computeSum() {
        System.out.println("Computing sum...");
    }

    public void countElements() {
        System.out.println("Counting elements...");
    }
}
