package penguin.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event tasks have both a start and end datetime field
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String start, String end) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

    /**
     * Returns the start datetime
     * @return string formatted datetime
     */
    public String getStart() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return this.start.format(formatter);
    }

    /**
     * Returns the end datetime
     * @return string formatted datetime
     */
    public String getEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return this.end.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"))
                    + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma")) + ")";
    }
}
