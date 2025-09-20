package command;

import java.time.LocalDate;

import taskmodule.DeadlineTask;
import taskmodule.Task;


/**
 * Represents the {@code deadline} command which adds a {@link DeadlineTask}
 * to the task list.
 *
 * <p>When executed, this command creates a new deadline task with the given
 * description and due date, appends it to the global {@link Command#taskList},
 * and returns a confirmation message to the user.</p>
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a deadline task to the task list.\n"
            + "Parameters: DESCRIPTION /by DATE\n"
            + "Example: " + COMMAND_WORD + " return book /by 2024-09-15";

    private final String description;
    private final LocalDate by;

    /**
     * Constructs a {@code DeadlineCommand} with the specified task description and deadline.
     *
     * @param description the description of the deadline task
     * @param by          the due date of the deadline task
     */
    public DeadlineCommand(String description, LocalDate by) {
        assert description != null : "Description should not be null";
        assert by != null : "Event start time should not be null";

        assert by != null : "Deadline date should not be null";
        this.description = description;
        this.by = by;
    }

    /**
     * Creates a {@link DeadlineTask} with the stored description and deadline,
     * and adds it to the global {@link Command#taskList}.
     *
     * @return the created {@code DeadlineTask}
     */
    public Task addUserTask() {
        Task deadlineTask = new DeadlineTask(this.description, this.by);
        taskList.addTask(deadlineTask);
        return deadlineTask;
    }

    /**
     * Executes this command by creating and adding a deadline task,
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
