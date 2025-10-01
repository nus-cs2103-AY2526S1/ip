package panda.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import panda.PandaException;

/**
 * Event is a subclass of Task, with additional information of
 * task type, and start and end date.
 */
public class Event extends Task {
    protected String type;
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs Event object based on info from user.
     *
     * @param description Description of task.
     * @param start Start date of task.
     * @param end End date of task.
     * @throws PandaException If the date format is wrong.
     */
    public Event(String description, String start, String end) throws PandaException {
        super(description);
        this.type = "[E]";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT);
            this.start = LocalDateTime.parse(start, formatter);
            this.end = LocalDateTime.parse(end, formatter);
        } catch (DateTimeParseException e) {
            throw new PandaException("wrongDateTimeFormat", "");
        }
    }

    /**
     * Constructs Event object based on info from text file.
     *
     * @param status Status of task.
     * @param description Description of task.
     * @param start Start date of task.
     * @param end End date of task.
     */
    public Event(String status, String description, String start, String end) {
        super(status, description);
        this.type = "[E]";
        this.start = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
        this.end = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

    private String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Returns the event info in a format for saving to text file.
     *
     * @return String of event info.
     */
    public String writeToFile() {
        return type + "|" + super.status + "|" + super.description
                + "|" + formatDate(start) + "|" + formatDate(end) + "\n";
    }

    @Override
    public String toString() {
        return type + super.toString() + " (from: " + formatDate(start) + " to: " + formatDate(end) + ")";
    }
}
