package commands;

import storage.Storage;
import tasks.TaskList;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
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
}
