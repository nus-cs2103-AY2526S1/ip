package sheares.exception;

/**
 * thrown when user input is incorrect e.g event party, where from and by not given
 */
public class NumberOfArgumentsException extends DukeException {
    private final String msg;
    public NumberOfArgumentsException(String command) {
        this.msg = "    Wrong number of arguments to " + command;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
