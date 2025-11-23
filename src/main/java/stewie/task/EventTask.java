package stewie.task;

import java.time.LocalDateTime;

import stewie.util.Helper;

/**
 * Represents a task that occurs during a specific time period.
 */
public class EventTask extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Creates a new EventTask with the specified description, start time, and end time.
     *
     * @param description The task description.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toFileFormat() {
        String formattedStartTime = Helper.dateTimeToFileFormat(startTime);
        String formattedEndTime = Helper.dateTimeToFileFormat(endTime);
        return "E | " + super.toFileFormat() + " | " + formattedStartTime
                + " | " + formattedEndTime;
    }

    @Override
    public String getDescription() {
        String formattedStartTime = Helper.dateTimeToString(startTime);
        String formattedEndTime = Helper.dateTimeToString(endTime);
        return "[E]" + super.getDescription() + " (from: " + formattedStartTime
                + " to: " + formattedEndTime + ")";
    }
}
