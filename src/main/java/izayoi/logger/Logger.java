package izayoi.logger;

/**
 * Represents an output for any string
 */
public interface Logger {

    /**
     * Logs the string to the output for this logger
     * @param s the String to be logged
     */
    public abstract void log(String s);
}
