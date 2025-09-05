package dobby.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(byStr, inputFormatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D] " + super.toString() + "(by: " + by + ")";
    }
}
