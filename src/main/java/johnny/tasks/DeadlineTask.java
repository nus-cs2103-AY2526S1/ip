package johnny.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a deadline date
 */
public class DeadlineTask extends Task {
    protected LocalDate deadline;
    protected String formattedDate;
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");;

    /**
     * Creates a new DeadlineTask instance with the given name and deadline
     * 
     * @param name
     * @param deadline
     */
    public DeadlineTask(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
        this.formattedDate = deadline.format(formatter);
    }

    /**
     * Creates a new DeadlineTask with the given completed boolean
     * 
     * @param name
     * @param completed Whether task is done
     * @param deadline
     */
    public DeadlineTask(String name, boolean completed, LocalDate deadline) {
        super(name, completed);
        this.deadline = deadline;
        this.formattedDate = deadline.format(formatter);
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    public String getStoredString() {
        if (isCompleted)
            return "D|1|" + this.name + "|" + formattedDate;
        return "D|0|" + this.name + "|" + formattedDate;
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[D][X] " + super.name + " (by: " + formattedDate + ")";
        } else {
            return "[D][ ] " + super.name + " (by: " + formattedDate + ")";
        }
    }
}
