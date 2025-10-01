package cat;

public class Exception extends java.lang.Exception {

    // Constructor for the exception
    public Exception(String message) {
        super(message);
    }

    /**
     * Static method to check for empty input.
     * Throws DukeException if input is empty.
     */
    public static void checkEmptyInput(String input) throws Exception {
        if (input == null || input.trim().isEmpty()) {
            throw new Exception("The input cannot be empty!\n");
        }
    }
}
