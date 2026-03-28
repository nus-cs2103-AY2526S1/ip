package taskstuff;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a deadline task with a specific due date and time.
 * Stores both the string and parsed LocalDateTime for the deadline.
 */
public class Deadline extends Task {
    /** Parsed LocalDateTime of the deadline. */
    protected LocalDateTime by;
    /** String representation of the deadline. */
    protected String byString;

    /**
     * Constructs a Deadline task with description and deadline time.
     *
     * @param description Description of the deadline task.
     * @param byString Deadline time as a string (ISO_LOCAL_DATE_TIME format).
     */
    public Deadline(String description, String byString) {
        super(description);
        this.byString = byString;
        this.by = LocalDateTime.parse(byString); // 2015-02-20T06:30:00;
    }

    /**
     * Returns a string representation suitable for saving to a data file.
     *
     * @return Data file string for this deadline.
     */
    @Override
    public String toDataFile() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                + this.byString;
    }

    /**
     * Returns a string representation of the deadline for display.
     *
     * @return Display string for this deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
}

//"2015-02-20T06:30:00"