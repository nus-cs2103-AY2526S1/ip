package hachiware;

/**
 * Custom exception class for the Hachiware chatbot.
 * Used to indicate errors specific to Hachiware commands and operations.
 */
public class HachiwareException extends Exception {

    /**
     * Constructs a new HachiwareException with a specific detail message.
     *
     * @param message the detail message about the cause of the exception
     */

    public HachiwareException(String message) {
        super(message);
    }
}
