package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDate by;

    public DeadlineTask(String taskName, LocalDate by) {
        super(taskName);
        this.by = by;
    }

    public DeadlineTask(String taskName, boolean isDone, LocalDate by) {
        super(taskName, isDone);
        this.by = by;
    }

    @Override
    public String toStorage() {
        String doneMark = isDone ? "1" : "0";
        return "D | " + doneMark + " | " + taskName + " | " + by;
    }

    @Override
    public LocalDate getDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}