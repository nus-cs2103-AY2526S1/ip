package exceptions;

/** DukeyException handles specific checked Exceptions */
public class DukeyException extends Exception {

    /**
     * Creates new DukeyException.
     * Create checked exception with error message.
     *
     * @param message Associated error message.
     */
    public DukeyException(String message) {
        super(message);
    }


}
