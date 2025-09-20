package command;

import taskmodule.Task;


/**
 * Represents the {@code delete} command which removes a task
 * from the task list.
 *
 * <p>When executed, this command deletes the task at the specified
 * index from the global {@link Command#taskList} and returns a
 * confirmation message to the user.</p>
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: TASK_INDEX (must be a positive integer less than or equal to the number of tasks)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} with the given task index.
     *
     * @param taskIndex the zero-based index of the task to be deleted
     */
    public DeleteCommand(int taskIndex) {
        assert taskIndex >= 0 : "Task index should be non-negative";

        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the task at the specified index from the global {@link Command#taskList}.
     *
     * @return the deleted {@link Task}
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task deleteUserTask() {
        if (this.taskIndex < 0 || this.taskIndex >= taskList.getTaskCount()) {
            throw new IndexOutOfBoundsException("taskmodule.Task index out of bounds.");
        }
        return taskList.deleteTask(this.taskIndex);
    }

    /**
     * Executes this command by deleting the specified task,
     * and returns Penny's confirmation message.
     *
     * @return the confirmation message to be displayed to the user
     */
    @Override
    public String respond() {
        try {
            return "Noted, I've removed this task:\n"
                    + this.deleteUserTask() + "\n"
                    + taskList;
        } catch (IndexOutOfBoundsException e) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(
                    "The task index provided is invalid.\n" + DeleteCommand.MESSAGE_USAGE);
            return incorrectCommand.respond();
        }
    }
}
