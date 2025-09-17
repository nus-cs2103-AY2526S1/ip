package haru.exception;

/**
 * Exception thrown when a time format is invalid.
 */
public class InvalidTimeException extends HaruException {

    /**
     * Constructs an InvalidTimeException with the invalid alias.
     *
     * @param alias the name of the invalid time argument
     */
    public InvalidTimeException(String alias) {
        super(String.format("Eh?! The %s must be in a valid format!", alias));
    }
}
