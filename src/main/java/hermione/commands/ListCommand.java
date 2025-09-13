package hermione.commands;

import hermione.storage.TaskStorage;
import hermione.tasks.TaskList;

/**
 * Represents a command to list all tasks in the Hermione application.
 */
public class ListCommand extends Command {

    public ListCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the command to list all tasks.
     * This method retrieves the list of tasks from storage and formats them for
     * display. It also includes the number of completed tasks in the list.
     *
     * @return A string containing the formatted list of tasks.
     */
    @Override
    public String execute() {
        TaskList tasks = storage.getTasks();
        return "Here's your current task list:\n"
                + tasks.toString()
                + "\nYou've completed %d tasks so far. Keep up the excellent work!"
                        .formatted(tasks.getCompletedCount());
    }
}
