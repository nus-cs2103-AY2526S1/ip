package jimbot.tasktype;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Stores the date and time by which the task should be completed.
 *
 * @author limjimin-nus
 */
public class Deadline extends Task {
    protected LocalDateTime dateTime;
    protected boolean isMidnight;

    /**
     * Constructs a Deadline task with the specified description and due date
     * .
     * @param description Description of the task.
     * @param by Date and time by which task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        assert by != null : "Deadline dateTime cannot be null";
        assert getDescription() != null && !getDescription().isEmpty()
                : "Task description should not be null or empty";

        this.dateTime = by;
        this.isMidnight = dateTime.toLocalTime().equals(LocalTime.MIDNIGHT);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    /**
     * Returns a string representation of the Deadline task.
     * Includes the task type, description, and formatted deadline.
     * Shows only the date if the time is midnight; otherwise, shows date and time.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        if (isMidnight) {
            return "[D]" + super.toString() + "\n               (BY: "
                    + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + "\n               (BY: "
                    + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
        }
    }
}
