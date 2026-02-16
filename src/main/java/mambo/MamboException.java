package mambo;

/**
 * Represents a runtime exception which could be thrown that is specific to the mambo chatbot
 *
 * @author kentalim2
 */
public class MamboException extends Exception {

    public MamboException(String message) {
        super(message);
    }
}
