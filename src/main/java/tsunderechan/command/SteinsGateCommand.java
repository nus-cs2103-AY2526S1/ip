package tsunderechan.command;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a command to show a response to user typing "El Psy Congroo".
 * Just a little Easter Egg for users who recognise the characters in the profiles.
 */
public class SteinsGateCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showSteinsGate();
    }
}
