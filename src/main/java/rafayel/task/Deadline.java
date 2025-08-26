package rafayel.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task which is a subtype of Task.
 * A Deadline task has a description and a deadline that is stored as a LocalDateTime.
 */
public class Deadline extends Task {

    /* Stores the date/time of the Deadline task */
    LocalDateTime deadlineDate;

    /**
     * Constructs a new Deadline task with the given description and Deadline Date.
     *
     * @param description the description of the Deadline task.
     * @param deadlineDate the deadline of the task which includes the date and time.
     */
    public Deadline(String description, LocalDateTime deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Handles the formatting of the date time object.
     * Converts the LocalDateTime object into a String.
     *
     * @param dateTime LocalDateTime object that stores a date and time.
     * @return formatted string in a standardised format.
     */
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
