package sheares.exception;

/**
 * exception thrown when user input not recognised
 */
public class WrongInputException extends DukeException {
    private final String msg;
    public WrongInputException() {
        this.msg = "    Input not recognised";
    }
    @Override
    public String getMessage() {
        return this.msg;
    }
}
