package command;

import sunday.Storage;
import sunday.TaskList;
import sunday.Ui;

/**
 * Command to say bye.
 */
public class ByeCommand extends Command {

    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
    }

    public boolean isExit() {
        return true;
    }
}
