package commands.task;

import commands.Command;
import commands.CommandsEnum;
import ineffaexceptions.IneffaException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Command to delete a Task
 */
public class DeleteCommand extends Command {
    private final String indexStr;

    /**
     * Instantiates deleting a specific task
     *
     * @param indexStr index of task to delete in TaskList
     */
    public DeleteCommand(String indexStr) {
        super(false, CommandsEnum.DELETE);
        this.indexStr = indexStr;
    }

    /**
     * Deletes task from tasks array.
     *
     * @param ui string format of index in tasks to remove element.
     * @return Message from executing task
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IneffaException {
        int index = tasks.validateTasksIndex(indexStr);
        Task task = tasks.get(index);
        tasks.remove(index);

        String message = "Noted. I've removed this task:\n" + task.toString() + "\n"
            + "Now you have " + tasks.taskSize() + " tasks in the list.";
        Ui.displayMessage(message);
        return message;
    }
}
