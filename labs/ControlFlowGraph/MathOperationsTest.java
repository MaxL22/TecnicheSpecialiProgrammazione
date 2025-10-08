public class MathOperationsTest {

    // testCalculate() calls computeSum() and computeAverage()
    @Invokes(methodName = "computeSum", callerMethod = "testCalculate")
    @Invokes(methodName = "computeAverage", callerMethod = "testCalculate")
    public void testCalculate() {
        computeSum();
        computeAverage();
    }

    // computeAverage() calls computeSum() and countElements()
    @Invokes(methodName = "computeSum", callerMethod = "computeAverage")
    @Invokes(methodName = "countElements", callerMethod = "computeAverage")
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
