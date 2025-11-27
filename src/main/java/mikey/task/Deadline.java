package mikey.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h.mma");

    /**
     * Initializes a Deadline instance
     * @param description Description of task
     * @param deadline deadline of task in "D/M/YYYY HHMM" format
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toSaveString() {
        String result = "D | " + (isDone ? "1" : "0") + " | " + description + " | " + this.deadline.format(FORMATTER);
        if (isTagged()) {
            return result + " | " + tag;
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "[D]" + super.toString() + "\n       (by: " + this.deadline.format(FORMATTER) + ")";
        return result;
    }
}
