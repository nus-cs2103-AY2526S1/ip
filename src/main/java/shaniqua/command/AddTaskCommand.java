package shaniqua.command;

import shaniqua.storage.Storage;
import shaniqua.taskcore.TaskList;
import shaniqua.taskcore.tasks.Task;
import shaniqua.ui.Ui;

/**
 * Command to add new task to task list.
 * This command encapsulates a task object and adds it to the provided task list when executed.
 */
public class AddTaskCommand extends Command {
    private Task task;

    /**
     * Constructs AddTaskCommand
     *
     * @param task the task to be added to the task list
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list.
     *
     * @param tasks the task list to add the task to
     * @param ui the user interface for interaction (unused in this command)
     * @param storage the storage system (unused in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.taskAdded(task.toString(), tasks.getLength());
    }
}
