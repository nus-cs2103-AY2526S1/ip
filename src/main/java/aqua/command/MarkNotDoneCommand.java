package aqua.command;
import aqua.exception.InvalidArgumentException;
import aqua.exception.StorageException;
import aqua.task.Task;
import aqua.task.TaskList;

/**
 * Command to mark a task as not done.
 */
public class MarkNotDoneCommand extends Command {
    private final String commandArgs;

    /**
     * Creates a mark not done command.
     *
     * @param commandArgs Rest of the user's input after "unmark" command
     */
    public MarkNotDoneCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Marks the task specified by a ID as not done.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidArgumentException, StorageException {
        try {
            int index = Integer.parseInt(commandArgs) - 1; // -1 since ArrayList is 0-indexed
            if (index < 0 || index >= taskList.size()) {
                throw new InvalidArgumentException("Invalid ID");
            }

            Task task = taskList.get(index);
            taskList.markTaskNotDone(index);
            return String.format("Okay! I've marked this task as not done yet:\n%s", task);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Invalid ID");
        }
    }
}
