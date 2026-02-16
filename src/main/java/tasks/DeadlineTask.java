package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Task class used in the Nicholas chatbot.
 * <p>
 * Creates Deadline tasks that requires a due date and a description.
 */
public class DeadlineTask extends Task {
    private LocalDate dueDate;

    public DeadlineTask(LocalDate dueDate, String description) {
        super(description);
        this.dueDate = dueDate;
    }

    public DeadlineTask(LocalDate dueDate, String description, boolean isDone) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    public String saveFileFormat() {
        return "D|" + super.saveFileFormat() + "|" + this.dueDate;
    }

    @Override
    public String toString(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + "(by: " + dueDate.format(pattern) + ")";
    }
}
