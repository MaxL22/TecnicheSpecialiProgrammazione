public class WorkflowEngineTest {

    // testWorkflowExecution() calls initializeWorkflow(), executeWorkflow(), finalizeWorkflow()
    @Invokes(methodName = "initializeWorkflow", callerMethod = "testWorkflowExecution")
    @Invokes(methodName = "executeWorkflow", callerMethod = "testWorkflowExecution")
    @Invokes(methodName = "finalizeWorkflow", callerMethod = "testWorkflowExecution")
    public void testWorkflowExecution() {
        initializeWorkflow();
        executeWorkflow();
        finalizeWorkflow();
    }

    // initializeWorkflow() calls loadConfiguration()
    @Invokes(methodName = "loadConfiguration", callerMethod = "initializeWorkflow")
    public void initializeWorkflow() {
        loadConfiguration();
        System.out.println("Workflow initialized.");
    }

    // executeWorkflow() calls processStep() and logProgress()
    @Invokes(methodName = "processStep", callerMethod = "executeWorkflow")
    @Invokes(methodName = "logProgress", callerMethod = "executeWorkflow")
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
}
