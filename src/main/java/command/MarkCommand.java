package command;

import taskmodule.Task;


/**
 * Represents the {@code mark} command which marks a task in the task list as done.
 *
 * <p>When executed, this command updates the specified task in the global
 * {@link Command#taskList} to indicate it has been completed, and returns
 * a confirmation message to the user.</p>
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task in the task list.\n"
            + "Parameters: TASK_INDEX (must be a positive integer less than or equal to the number of tasks)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public final int taskIndex;

    /**
     * Constructs a {@code MarkCommand} for the specified task index.
     *
     * @param taskIndex the zero-based index of the task to mark as done
     */
    public MarkCommand(int taskIndex) {
        assert taskIndex >= 0 : "Task index should be non-negative";

        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task at the specified index as done in the global {@link Command#taskList}.
     *
     * @param taskIndex the index of the task to mark as done
     * @return the updated {@link Task} after being marked as done
     * @throws IndexOutOfBoundsException if the task index is invalid
     */
    public Task markTaskAsDone(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new IndexOutOfBoundsException("taskmodule.Task index out of bounds.");
        }
        return taskList.getTask(taskIndex).markAsDone();
    }

    /**
     * Executes this command by marking the specified task as done,
     * and returns Penny's confirmation message.
     *
     * @return the message confirming the task has been marked as done
     */
    @Override
    public String respond() {
        try {
            return "Nice! I've marked this task as done:\n" + this.markTaskAsDone(this.taskIndex);
        } catch (IndexOutOfBoundsException e) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(
                    "The task index provided is invalid.\n" + MarkCommand.MESSAGE_USAGE);
            return incorrectCommand.respond();
        }
    }
}
