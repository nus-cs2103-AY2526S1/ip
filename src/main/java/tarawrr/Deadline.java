package tarawrr;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Class - Represents a Task with a description and deadline
 */
public class Deadline extends Task {
    private LocalDate deadline;

    //Constructor initiates an instance of Deadline with name and date
    public Deadline(String name, String date) {
        super(name);
        this.deadline = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        String date = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D] " +
                super.toString() +
                " (by: " + date + ")";
    }

    @Override
    public String toStorageString() {
        String date = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format("D | %d | %s | %s", super.isDone() ? 1 : 0, super.getDescription(), date);
    }

    public Deadline changeDeadline(String newDate) {
        return new Deadline(this.getDescription(), newDate);
    }
}
