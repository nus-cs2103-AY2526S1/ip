package tom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task that has to be completed by a certain date.
 */
public class Deadline extends Task {
    protected LocalDateTime endDateAndTime;

    /**
     * Constructs a Deadline.
     * @param description Task description.
     * @param by Time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        super.id = "D";
        this.endDateAndTime = by;
    }

    @Override
    public String saveDesc() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")
                .withLocale(Locale.ENGLISH);
        return super.saveDesc() + " | " + endDateAndTime.format(outputFormatter);
    }

    @Override
    public String toString() {
        return super.showPriority() + "[D]" + super.toString() + " (by: "
                + endDateAndTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")
                .withLocale(Locale.ENGLISH)) + ")";
    }
}
