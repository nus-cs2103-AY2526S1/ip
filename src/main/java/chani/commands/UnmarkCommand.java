package chani.commands;

import chani.Storage;
import chani.TaskList;
import chani.Ui;
import chani.tasks.Task;

/**
 * Command to mark a {@link Task} in the {@link TaskList} as not done.
 */
public class UnmarkCommand extends Command {

    /**
     * Creates an UnMarkCommand with a command keyword and task ID.
     * @param command The command keyword (e.g., "unmark").
     * @param args The task ID to unmark (first argument).
     */
    public UnmarkCommand(String command, String... args) {
        super(command, args);
    }

    /**
     * Marks the specified task as not done and displays a confirmation message.
     * @param storage The storage handler (not used here).
     * @return Message confirming the task was unmarked.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        int taskId = Integer.parseInt(args[0]);
        Task task = taskList.get(taskId).markAsUndone();
        return ui.showUnmarkedTask(task);
    }
}
