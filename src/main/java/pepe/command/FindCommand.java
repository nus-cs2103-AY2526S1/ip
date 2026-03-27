package pepe.command;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

/**
 * Represents a command that searches for tasks in the task list
 * whose names contain a given keyword.
 * <p>
 * When executed, this command filters the tasks and displays
 * the matching results using the {@link Ui}.
 */
public class FindCommand extends Command {
    private String taskName;

    public FindCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        TaskList output = tasks.findTask(taskName);
        super.setResponse(ui.showUiFind(output));
    }

}
