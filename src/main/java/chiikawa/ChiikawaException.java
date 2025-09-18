package chiikawa;

/**
 * Exception that is catered to the chatbot ChiikawaException.
 */
public class ChiikawaException extends Exception {
    /**
     * Constructor to create a new instance of ChiikawaException.
     *
     * @param message The string representation of the exception
     */
    public ChiikawaException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
