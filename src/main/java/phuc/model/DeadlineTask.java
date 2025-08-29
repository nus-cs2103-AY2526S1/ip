package phuc.model;

import phuc.parser.DateTimeProcess;
import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public DeadlineTask(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String writeToFile() {
        return "D | " + isDone + " | " + description + " | " + DateTimeProcess.formatForFile(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeProcess.formatForDisplay(deadline) + ")";
    }
}
