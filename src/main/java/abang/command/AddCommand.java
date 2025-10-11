package abang.command;

import abang.task.TaskList;
import abang.task.Task;
import abang.ui.UI;
import abang.storage.Storage;
import abang.exception.AbangException;

/**
 * Represents a command to add a new task to the task list.
 */
public class AddCommand extends Command {
    Task task;

    /**
     * Creates an AddCommand with the given task.
     *
     * @param task the task to add
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list
     * and saving it to storage.
     *
     * @param taskList the current task list
     * @param ui       the UI object for interaction
     * @param storage  the storage object for saving tasks
     */
    
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws AbangException {
        taskList.add(this.task);
        int numTask = taskList.numTask();

        String msg = "Got it. I've added this task:\n"
                + this.task + "\n"
                + String.format("Now you have %d tasks in the list.", numTask);

        storage.save(taskList.toFileLines());
        return msg;
    }
}

