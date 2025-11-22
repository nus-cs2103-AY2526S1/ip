package exceptions;

/**
 * A unique type of exception used by this chatBot. 
 */
public class ApunableException extends Exception {
    public ApunableException(String message) {
        super(message);
    }
}
