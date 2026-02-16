package ryuji.ui;

/**
 * The {@code RyujiException} class represents exceptions specific to the Ryuji chatbot application.
 * It extends the built-in {@link Exception} class and is used to signal application-specific errors,
 * such as invalid input or issues loading files.
 */
public class RyujiException extends Exception {

    /**
     * Constructs a {@code RyujiException} with the specified error message.
     *
     * @param message the detailed error message
     */
    public RyujiException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "I didn't understand your instructions master: " + super.getMessage();
    }
}
