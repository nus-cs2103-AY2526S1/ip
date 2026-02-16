package bambam.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents events which is a type of Task.
 */
public class Events extends Task {
    private LocalDateTime fromTime;
    private LocalDateTime toTime;

    private static final DateTimeFormatter DATE_TIME_FILE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Events(String taskDescription, String from, String to) {
        super(taskDescription);
        this.fromTime = getLocalDateTime(from);
        this.toTime = getLocalDateTime(to);

        if (fromTime.isAfter(toTime)) { // ChatGPT enhanced
            throw new IllegalArgumentException("Event start time cannot be after end time.");
        }
    }

    @Override
    public String printTaskString() {
        String fromTimeString = printLocalDateTime(fromTime);
        String toTimeString = printLocalDateTime(toTime);

        return "[E]" + super.printTaskString() + " (from: " +
                fromTimeString + " to: " + toTimeString + ")";
    }

    @Override
    public String taskStorageString() {
        String fromTimeString = fromTime.format(DATE_TIME_FILE_FORMAT);
        String toTimeString = toTime.format(DATE_TIME_FILE_FORMAT);

        return "E | " + super.taskStorageString() + " | " +
                fromTimeString + " to " + toTimeString;
    }
}
