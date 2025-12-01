package tasks;

/**
 * Represents a task with as an event.
 * Extends Task with a start time and end time
 */
public class EventTask extends Task {
    /**
     * Constructor of an event task
     *
     * @param information TaskInformation object holding information of task
     */
    public EventTask(TaskInformation information) {
        super(information);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + super.getStartTime().toPrint()
                + " to: " + super.getEndTime().toPrint() + ")";
    }

    @Override
    public String toSave() {
        return "E | " + super.toSave() + " | "
                + super.getStartTime().toString()
                + " | " + super.getEndTime().toString();
    }

    @Override
    public void setInformation(TaskInformation info) {
        super.setInformation(info);
    }
}
