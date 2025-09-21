package merlie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a due date.
 */
public class Deadline extends Task {
    private LocalDateTime by; // store the deadline as LocalDate
    private boolean hasByTime;

    /**
     * Constructs a Deadline with description, due date, and time flag.
     *
     * @param description Task description.
     * @param by Due date of the task.
     * @param hasByTime Whether the due date includes a specific time.
     */
    public Deadline(String description, LocalDateTime by, boolean hasByTime) {
        super(description);
        this.by = by;
        this.hasByTime = hasByTime;
    }

    private boolean getHasByTime() {
        return this.hasByTime;
    }

    /**
     * Returns the deadline of this task.
     *
     * @return Deadline as a LocalDate.
     */
    private LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Sets or updates the deadline of this task.
     *
     * @param by The new deadline.
     */
    private void setBy(LocalDateTime by, boolean hasByTime) {
        this.by = by;
        this.hasByTime = hasByTime;
    }

    @Override
    public boolean isDuplicate(Task other) {
        if (this.isSameDescription(other)) {
            if (other instanceof Deadline d) {
                return this.by.equals(d.getBy());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isUpdateSuccessful(Task other) {
        if (this.isSameDescription(other) && other instanceof Deadline d) {
            setBy(d.getBy(), d.getHasByTime());
            return true;
        }
        return false;
    }

    @Override
    public String format() {
        if (hasByTime) {
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                    + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else {
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                    + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    @Override
    public String toString() {
        if (hasByTime) {
            return "[D]" + super.toString() + " (by: "
                    + by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}
