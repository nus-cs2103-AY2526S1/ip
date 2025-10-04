package angsoontong.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;

    /**
     * constructor
     * @param name String name to describe task
     * @param dueDate LocalDate indicating the date the task is due
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = LocalDate.parse(dueDate);
    }

    /**
     * custom toString representation for deadline
     */
    @Override
    public String toString() {
        return withTags(String.format("[D]" + super.toString() +
                "(by: " + dueDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")"));
    }

    /**
     * method to format task description to write to storage file
     */
    @Override
    public String toFileFormat() {
        return "D | " +
                (super.isDone() ? "1" : "0") +
                " | " + super.getName() +
                " | " + this.dueDate +
                tagsForFile();
    }
}
