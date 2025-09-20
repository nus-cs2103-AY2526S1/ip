package command;

import taskmodule.Task;


/**
 * Represents the {@code unmark} command which marks a task in the task list as not done.
 *
 * <p>When executed, this command updates the specified task in the global
 * {@link Command#taskList} to indicate it is not yet completed, and returns
 * a confirmation message to the user.</p>
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task as not done yet.\n"
            + "Parameters: TASK_INDEX (must be a positive integer less than or equal to the number of tasks)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public final int taskIndex;

    /**
     * Constructs an {@code UnmarkCommand} for the specified task index.
     *
     * @param taskIndex the zero-based index of the task to unmark as done
     */
    public UnmarkCommand(int taskIndex) {
        assert taskIndex >= 0 : "Task index should be non-negative";

        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task at the specified index as not done in the global {@link Command#taskList}.
     *
     * @return the updated {@link Task} after being unmarked
     * @throws IndexOutOfBoundsException if the task index is invalid
     */
    public Task unmarkTaskAsDone() {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new IndexOutOfBoundsException("taskmodule.Task index out of bounds.");
        }
        return taskList.getTask(taskIndex).unmarkAsDone();
    }

    /**
     * Executes this command by unmarking the specified task as not done,
     * and returns Penny's confirmation message.
     *
     * @return the message confirming the task has been unmarked
     */
    @Override
    public String respond() {
        try {
            return "OK, I've marked this task as not done yet:\n" + this.unmarkTaskAsDone();
        } catch (IndexOutOfBoundsException e) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(
                    "The task index provided is invalid.\n" + UnmarkCommand.MESSAGE_USAGE);
            return incorrectCommand.respond();
        }
    }
}
