package bobbywasabi.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, boolean isMarked, LocalDateTime deadline) {
        super(description, isMarked);
        this.deadline = deadline;
    }

    public String getDeadline() {
        String output = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));

        return String.format("by: %s",
                output);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + this.getDeadline();
    }

    @Override
    public String getData() {
        String deadlineOutput = this.deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return String.format("D|%s|%s|%s",
                super.getDescription(), super.checked(), deadlineOutput);
    }

}
