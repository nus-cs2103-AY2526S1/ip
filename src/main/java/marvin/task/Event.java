package marvin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An Event object that contains a task description with an additional start DateTime and end DateTime.
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Instantiates an Event object.
     *
     * @param description The description of the task.
     * @param startTime   The time at which the event starts
     * @param endTime     The time at which the event ends
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        // Construct string based on description and status
        return String.format("[E]%s %s (from: %s to: %s)", super.toString(), this.getDescription(),
                this.startTime.format(DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy, ha"
                )),
                this.endTime.format(DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy, ha"
                ))
        );
    }
}
