package chani.commands;

import chani.Storage;
import chani.tasks.Task;
import chani.TaskList;
import chani.Ui;

/**
 * Represents command to delete a {@link Task} from the {@link TaskList}.
 */
public class DeleteCommand extends Command {

    /**
     * Creates a DeleteCommand with a command keyword and arguments.
     * @param command The command keyword (e.g., "delete").
     * @param args The task ID to delete (first argument).
     */
    public DeleteCommand(String command, String... args) {
        super(command, args);
    }

    /**
     * Removes the specified task from the task list and returns a message.
     * @param storage The storage handler (not used here).
     * @return Message confirming the task was deleted.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        int taskId = Integer.parseInt(args[0]);
        Task toDelete = taskList.get(taskId);
        taskList.remove(toDelete);
        return ui.showDeletedTask(toDelete, taskList.size());
    }
}
