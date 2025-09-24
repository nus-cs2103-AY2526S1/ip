package khat.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import khat.exception.DeadlineTaskException;

/** Represents a Deadline task */
public class Deadline extends Task {

    protected LocalDate date;
    protected LocalDateTime dateTime;
    protected String by;
    private boolean hasTime;

    /**
     * Constructs a Deadline task with the given description, completion status
     * and deadline date/time. Accepts deadline in the "dd-MM-yyyy HHmm" or "dd-MM-yyyy" format.
     *
     * @param description Description of task.
     * @param isDone True if task is completed, false otherwise.
     * @param by Deadline date/time string.
     * @throws DeadlineTaskException If the date/time format is invalid.
     */
    public Deadline(String description, boolean isDone, String by) throws DeadlineTaskException {
        super(description, isDone);
        try {
            this.dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            this.hasTime = true;
            this.by = by;
        } catch (DateTimeParseException e1) {
            try {
                this.date = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                this.hasTime = false;
                this.by = by;
            } catch (DateTimeParseException e2) {
                throw new DeadlineTaskException("Invalid date/time format! Use dd-MM-yyyy or dd-MM-yyyy HHmm!");
            }
        }
    }

    /**
     * Returns true if the deadline includes a time, false if only date.
     *
     * @return True if deadline has time, false otherwise.
     */
    public boolean hasTime() {
        return this.hasTime;
    }

    /**
     * Returns the formatted deadline string for display.
     *
     * @return Formatted deadline string.
     */
    private String deadlineToString() {
        if (hasTime) {
            return this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yy h:mm a"));
        } else {
            return this.date.format(DateTimeFormatter.ofPattern("dd MMM yy"));
        }
    }

    @Override
    public String toSaveString() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.getDescription() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineToString() + ")";
    }
}
