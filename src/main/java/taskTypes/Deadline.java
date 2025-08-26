package taskTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    protected LocalDateTime dateTime;
    protected boolean isMidnight;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.dateTime = by;
        this.isMidnight = dateTime.toLocalTime().equals(LocalTime.MIDNIGHT);
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    @Override
    public String toString() {
        if (isMidnight) {
            return "[D]" + super.toString() + " (BY: " +
                    dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";

        } else {
            return "[D]" + super.toString() + " (BY: " +
                    dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
        }
    }
}
