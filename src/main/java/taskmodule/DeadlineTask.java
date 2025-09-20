package taskmodule;

import java.time.LocalDate;

public class DeadlineTask extends Task {
    public LocalDate by;

    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDataString() {
        return String.format("D | %s | %s", super.toDataString(), this.by);
    }
}
