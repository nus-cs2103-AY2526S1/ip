package burgerburglar;

/**
 * Represents a custom exception specific to BurgerBurglar.
 * Thrown when invalid input or operations occur in the application.
 */
public class BurgerException extends Exception {
    public BurgerException(String message) {
        super(message);
    }
}
