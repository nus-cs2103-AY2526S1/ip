package abang.task;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a deadline task with a description and a specific deadline.
 * The deadline may be stored as either a LocalDate, a LocalDateTime,
 * or as a free-text detail string if parsing fails.
 */
public class Deadline extends Task {
    String detail;
    LocalDate deadline;
    LocalDateTime deadlineTime;

    /**
     * Creates a Deadline task with the given description and detail.
     * <p>
     * The detail string is first attempted to be parsed as a date (d/M/yyyy).
     * If that fails, it is attempted to be parsed as a date and time (d/M/yyyy HHmm).
     * If both parsing attempts fail, the detail is stored as a free-text string.
     *
     * @param description the description of the task
     * @param detail      the deadline detail as a string
     */
    public Deadline(String description, String detail) {
        super(description);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy");
            this.deadline = LocalDate.parse(detail, format);
            return;
        } catch (DateTimeParseException e) {
        }
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.deadlineTime = LocalDateTime.parse(detail, format);
            return;
        } catch (DateTimeParseException e) {
        }
        this.detail = detail;
    }

    /**
     * Returns a string representation of this deadline task,
     * including the formatted deadline.
     *
     * @return the string representation of the deadline task
     */
    @Override
    public String toString() {
        if (deadline != null) {
            return "[D]" + super.toString() +
                    " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        if (deadlineTime != null) {
            return "[D]" + super.toString() +
                    " (by: " + deadlineTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + detail + ")";
        }
    }

    /**
     * Returns the file format representation of this deadline task,
     * which is used for saving the task to persistent storage.
     *
     * @return the deadline task as a string suitable for file saving
     */
    @Override
    public String toFileFormat() {
        if (deadline != null) {
            return "D | " + getStatusIcon() + " | " + getTaskDescription() + " | "
                    + (Objects.isNull(this.getTag()) ? "null" : this.getTag().split("#")[1])
                    + " | " + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if (deadlineTime != null) {
            return "D | " + getStatusIcon() + " | " + getTaskDescription() + " | "
                    + (Objects.isNull(this.getTag()) ? "null" : this.getTag().split("#")[1])
                    + " | " + deadlineTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else {
            return "D | " + getStatusIcon() + " | " + getTaskDescription() + " | "
                    + (Objects.isNull(this.getTag()) ? "null" : this.getTag().split("#")[1])
                    + " | " + detail;
        }
    }

}
