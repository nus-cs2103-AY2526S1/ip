package chiikawa.command;

import chiikawa.Storage;
import chiikawa.TaskList;
import chiikawa.Ui;

/**
 * Class for when user inputs an invalid command.
 */
public class InvalidCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showInvalid();
    }
}
