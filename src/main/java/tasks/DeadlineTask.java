package tasks;

/**
 * Represents a task with a deadline.
 * Extends Task with a deadline
 */
public class DeadlineTask extends Task {
    /**
     * Constructor of a Deadline task
     *
     * @param information TaskInformation object holding information of task
     */
    public DeadlineTask(TaskInformation information) {
        super(information);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + super.getEndTime().toPrint() + ")";
    }

    @Override
    public String toSave() {
        return "D | " + super.toSave() + " | "
                + super.getEndTime().toString();
    }



    @Override
    public void setInformation(TaskInformation info) {
        super.setInformation(info);
    }
}