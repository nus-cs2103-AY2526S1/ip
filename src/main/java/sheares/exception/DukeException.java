package sheares.exception;

/**
 * exception specific to program, other exceptions extend from here
 */
public class DukeException extends Exception {
    private final String msg;
    public DukeException() {
        this.msg = "    File not loaded ";
    }
    public String getMessage() {
        return this.msg;
    }
}
