package reboot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {

    private LocalDate dueDate; // If date is the only thing provided
    private LocalDateTime dueDateTime; // If time is provided

    /**
     * Constructs a new Deadline with the given description, isDone and dueDate.
     * @param description Description of the deadline task.
     * @param isDone Status of the task.
     * @param dueDate Due date of the task.
     */
    public Deadline(String description, boolean isDone, LocalDate dueDate) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    public Deadline(String description, boolean isDone, LocalDateTime dueDateTime) {
        super(description, isDone);
        this.dueDateTime = dueDateTime;
    }

    public LocalDateTime getDueDateTime() {
        return this.dueDateTime;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setDueDateTime(LocalDateTime dateTime) {
        this.dueDateTime = dateTime;
    }

    public void setDueDate(LocalDate date) {
        this.dueDate = date;
    }

    /**
     * Converts the task to a string that can be stored in a file.
     */
    @Override
    public String toFileString() {
        if (dueDate != null) {
            DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MM yyyy");
            return "D | " + (isDone ? "1" : "0") + " | "
                    + description + " | " + dueDate.format(output);
        } else {
            DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MM yyyy HHmm");
            return "D | " + (isDone ? "1" : "0") + " | "
                    + description + " | " + dueDateTime.format(output);
        }
    }

    /**
     * Converts the task to a string that can be displayed.
     */
    @Override
    public String toString() {
        if (dueDate != null) {
            DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MM yyyy");
            return "[D]" + super.toString() + " (by: " + dueDate.format(output) + ")";
        } else {
            DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MM yyyy HHmm");
            return "[D]" + super.toString() + " (by: " + dueDateTime.format(output) + ")";
        }
    }
}
