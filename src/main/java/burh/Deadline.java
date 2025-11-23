package burh;
import java.time.LocalDate;

/**
 * Represents a deadline task with a description and due date.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Creates a deadline task with the given description and due date.
     *
     * @param task Description of the deadline task.
     * @param dueDate Due date in yyyy-MM-dd format.
     */
    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = LocalDate.parse(dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dueDate.format(DISPLAY_FORMAT) + ")";
    }
    @Override
    public String getSaveString() {
        return "D|" + super.getSaveString() + super.getDescription() + "|" + dueDate.toString();
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    @Override
    public int compareTo(Task t) {
        if (t instanceof Todo) {
            return -1;
        } else if (t instanceof Deadline d) {
            return this.dueDate.compareTo(d.dueDate);
        } else {
            assert t instanceof Event;
            Event e = (Event) t;
            return this.dueDate.compareTo(e.getFromDate());
        }
    }
}
