package shaniqua.command;

import shaniqua.ShaniquaException;

public class CommandFailException extends ShaniquaException {
    /**
     * Constructs a CommandFailException with the specified error message.
     *
     * @param msg the error message describing why the command failed
     */
    CommandFailException(String msg) {
        super("Command Failed: " + msg);
    }
}
