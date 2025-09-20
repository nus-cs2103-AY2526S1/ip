package command;

import java.time.LocalDate;

import taskmodule.EventTask;
import taskmodule.Task;


/**
 * Represents the {@code event} command which adds an {@link EventTask}
 * to the task list.
 *
 * <p>When executed, this command creates a new event task with the given
 * description, start time, and end time, appends it to the global
 * {@link Command#taskList}, and returns a confirmation message to the user.</p>
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event task to the task list.\n"
            + "Parameters: DESCRIPTION /from START_DATE /to END_DATE\n"
            + "Example: " + COMMAND_WORD + " orientation camp /from 2025-07-20 /to 2025-07-24";

    private final String description;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an {@code EventCommand} with the specified description,
     * start time, and end time.
     *
     * @param description the description of the event task
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
    public EventCommand(String description, LocalDate from, LocalDate to) {
        assert description != null : "Description should not be null";
        assert from != null : "Event start time should not be null";
        assert to != null : "Event end time should not be null";

        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an {@link EventTask} with the stored description, start time,
     * and end time, and adds it to the global {@link Command#taskList}.
     *
     * @return the created {@code EventTask}
     */
    public Task addUserTask() {
        Task eventTask = new EventTask(this.description, this.from, this.to);
        taskList.addTask(eventTask);
        return eventTask;
    }

    /**
     * Executes this command by creating and adding an event task,
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
