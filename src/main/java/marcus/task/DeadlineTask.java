package marcus.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    private LocalDate deadline;

    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the task in a format meant for the save file.
     * The format of the string is for easier parsing when reading from the save file.
     */
    @Override
    public String getSaveFileString() {
        return String.format("D|%d|%s|%s", this.isCompleted ? 1 : 0, this.description, this.deadline);
    }

    /**
     * Returns a string representation of the task.
     * The format of the string representation is for the user interface.
     */
    @Override
    public String toString() {
        String formattedDeadline = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }
}
