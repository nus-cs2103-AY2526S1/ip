package bobby.task;

/**
 * subclass of Task
 */
public class ToDo extends Task {
    public ToDo(String description, boolean isMark) {
        super(description, isMark);
    }

    /**
     * used to categorise tasks
     * @return task type
     */
    @Override
    public int getTaskType() {
        return 0;
    }

    /**
     * converting the task to a String friendly format
     * @return String that is saved in storage
     */
    @Override
    public String toStorage() {
        return super.toStorage();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
