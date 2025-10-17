package lux.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task representing an item with a deadline.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime by;

    /**
     * Create a new deadline task.
     *
     * @param description task description
     * @param by          deadline as a LocalDateTime
     */
    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HHmm");
        return "[D] " + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
