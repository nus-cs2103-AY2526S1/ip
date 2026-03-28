package chani.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//TODO: Catch this.by DateTime errors

/**
 * Represents a deadline task with a description and a due date.
 */
public class DeadlineTask extends Task {
    protected LocalDate by;

    /**
     * Constructor.
     *
     * @param description The task description.
     * @param by The due date in yyyy-MM-dd format.
     */
    public DeadlineTask(String description, String by) {
        super(description, "D");

        assert by != null : "DeadlineTask date string cannot be null";

        this.by = LocalDate.parse(by);
    }

    @Override
    public List<String> toAttributeList() {
        List<String> list = super.toAttributeList();
        list.add(by.toString());
        return list;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
