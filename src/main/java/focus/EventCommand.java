package focus;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Adds a new Event task to the task list.
 * The time period is captured as an even start and an end string.
 */
public class EventCommand extends FocusCommand {

    private final String description;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /** User input and storage format. */
    private DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs an EventCommand.
     * Note: Used ChatGPT here to modify EventCommand to handle local date time formats
     *
     * @param description      Description of the event.
     * @param startRaw         Event start in raw String (will be parsed into yyyy-MM-dd HHmm)
     * @param endRaw           Event end in raw String (will be parsed into yyyy-MM-dd HHmm)
     *
     * @throws FocusException If the startRaw or endRaw cannot be parsed into a LocalDateTime
     */
    public EventCommand(String description, String startRaw, String endRaw) throws FocusException {
        this.description = description;
        try {
            this.start = LocalDateTime.parse(startRaw, inputFormat);
            this.end = LocalDateTime.parse(endRaw, inputFormat);
        } catch (DateTimeParseException e) {
            throw new FocusException("     Invalid date-time. Use yyyy-MM-dd HHmm (e.g., 2025-10-01 0930).");
        }
        if (this.end.isBefore(this.start)) {
            throw new FocusException("     End date-time cannot be before start date-time.");
        }
    }

    @Override
    public boolean isMutating() {
        return true;
    }

    /**
     * Executes the command by adding the event to the list.
     *
     * @param tasks Task list to update.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(new Event(description, start, end), false);
    }

}
