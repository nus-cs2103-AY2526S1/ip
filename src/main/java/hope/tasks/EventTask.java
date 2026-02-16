package hope.tasks;

import hope.common.MaybeDate;

/**
 * A class representing an event task with a start and end date, extending the Task class.
 * It includes a description, a task type (E for event), and start and end dates.
 */
public class EventTask extends Task {
    private MaybeDate from;
    private MaybeDate to;

    /**
     * Constructs an EventTask with the specified description, start date, and end date.
     *
     * @param description the description of the event task
     * @param from the start date of the event as a string, parsed into a MaybeDate object
     * @param to the end date of the event as a string, parsed into a MaybeDate object
     */
    public EventTask(String description, String from, String to) {
        super(description, Task.TaskType.E);
        this.from = MaybeDate.parse(from);
        this.to = MaybeDate.parse(to);
    }

    /**
     * Formats the event task into a string representation suitable for storage.
     * The format includes the task type, status (1 for done, 0 for not done), description,
     * start date, and end date.
     *
     * @return a formatted string representing the event task for storage
     */
    @Override
    public String format() {
        int status;

        if (this.isDone) {
            status = 1;
        } else {
            status = 0;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(type).append("|").append(status)
                .append("|").append(description)
                .append("|").append(from)
                .append("|").append(to);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTaskType()).append(" [").append(this.getStatusIcon())
                .append("] ").append(this.description).append(" (from: ")
                .append(from).append(" to: ").append(to).append(")");
        return sb.toString();
    }


}
