package tsunderechan.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tsunderechan.ui.Ui;

/**
 * Represents an Event task. It has a description, and a timing from x to y.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Instantiates an Event object.
     *
     * @param description Description of the Event Task.
     * @param from The time the Event Task will start.
     * @param to The time the Event Task will last until.
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeFrom = LocalDateTime.parse(from, inputFormatter);
            this.from = dateTimeFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            LocalDateTime dateTimeTo = LocalDateTime.parse(to, inputFormatter);
            this.to = dateTimeTo.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));

            // If end time is earlier than start time, throw exception
            if (dateTimeTo.isBefore(dateTimeFrom)) {
                Ui.showDateTimeInvalidError();
            }
        } catch (DateTimeParseException e) {
            Ui.showDateTimeFormatError("from and to");
        }
    }

    /**
     * Instantiates an Event object.
     * This overloaded method allows for manually setting the isDone field.
     *
     * @param description Description of the Event Task.
     * @param from The time the Event Task will start.
     * @param to The time the Event Task will last until.
     * @param isDone Whether the task has already been completed.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeFrom = LocalDateTime.parse(from, inputFormatter);
            this.from = dateTimeFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            LocalDateTime dateTimeTo = LocalDateTime.parse(to, inputFormatter);
            this.to = dateTimeTo.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));

            // If end time is earlier than start time, throw exception
            if (dateTimeTo.isBefore(dateTimeFrom)) {
                Ui.showDateTimeInvalidError();
            }
        } catch (DateTimeParseException e) {
            Ui.showDateTimeFormatError("from and to");
        }
    }

    @Override
    public String getIcon() {
        return "E";
    }

    @Override
    public String getTiming() {
        return " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
