package mel.tasks;

/**
 * Represents the event task
 */
public class Event extends Task {

    protected String startTime;
    protected String endTime;

    /**
     * Constructor for event task
     *
     * @param description
     * @param startTime
     * @param endTime
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;

    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.startTime, this.endTime);

    }

    @Override
    public String toSaveString() {
        return "E" + super.toSaveString() + " | " + startTime + " | " + endTime;

    }

    /**
     * Converts the savedString into a event task
     *
     * @param savedString
     * @return Task
     */
    public static Task fromSavedString(String savedString) {
        String[] saved = savedString.split(" \\| ");
        Task task = new Event(saved[2], saved[3], saved[4]);
        if (saved[1].equals("1")) {
            task.markAsDone();

        }
        return task;

    }

    /**
     * Changes the start time for the event task
     *
     * @param newValue
     */
    public void setStart(String newValue) {
        this.startTime = newValue;

    }

    /**
     * Changes the end time for the event task
     *
     * @param newValue
     */
    public void setEnd(String newValue) {
        this.endTime = newValue;

    }

}
