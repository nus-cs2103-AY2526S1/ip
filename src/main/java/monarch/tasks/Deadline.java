package monarch.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, consisting of a description and an end date.
 */
public class Deadline extends Task {
    private final LocalDateTime end;

    /**
     * Constructor for a Deadline Task.
     *
     * @param description A description for the task.
     * @param end The end date for this task.
     */
    public Deadline(String description, String end) {
        super(description, "D");
        this.end = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    @Override
    public String[] getInfo() {
        return new String[] {
                end.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))};
    }

    @Override
    public String toString() {
        return (String.format("[%s][%s] %s (by: %s)",
                "D",
                super.getStatusIcon(),
                super.getDescription(),
                this.end
        ));
    }

    /**
     * Compares based on type of task, alphabetically or by end date.
     * @param other the object to be compared.
     * @return 0, 1 or -1.
     */
    @Override
    public int compareTo(Task other) {
        if (other instanceof ToDo) {
            return -1;
        } else if (other instanceof Event) {
            return 1;
        }
        if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            int ifEarlierEnd = this.end.compareTo(otherDeadline.end);

            // Determine which event start & ends earlier, then return that
            if (ifEarlierEnd != 0) {
                return ifEarlierEnd;
            }
            // Else compare their descriptions
            return this.getDescription().compareTo(other.getDescription());
        }
        return 1;
    }
}
