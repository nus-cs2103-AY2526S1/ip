package dobby.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert by != null : "Deadline date 'by' cannot be null";
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D] " + super.toString() + "(by: " + by.format(outputFormatter) + ")";
    }
}
