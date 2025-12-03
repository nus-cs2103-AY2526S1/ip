package aqua.command;
import aqua.exception.InvalidArgumentException;
import aqua.exception.StorageException;
import aqua.task.Deadline;
import aqua.task.Task;
import aqua.task.TaskList;

/**
 * Command to add a Deadline task to the task list.
 */
public class AddDeadlineCommand extends AddTaskCommand {
    private final String commandArgs;

    /**
     * Creates a command that adds a Deadline task.
     *
     * @param commandArgs the string after the "deadline" command word, representing
     *             the description, and by (/by) deadline of the Deadline Task
     */
    public AddDeadlineCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Adds a Deadline task to the task list.
     *
     * @throws InvalidArgumentException if description is blank, missing /by or blank /by
     */
    @Override
    public String execute(TaskList taskList) throws InvalidArgumentException, StorageException {
        int byIndex = commandArgs.indexOf("/by");

        if (byIndex != -1) {
            String description = commandArgs.substring(0, byIndex).trim();
            if (description.isBlank()) {
                throw new InvalidArgumentException("Description of a deadline cannot be empty.");
            }

            String by = commandArgs.substring(byIndex + 3).trim();
            if (by.isBlank()) {
                throw new InvalidArgumentException("Deadline (/by) cannot be empty.");
            }

            Task task = new Deadline(description, by);
            return super.addTask(task, taskList);
        } else {
            throw new InvalidArgumentException("By (/by) required for deadline.");
        }
    }
}
