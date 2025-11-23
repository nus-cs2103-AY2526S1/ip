package burh;
import java.time.LocalDate;

/**
 * Represents an event task with a description, start date and end date.
 */
public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Creates an event task with the given description, start date and end date.
     *
     * @param task Description of the event task.
     * @param from Start date in yyyy-MM-dd format.
     * @param to End date in yyyy-MM-dd format.
     */
    public Event(String task, String from, String to) {
        super(task);
        this.fromDate = LocalDate.parse(from);
        this.toDate = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + fromDate.format(DISPLAY_FORMAT)
                + " to: " + toDate.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String getSaveString() {
        return "E|" + super.getSaveString() + super.getDescription() + "|"
                + fromDate.toString() + "|" + toDate.toString();
    }

    public LocalDate getFromDate() {
        return this.fromDate;
    }

    @Override
    public int compareTo(Task t) {
        if (t instanceof Todo) {
            return -1;
        } else if (t instanceof Deadline d) {
            return this.fromDate.compareTo(d.getDueDate());
        } else {
            assert t instanceof Event;
            Event e = (Event) t;
            return this.fromDate.compareTo(e.getFromDate());
        }
    }
}
