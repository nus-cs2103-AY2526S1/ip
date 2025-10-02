package focus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Adds a new Deadline task to the task list.
 * The date is expected to be input in yyyy-MM-dd HHmm format.
 */

public class DeadlineCommand extends FocusCommand {

    private final String description;
    private final LocalDateTime deadline;

    /** User input and storage format. */
    private DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a DeadlineCommand.
     * Note: Used ChatGPT here to modify DeadlineCommand to handle local date time formats
     *
     * @param description Description of the deadline.
     * @param deadline Due date in yyyy-MM-dd HHmm format.
     */
    public DeadlineCommand(String description, String deadline) throws FocusException {
        this.description = description;
        try {
            this.deadline = LocalDateTime.parse(deadline, inputFormat);
        } catch (DateTimeParseException ex) {
            throw new FocusException("     Invalid date-time. Use yyyy-MM-dd HHmm (e.g., 2025-10-01 0930).");
        }
    }

    @Override
    public boolean isMutating() {
        return true;
    }

    /**
     * Executes the command by adding the deadline to the list and showing feedback.
     *
     * @param tasks Task list to update.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(new Deadline(description, this.deadline), false);
    }


}

