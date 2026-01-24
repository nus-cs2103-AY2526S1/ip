package noob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import noob.exception.NoobException;

/**
 * A subclass of Task that represents tasks with a single deadline
 */
public class DeadlineTask extends Task {

    private LocalDate deadline;

    public DeadlineTask(String text, String deadline) throws NoobException {
        super(text);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new NoobException("Invalid deadline format, please provide a date of the form yyyy-mm-dd");
        }
    }

    /**
     * Formats the deadline into a string
     *
     * @return A string of the form "MMM d yyyy"
     */
    public String getFormattedDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Formats the deadline into an ISO string
     *
     * @return An ISO string of the deadline
     */
    public String getIsoDeadline() {
        return this.deadline.toString();
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", getFormattedDeadline());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeadlineTask t) {
            boolean deadlineEquals = this.deadline.equals(t.deadline);
            boolean descEquals = this.getDesc().equals(t.getDesc());
            return deadlineEquals && descEquals;
        }
        return false;
    }
}
