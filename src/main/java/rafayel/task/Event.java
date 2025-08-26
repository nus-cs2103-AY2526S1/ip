package rafayel.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    LocalDateTime startDate;
    LocalDateTime endDate;

    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    private static String handleDateTimeFormatting(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + handleDateTimeFormatting(this.startDate) + " to: "
                + handleDateTimeFormatting(this.endDate) + ")";
    }

    @Override
    public String saveTaskName() {
        return "E" + super.saveTaskName() + " | " + handleDateTimeFormatting(this.startDate) + " - "
                + handleDateTimeFormatting(this.endDate);
    }
}
