package aqua.command;

import aqua.exception.InvalidArgumentException;
import aqua.exception.StorageException;
import aqua.task.Task;
import aqua.task.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkDoneCommand extends Command {
    private final String commandArgs;

    /**
     * Creates a mark done command.
     *
     * @param commandArgs Rest of the user's input after "mark" command
     */
    public MarkDoneCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Marks the task specified by a ID as done.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidArgumentException, StorageException {
        try {
            int index = Integer.parseInt(commandArgs) - 1; // -1 since ArrayList is 0-indexed
            if (index < 0 || index >= taskList.size()) {
                throw new InvalidArgumentException("Invalid ID");
            }

            Task task = taskList.get(index);
            taskList.markTaskDone(index);
            return String.format("Okay! I've marked this task as done:\n%s", task);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Invalid ID");
        }
    }
}
