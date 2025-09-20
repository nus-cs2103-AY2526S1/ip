package balloon.command;

import balloon.logic.Balloon;
import balloon.logic.Storage;
import balloon.logic.TaskList;
import javafx.application.Platform;

/**
 * Represents a command that terminates the app and exits.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Balloon balloon) {
        Platform.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getString() {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
