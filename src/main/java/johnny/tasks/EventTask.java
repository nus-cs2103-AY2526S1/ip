package johnny.tasks;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A Task that refers to a particular event that falls on a day with a start and
 * end time.
 */
public class EventTask extends Task {
    protected LocalDateTime start;
    protected LocalTime end;
    protected String formattedStart;
    protected String formattedEnd;
    protected static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    protected static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Creates a new EventTask with the given name, start date and time, and the end
     * time.
     * 
     * @param name
     * @param start
     * @param end
     */
    public EventTask(String name, LocalDateTime start, LocalTime end) {
        super(name);
        this.start = start;
        this.end = end;
        this.formattedStart = start.format(dateTimeFormatter);
        this.formattedEnd = end.format(timeFormatter);
    }

    /**
     * Creates a new EventTask with the given boolean on whether it is done
     * 
     * @param name
     * @param completed
     * @param start
     * @param end
     */
    public EventTask(String name, boolean isCompleted, LocalDateTime start, LocalTime end) {
        super(name, isCompleted);
        this.start = start;
        this.end = end;
        this.formattedStart = start.format(dateTimeFormatter);
        this.formattedEnd = end.format(timeFormatter);
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalTime getEnd() {
        return this.end;
    }

    @Override
    public String getStoredString() {
        if (isCompleted)
            return "E|1|" + this.name + "|" + this.formattedStart + "|" + this.formattedEnd;
        return "E|0|" + this.name + "|" + this.formattedStart + "|" + this.formattedEnd;
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[D][X] " + super.name + " (from: " + this.formattedStart + " to: " + this.formattedEnd + ")";
        } else {
            return "[D][ ] " + super.name + " (from: " + this.formattedStart + " to: " + this.formattedEnd + ")";
        }
    }
}
