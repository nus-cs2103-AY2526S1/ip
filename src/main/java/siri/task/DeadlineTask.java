package siri.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Class for DeadlineTask
 */
public class DeadlineTask extends Task {
    private String deadline;
    private LocalDate dateTime;

    /**
     * Constructor for deadline task
     * @param description the description of the task
     * @param deadline the deadline of the task
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;

        LocalDate parsed = null;
        try {
            parsed = LocalDate.parse(deadline);
        } catch (Exception e) {
        }
        this.dateTime = parsed;
    }


    /**
     * Returns the string representation of this deadline task,
     * including its type marker "[D]", completion status,
     * description, and deadline information.
     *
     * <p>If the task is marked as done, "[X]" is shown;
     * otherwise "[ ]" is shown.</p>
     *
     * @return a formatted string representing the deadline task
     *         (e.g., "[D][ ] return book (by: Sunday)")
     */
    @Override
    public String display() {
        String ddl;
        if (dateTime != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
            ddl = dateTime.format(dateTimeFormatter);
        } else {
            ddl = deadline;
        }
        if (super.isDone()) {
            return "[D][X] " + super.getDescription() + " (by: " + ddl + ")";
        } else {
            return "[D][ ] " + super.getDescription() + " (by: " + ddl + ")";
        }
    }

    /**
     * Get deadline of the task
     * @return deadline
     */
    public String getDeadline() {
        return deadline;
    }


}
