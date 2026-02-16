package commands.task;

import commands.Command;
import commands.CommandsEnum;
import ineffaexceptions.IneffaException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Command to mark whether task is done or not
 */
public class MarkCommand extends Command {
    private final boolean isDone;
    private final String indexStr;

    /**
     * Marks task status
     *
     * @param indexStr index of task to mark in TaskList
     * @param isDone task is done: true, task is not done: false
     */
    public MarkCommand(String indexStr, boolean isDone) {
        super(false, isDone ? CommandsEnum.MARK : CommandsEnum.UNMARK);
        this.isDone = isDone;
        this.indexStr = indexStr;
    }

    /**
     * Marks or unmarks task based on user input.
     *
     * @return Message from executing task
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IneffaException {
        int index = tasks.validateTasksIndex(indexStr);
        Task currentTask = tasks.get(index);
        if (isDone) {
            currentTask.setDone();
            Ui.displayMessage("Nice! I've marked this task as done:");
        } else {
            currentTask.setNotDone();
            Ui.displayMessage("OK, I've marked this task as not done yet:");
        }

        String message = currentTask.toString();
        Ui.displayMessage(message);
        return message;
    }

    public boolean isDone() {
        return this.isDone;
    }
}
