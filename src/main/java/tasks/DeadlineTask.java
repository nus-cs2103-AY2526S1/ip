package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    /**
     * Creates a task with a description and a deadlineString
     * @param description String containing the description
     * @param deadlineString String format of deadline
     */
    public DeadlineTask(String description, String deadlineString) {
        super(description);
        try {
            this.deadline = LocalDateTime.parse(deadlineString, formatter);
        } catch (DateTimeParseException e) {
            this.deadline = null;
        }
    }

    @Override
    public String getTaskType() {
        return "Deadline";
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }
    
    @Override
    public String toString() {
        String m = getMarked() ? "X" : " ";
        return String.format("[%s][%s] %s (By: %s)", 
                "DDLN", m, getDescription(), 
                getDateTimeAsString(deadline));
    }
    
    @Override
    public String getSaveString() {
        return String.format("%s|||%s|||%s|||%s", 
                getTaskType(), getMarked(), getDescription(), 
                getSaveDateTimeAsString(deadline));
    }
}
