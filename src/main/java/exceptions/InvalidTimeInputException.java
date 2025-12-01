package exceptions;

/**
 * Exception thrown when an invalid time input is provided.
 */
public class InvalidTimeInputException extends RuntimeException {

    /**
     * Constructs an error
     * Prints specific message
     */
    public InvalidTimeInputException() {
        super("Candy needs the recipe right!"
                + "\nPlease input in the format: 'dd-mm-yyyy HHmm' "
                + "\nIf date/month < 10, remember to add '0' at the front!");
    }
}
