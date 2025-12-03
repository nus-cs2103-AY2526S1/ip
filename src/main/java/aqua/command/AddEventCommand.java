package aqua.command;

import aqua.exception.InvalidArgumentException;
import aqua.exception.StorageException;
import aqua.task.Event;
import aqua.task.Task;
import aqua.task.TaskList;

/**
 * Command to add a Event task to the task list.
 */
public class AddEventCommand extends AddTaskCommand {
    private final String commandArgs;

    /**
     * Creates a command that adds a Event task.
     *
     * @param commandArgs the string after the "event" command word, representing
     *             the description, from (/from) and to (/to) of the event Task
     */
    public AddEventCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Adds a Event task to the task list.
     *
     * @throws InvalidArgumentException if description is blank, missing /from or /to,
     *         blank /from or /to
     */
    @Override
    public String execute(TaskList taskList) throws InvalidArgumentException, StorageException {
        int fromIndex = commandArgs.indexOf("/from");
        int toIndex = commandArgs.indexOf("/to");

        if (fromIndex != -1 && toIndex != -1) {
            String description = commandArgs.substring(0, Math.min(fromIndex, toIndex)).trim();
            String from;
            String to;

            if (description.isBlank()) {
                throw new InvalidArgumentException("Description of an event cannot be empty.");
            }

            if (fromIndex < toIndex) {
                from = commandArgs.substring(fromIndex + 5, toIndex).trim();
                to = commandArgs.substring(toIndex + 3).trim();
            } else {
                from = commandArgs.substring(fromIndex + 5).trim();
                to = commandArgs.substring(toIndex + 3, fromIndex).trim();
            }

            Task task = new Event(description, from, to);
            return super.addTask(task, taskList);
        } else {
            throw new InvalidArgumentException("From (/from) and To (/to) required for event");
        }
    }
}
