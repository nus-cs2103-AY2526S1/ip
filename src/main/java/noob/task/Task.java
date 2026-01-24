package noob.task;

/**
 * The main class of tasks that stores description and completion status
 */
public abstract class Task {
    private boolean isDone = false;
    private String desc;

    public Task(String desc) {
        this.desc = desc;
    }

    /**
     * Marks the task as completed
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as uncompleted
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task
     *
     * @return True if the task is completed
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the enum type of the task
     */
    public abstract TaskType getType();

    /**
     * Returns the description of the task
     */
    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        String s = isDone ? "[X]" : "[ ]";
        return s + " " + desc;
    }
}

