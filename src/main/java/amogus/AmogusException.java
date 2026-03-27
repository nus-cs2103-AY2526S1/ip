package amogus;

/**
 * Represents an exception when incorrect input has been
 * fed to the chatbot
 */
public class AmogusException extends Exception {

    public AmogusException(String message) {
        super(message);
    }

}
