package smartstelle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * A Deadline task is a type of Task that has a name, completion status, and a
 * specific due date.
 * It extends the Task class and adds functionality to store and format a due
 * date.
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Constructs a new Deadline task with a name, due date, and completion status.
     *
     * @param name        The name or description of the task.
     * @param date        The due date as a string in ISO_LOCAL_DATE_TIME format
     *                    (yyyy-MM-ddTHH:mm).
     * @param isCompleted Whether the task is completed or not.
     * @throws StelleException If the task name is empty.
     */
    public Deadline(String name, String date, boolean isCompleted) throws StelleException {
        super(name, isCompleted);
        this.date = LocalDateTime.parse(date);
    }

    /**
     * Returns a formatted string of the due date.
     * Format pattern: "dd MMM yyyy HH:mm"
     *
     * @return Formatted date string.
     */
    private String formatDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    /**
     * Returns a display string representing the Deadline task for UI or console
     * output.
     * Example format: "[D][ ] TaskName by 01 Sep 2025 10:00"
     *
     * @return The formatted string for display.
     */
    @Override
    public String display() {
        return "[D]" + super.display() + " by " + formatDate();
    }

    /**
     * Returns a string representation of the Deadline task for saving to text.txt
     * file.
     * Format: "D | 0 | TaskName | 2025-09-01T10:00"
     * 
     * @return The string representation suitable for storage.
     */
    public String toSaveFormat() {
        return "D | " + (this.getIsCompleted() ? "1" : "0")
                + " | " + this.getName() + " | " + this.date;
    }

    public LocalDateTime getDate() {
        return this.date;
    }
}
