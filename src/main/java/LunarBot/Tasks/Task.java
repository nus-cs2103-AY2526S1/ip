package LunarBot.Tasks;

public class Task {
    String name;
    Boolean isCompleted;

    public Task(String name, Boolean completed) {
        this.name = name;
        this.isCompleted = completed;
    }

    /**
     * Sets completion status of task
     *
     * @param bool Boolean to set the completeness of the task to
     */
    public void setCompleted(Boolean bool) {
        this.isCompleted = bool;
    }

    /**
     * Returns string version of the task
     *
     * @return string of task
     */
    public String print() {
        return "[" + (this.isCompleted ? "X" : " ") + "] " + this.name;
    }

    /**
     * Returns comma separated version of the task
     *
     * @return comma separated value
     */
    public String getAsCsv() {
        return "X," + this.isCompleted.toString() + "," + this.name;
    }

    /**
     * Returns name of Task
     *
     * @return name of task
     */
    public String getName() {
        return this.name;
    }
}