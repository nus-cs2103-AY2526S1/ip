package commands;

import ui.Ui;

/**
 * A command that represents an error (invalid input, parsing failure, etc.).
 * It stores the error message and ensures Rainy can display it consistently.
 */
public class ErrorCommand extends Command {
    private final String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(Object... args) {
        Ui ui = (Ui) args[1];
        ui.showError(message);
    }

    @Override
    public boolean isExit() {
        return false; // never exits
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
