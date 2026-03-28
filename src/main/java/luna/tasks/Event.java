package luna.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event with the given description and duration
     *
     * @param description Description of task
     * @param isDone Status of task, usually initialised to false
     * @param from Start of event
     * @param to End of event
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.from = LocalDateTime.parse(from, inputFormat);
        this.to = LocalDateTime.parse(to, inputFormat);
    }

    /**
     * Returns a string representation of the task for display.
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (from: "
                + this.from.format(outputFormat) + " to: " + this.to.format(outputFormat) + ")"
                + super.tagToString();
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E" + super.toFileString() + " | "
                + from.format(outputFormat) + " | " + to.format(outputFormat)
                + super.tagToString();
    }
}
