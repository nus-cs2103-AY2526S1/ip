package shaduke;

/**
 * Represents the exceptions unique to this application.
 */
public class ShadukeException extends RuntimeException {
    public ShadukeException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "Sorry! " + super.getMessage();
    }
}
