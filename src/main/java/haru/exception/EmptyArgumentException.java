package haru.exception;

/**
 * Exception thrown when a required argument is empty.
 */
public class EmptyArgumentException extends HaruException {

    /**
     * Constructs an EmptyArgumentException with the missing alias.
     *
     * @param alias the name of the missing argument
     */
    public EmptyArgumentException(String alias) {
        super(String.format("Eh?! The %s cannot be empty!", alias));
    }
}
