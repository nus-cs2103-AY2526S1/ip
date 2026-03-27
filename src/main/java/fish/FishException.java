package fish;

/**
 * Represents checked exceptions thrown within the Fish application.
 */
public class FishException extends Exception {

    /**
     * Constructs a {@code FishException} with the specified detail message.
     *
     * @param message detail message describing the error condition
     */
    public FishException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
