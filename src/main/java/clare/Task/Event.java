package clare.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import clare.exception.StringConvertExceptions;

/**
 * Represents a task type with a deadline and a start time
 */
public class Event extends Deadline {
    private final LocalDate startDate;
    private LocalTime startTime;

    /**
     * Constructs the Event class
     *
     * @param title the task title
     * @param startTime the start time of the event
     * @param deadline the deadline of the event
     * @param isDone the task status
     * @throws StringConvertExceptions if the format is invalid
     */
    public Event(String title, String startTime, String deadline, boolean isDone) throws StringConvertExceptions {
        super(title, deadline, isDone);
        try {
            String[] d = startTime.split(" ");
            if (Objects.equals(d[0], "now")) {
                startDate = LocalDate.now();
                this.startTime = LocalTime.now();
                return;
            }
            startDate = LocalDate.parse(d[0]);
            if (d.length > 1) {
                this.startTime = LocalTime.parse(d[1]);
            }
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new StringConvertExceptions("Error start date format: "
                    + deadline + " Please follow this format YYYY-MM-DD HH:MM");
        }
    }

    @Override
    String getTypeString() {
        return "E";
    }

    @Override
    public TaskType getType() {
        return TaskType.E;
    }

    private String getStartTime() {
        return startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + (startTime == null ? "" : (" " + startTime));
    }

    public int compareStartTime(Event event) {
        return startDate.isAfter(event.startDate) ? 1 : startDate.isBefore(event.startDate) ? -1 : 0;
    }

    @Override
    public String toString() {
        return "[" + getTypeString() + "]" + getIsDoneStatus() + " " + getTitle()
                + " (from: " + getStartTime() + " to: " + getDeadlineString() + ")";
    }

    @Override
    public String toSaveString() {
        return getTypeString() + super.toSaveString().substring(1)
                + "|" + startDate + (startTime == null ? "" : (" " + startTime));
    }
}
