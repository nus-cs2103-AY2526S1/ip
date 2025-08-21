/**
 * This task extends from task.
 */

public class EventTask extends Task {
    // New fiels declared
    String from;
    String to;

    // Constructor
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
