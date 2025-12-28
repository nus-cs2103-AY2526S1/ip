package tsunderechan.command;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a command to show a response to user typing "The world is ending".
 * Just a little Easter Egg for users who recognise the characters in the profiles.
 */
public class WorldIsEndingCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showWorldIsEnding();
    }
}
