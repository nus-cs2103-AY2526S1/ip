package zell.exception;

/**
 * Represents exceptions for the Zell chatbot
 */
public class ZellException extends Exception{
    public ZellException(String message) {
        super(message);
    }

    /**
     * Overrides the default toString
     * <p>
     * Displays the exception message with an additional {@code An error occured: } infront.
     * </p>
     * @return The toString format of the ZellException
     */
    @Override
    public String toString() {
        return "An error occurred: " + getMessage();
    }
}
