package friday.task;

abstract public class Task {
    private String desc;
    private Boolean isDone;

    /**
     * Constructor
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Returns description of the task
     * @return String description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns full description of the task including the '/by', '/from', '/to' parts if applicable
     * @return String full description
     */
    public String getFullDesc() {
        return desc;
    }

    /**
     * Returns true if the task is done, false otherwise
     * @return Boolean isDone
     */
    public Boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns status icon of the task
     * @return String status ("[X]" or "[ ]")
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Abstract method to get the type of task
     * @return String type of task
     */
    public abstract String getTypeOfTask();

}
