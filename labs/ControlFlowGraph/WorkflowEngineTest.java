public class WorkflowEngineTest {
    private static final MathOperationsTest m = new MathOperationsTest();

    // testWorkflowExecution() calls initializeWorkflow(), executeWorkflow(), finalizeWorkflow()
    @Invokes(calledMethod = "initializeWorkflowExecution", calledClass = "WorkflowEngineTest")
    @Invokes(calledMethod = "executeWorkflow", calledClass = "WorkflowEngineTest")
    @Invokes(calledMethod = "finalizeWorkflow", calledClass = "WorkflowEngineTest")
    public void testWorkflowExecution() {
        initializeWorkflow();
        executeWorkflow();
        finalizeWorkflow();
    }

    // initializeWorkflow() calls loadConfiguration()
    @Invokes(calledMethod = "loadConfiguration", calledClass = "WorkflowEngineTest")
    public void initializeWorkflow() {
        loadConfiguration();
        System.out.println("Workflow initialized.");
    }

    // executeWorkflow() calls processStep() and logProgress()
    @Invokes(calledMethod = "processStep", calledClass = "WorkflowEngineTest")
    @Invokes(calledMethod = "logProgress", calledClass = "WorkflowEngineTest")
    public void executeWorkflow() {
        processStep();
        logProgress();
    }

    public void finalizeWorkflow() {
        System.out.println("Workflow finalized.");
    }

    public void loadConfiguration() {
        System.out.println("Configuration loaded.");
    }

    public void processStep() {
        System.out.println("Processing step...");
    }

    public void logProgress() {
        System.out.println("Progress logged.");
    }

    @Invokes(calledMethod = "computeSum", calledClass = "MathOperationsTest")
    public static void useSmthng() {
        m.computeSum();
    }

    @Invokes(calledMethod = "computeAverage", calledClass = "MathOperationsTest")
    public static void useSmthng2() {
        m.computeAverage();
    }

    @Invokes(calledMethod = "countElements", calledClass = "MathOperationsTest")
    public static void useSmthng3() {
        m.countElements();
    }
}
