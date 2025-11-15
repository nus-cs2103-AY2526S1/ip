package shaniqua.taskcore.tasks;

public class Event extends Task {
    private FlexibleDateTime from;
    private FlexibleDateTime to;

    /**
     * Constructs event object that encapsulates two FlexibleDateTimes representing start and end.
     * @param name name/ description of event
     * @param from string input of date of commencement
     * @param to string input of date of conclusion
     * @throws InvalidTaskDataException if inappropriate name or date input
     */
    public Event(String name, String from, String to) throws InvalidTaskDataException {
        super(name);
        try {
            this.from = FlexibleDateTime.parse(from);
            this.to = FlexibleDateTime.parse(to);
        } catch (FlexibleDateTimeException e) {
            throw new InvalidTaskDataException("Invalid date/ time entry");
        }
    }

    /**
     * Compares event object with Object o for equality
     * @param object the object to compare with task.
     * @return boolean of equality
     */
    public boolean equals(Object object) {
        if (object instanceof Event) {
            Event temp = (Event) object;
            return super.name == temp.name && this.from == temp.from && this.to == temp.to;
        }
        return false;
    }

    /**
     * Returns string representation of event.
     * @return string representation of event.
     */
    public String toString() {
        return "[E] " + super.toString() + String.format(" (from: %s to: %s)", from, to);
    }
}
