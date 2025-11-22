package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task with deadline. 
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Checks if the Deadline is occuring at the inputDate(i.e. the inputDate is not earlier than {@code by}). 
     */
    @Override
    public Boolean isOcurringAt(LocalDateTime inputDate) {
        return !(inputDate.isBefore(by));
    }

    @Override
    public String getFormattedString() {
        return "D | " + super.getFormattedString() + " | " + by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), 
            by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
