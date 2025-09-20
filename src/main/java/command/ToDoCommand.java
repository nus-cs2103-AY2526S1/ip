package command;

import taskmodule.Task;
import taskmodule.ToDoTask;


/**
 * Represents the {@code todo} command which adds a {@link ToDoTask}
 * to the task list.
 *
 * <p>When executed, this command creates a new todo task with the given
 * description, appends it to the global {@link Command#taskList}, and
 * returns a confirmation message to the user.</p>
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo task to the task list.\n"
            + "Parameters: DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " read book";

    private final String description;

    /**
     * Constructs a {@code ToDoCommand} with the specified task description.
     *
     * @param description the description of the todo task
     */
    public ToDoCommand(String description) {
        assert description != null : "Description should not be null";

        this.description = description;
    }

    /**
     * Creates a {@link ToDoTask} with the stored description,
     * and adds it to the global {@link Command#taskList}.
     *
     * @return the created {@code ToDoTask}
     */
    public Task addUserTask() {
        Task todoTask = new ToDoTask(this.description);
        taskList.addTask(todoTask);
        return todoTask;
    }

    /**
     * Executes this command by creating and adding a todo task,
     * and returns Penny's confirmation message.
     *
     * @return the confirmation message to be displayed to the user
     */
    @Override
    public String respond() {
        return "Got it. I've added this task:\n"
                + this.addUserTask() + "\n"
                + taskList;
    }
}
