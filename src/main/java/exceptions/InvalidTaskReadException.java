package exceptions;

public class InvalidTaskReadException extends RuntimeException {
    /**
     * Constructs an error
     * Prints specific message
     */
    public InvalidTaskReadException() {
        super("Oh no! Your stored candies has melted. Candy can't distinguish them!"
                + "\nPlease check your storage data file!");
    }
}
