package chiikawa.command;

import chiikawa.Storage;
import chiikawa.TaskList;
import chiikawa.Ui;

/**
 * Class for when user wants to see the list of all inputted tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
