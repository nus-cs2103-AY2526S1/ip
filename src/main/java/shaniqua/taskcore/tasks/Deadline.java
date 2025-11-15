package shaniqua.taskcore.tasks;

public class Deadline extends Task {
    private final FlexibleDateTime dateTime;

    /**
     * Constructs deadline object
     * @param name string of description
     * @param by string input of time task is due by
     * @throws InvalidTaskDataException if date is entered wrongly
     */
    public Deadline(String name, String by) throws InvalidTaskDataException {
        super(name);
        try {
            dateTime = FlexibleDateTime.parse(by);
        } catch (FlexibleDateTimeException e) {
            throw new InvalidTaskDataException("Invalid date/ time entry");
        }
    }

    /**
     * Compares two deadline objects for equality
     * @param object the object to compare with deadline.
     * @return boolean of status of equality.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Deadline) {
            Deadline temp = (Deadline) object;
            return super.name == temp.name && this.dateTime == temp.dateTime;
        }
        return false;
    }

    /**
     * Returns String representation of the task.
     * @return String representation
     */
    public String toString() {
        return "[D] " + super.toString() + String.format(" (by: %s)", dateTime.toString());
    }
}
