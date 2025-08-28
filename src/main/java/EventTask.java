/// This class extends from Task, representing an event task.
///
/// @author Ravichandran Gokul
public class EventTask extends Task {
    // New fields declared
    String from;
    String to;

    /**
     * Constructs a new {@code DeadlineTask} object with the name of the task and its deadline.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param nameOfTask  The name of the task
     * @param from        The start time
     * @param to          The end time
     */
    public EventTask(String nameOfTask, String from, String to) {
        super(nameOfTask);
        this.from = from;
        this.to = to;
    }

    // toString overriding
    @Override
    public String toString() {
        return "[E][" + getCompletionStatus() + "] " + getNameOfTask() + " (from: " + from + " to: " + to + ")";
    }
}
