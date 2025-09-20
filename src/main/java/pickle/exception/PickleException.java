package pickle.exception;

/**
 * PickleException is thrown when user input is invalid and not to be expected.
 */
public class PickleException extends Exception {

    public PickleException(String description) {
        super(description);
    }
}
