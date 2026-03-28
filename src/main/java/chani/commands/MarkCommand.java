package chani.commands;

import chani.Storage;
import chani.TaskList;
import chani.Ui;
import chani.tasks.Task;

/**
 * Command to mark a {@link Task} in the {@link TaskList} as done.
 */
public class MarkCommand extends Command {

    /**
     * Creates a MarkCommand with a command keyword and task ID.
     * @param command The command keyword (e.g., "mark").
     * @param args The task ID to mark (first argument).
     */
    public MarkCommand(String command, String... args) {
        super(command, args);
    }

    /**
     * Marks the specified task as done and returns a confirmation message.
     * @param storage The storage handler (not used here).
     * @return Message confirming the task was marked done.
     * @throws NumberFormatException if the task ID is not a valid integer.
     * @throws IndexOutOfBoundsException if the task ID does not exist.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        int taskId = Integer.parseInt(args[0]);
        Task markedTask = taskList.get(taskId).markAsDone();
        return ui.showMarkedTask(markedTask);
    }
}
