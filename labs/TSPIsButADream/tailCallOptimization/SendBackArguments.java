public class SendBackArguments extends RuntimeException {
    public final Object[] args;

    public SendBackArguments(Object[] args) {
        this.args = args;
    }
}
