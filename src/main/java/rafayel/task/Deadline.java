package rafayel.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime deadlineDate;

    public Deadline(String description, LocalDateTime deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    private static String handleDateTimeFormatting(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + handleDateTimeFormatting(this.deadlineDate) + ")";
    }

    @Override
    public String saveTaskName() {
        return "D" + super.saveTaskName() + " | " + handleDateTimeFormatting(this.deadlineDate);
    }
}
