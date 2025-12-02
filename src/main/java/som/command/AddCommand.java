package som.command;

import som.SomException;
import som.Storage;
import som.TaskList;
import som.Ui;
import som.task.Task;

/**
 * Represents a command to add a task to the task list.
 * <p>When executed, this command adds the specified task and confirms the addition
 * through the user interface. It also triggers an automatic save of the updated list.</p>
 *
 * @author Darien Tan
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the given task.
     * @param task the task to be added, must not be null.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws SomException {
        tasks.add(task);
        Storage.save(tasks.getAllTasks());
        return ui.showAdd(task, tasks.size());
    }

    public Task getTask() {
        return this.task;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
