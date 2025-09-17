package haru.exception;

/**
 * Exception thrown when an option is unknown.
 */
public class UnknownOptionException extends HaruException {

    /**
     * Constructs an UnknownOptionException with the unknown option name.
     *
     * @param name the unknown option name
     */
    public UnknownOptionException(String name) {
        super(String.format("Eh?! I don't recognize the /%s option!", name));
    }
}
