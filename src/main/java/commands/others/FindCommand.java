package commands.others;

import java.util.ArrayList;

import commands.Command;
import commands.CommandsEnum;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Finds list of tasks that user wants to find based on description.
 */
public class FindCommand extends Command {
    private final String toFind;
    /**
     * Sets description of task user wants to find.
     *
     * @param toFind Description of task.
     */
    public FindCommand(String toFind) {
        super(false, CommandsEnum.FIND);
        this.toFind = toFind;
    }

    /**
     * Gets all elements in tasks array.
     * Tasks adhere to string format specified by user.
     *
     *  @return Message from executing task
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.getAllTasks(toFind);
        return ui.displayTasks(tasks);
    }
}
