package commands.others;

import java.util.ArrayList;

import commands.Command;
import commands.CommandsEnum;
import ineffaexceptions.IneffaException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * List all tasks created by user.
 */
public class ListCommand extends Command {
    /**
     * Sets isExit as false in Command task.
     * So that program does not exit on ListCommand.
     */
    public ListCommand() {
        super(false, CommandsEnum.LIST);
    }

    /**
     * Gets all elements in tasks array.
     *
     * @return Message from executing task.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IneffaException {
        ArrayList<Task> tasks = taskList.getAllTasks("");
        return ui.displayTasks(tasks);
    }
}
