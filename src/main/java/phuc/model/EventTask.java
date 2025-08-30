package phuc.model;

import phuc.parser.DateTimeProcess;
import java.time.LocalDateTime;

/**
 * Represents an event task with specific start and end times.
 * Extends the base Task class with event timing functionality.
 */
public class EventTask extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Constructs a new EventTask with the specified description and time range.
     *
     * @param description the event description
     * @param startDate the event start date and time
     * @param endDate the event end date and time
     */
    public EventTask(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs an EventTask with completion status.
     *
     * @param description the event description
     * @param startDate the event start date and time
     * @param endDate the event end date and time
     * @param isDone whether the event is completed
     */
    public EventTask(String description, LocalDateTime startDate, LocalDateTime endDate, boolean isDone) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Converts the event task to a string format suitable for file storage.
     *
     * @return a string representation for file storage
     */
    @Override
    public String writeToFile() {
        return "E | " + isDone + " | " + description + " | " + DateTimeProcess.formatForFile(startDate) + " | " + DateTimeProcess.formatForFile(endDate);
    }

    /**
     * Returns a string representation of the event task for display.
     *
     * @return a formatted string showing event details and time range
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeProcess.formatForDisplay(startDate) + " to: " + DateTimeProcess.formatForDisplay(endDate) + ")";
    }
}
