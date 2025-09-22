package izayoi.logger;

/**
 * Represents a logger that outputs to nowhere
 */
public class VoidLogger implements Logger {
    @Override
    public void log(String string) {}
}
