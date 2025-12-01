package tasks;

/**
 * Represents a task.
 */
public class TodoTask extends Task {

    /**
     * Constructor of Todo task
     *
     *
     * @param information TaskInformation object holding information of task
     */
    public TodoTask(TaskInformation information) {
        super(information);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T | " + super.toSave();
    }

    @Override
    public void setInformation(TaskInformation info) {
        super.setInformation(info);
    }
}
