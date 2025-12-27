package ip.ui;

import java.io.FileNotFoundException;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;

/**
 * Represents an error handler
 */
public class ErrorHandler {

    private enum ErrorCode {
        UNKNOWN,
        NOT_FOUND,
        CORRUPT,
        NONE
    }

    private final Storage storage;
    private final Ui ui;

    public ErrorHandler(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Handles exceptions based on which type of
     * exception is input
     * @param e Exception to be handled
     * @return Message based on the type of exception
     */
    public String handleError(Exception e) {
        String msg = e.getMessage();
        ErrorCode code;
        if (e instanceof UnknownInputException) {
            code = ErrorCode.UNKNOWN;
        } else if (e instanceof FileCorruptedException) {
            code = ErrorCode.CORRUPT;
        } else if (e instanceof FileNotFoundException) {
            code = ErrorCode.NOT_FOUND;
        } else {
            code = ErrorCode.NONE;
        }

        switch (code) {
        case UNKNOWN:
            return ui.showUnknownInputMsg(msg);

        case NOT_FOUND:
            storage.start();
            return ui.showFileNotFoundMsg(msg);

        case CORRUPT:
            return ui.showFileCorruptedMsg(msg);

        case NONE:
            return ui.showOtherError(e.getMessage());

        default:
            assert false : "This is not possible";
            return "";
        }
    }
}
