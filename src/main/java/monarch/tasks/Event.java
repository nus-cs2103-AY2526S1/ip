package monarch.tasks;

/**
 * Represents an Event task, consisting of a description, and a pair of start & end
 * dates.
 */
public class Event extends Task {
    private String start = "";
    private String end = "";

    /**
     * Constructor for an Event task.
     *
     * @param description A description for the task.
     * @param start A start date for this task.
     * @param end An end date for this task.
     */
    public Event(String description, String start, String end) {
        super(description, "E");
        this.start = start;
        this.end = end;
    }

    @Override
    public String[] getInfo() {
        return new String[] {this.start, this.end};
    }

    @Override
    public String toString() {
        return (String.format("[%s][%s] %s (from: %s to: %s)",
                "E",
                super.getStatusIcon(),
                super.getDescription(),
                this.start,
                this.end
        ));
    }

    /**
     * Compares based on type of task, alphabetically or by start / end times.
     * @param other the object to be compared.
     * @return 0, 1 or -1.
     */
    @Override
    public int compareTo(Task other) {
        if (other instanceof ToDo || other instanceof Deadline) {
            return -1;
        }
        if (other instanceof Event) {
            Event otherEvent = (Event) other;
            int ifEarlierStart = this.start.compareTo(otherEvent.start);
            int ifEarlierEnd = this.end.compareTo(otherEvent.end);

            // Determine which event start & ends earlier, then return that
            if (ifEarlierStart != 0) {
                return ifEarlierStart;
            } else if (ifEarlierEnd != 0) {
                return ifEarlierEnd;
            }
            // Else compare their descriptions
            return this.getDescription().compareTo(other.getDescription());
        }
        return 1;
    }
}
