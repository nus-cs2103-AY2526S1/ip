package john.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task with a start date and an end date.
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;
    /**
     * Constructor for event.
     */
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }
    /**
     * Constructor for event with specified mark as done.
     */
    public Event(String name, LocalDate start, LocalDate end, boolean done) {
        super(name, done);
        this.start = start;
        this.end = end;
    }
    /**
     * Constructor for event that creates a copy of another event.
     */
    public Event(Event other) {
        super(other);
        this.start = other.start;
        this.end = other.end;
    }

    /**
     * Creates a deep copy of this task.
     */
    @Override
    public Event copy() {
        return new Event(this);
    }
    /**
     * String representation of the event for displaying to user.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ", to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
    /**
     * String representation of the event for saving in the save file.
     */
    @Override
    public String writeString() {
        return "E | " + super.writeString() + " | "
                + this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " | "
                + this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
