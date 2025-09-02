package LunarBot.Tasks;

public class Task {
    String name;
    Boolean completed;

    public Task(String name, Boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    /**
     * Sets completion status of task
     *
     * @param bool Boolean to set the completeness of the task to
     */
    public void setCompleted(Boolean bool) {
        this.completed = bool;
    }

    /**
     * Returns string version of the task
     *
     * @return string of task
     */
    public String print() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.name;
    }

    /**
     * Returns comma separated version of the task
     *
     * @return comma separated value
     */
    public String getAsCsv() {
        return "X," + this.completed.toString() + "," + this.name;
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