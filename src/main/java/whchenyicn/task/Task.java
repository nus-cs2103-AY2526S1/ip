package whchenyicn.task;

/**
 * A Task class to represent tasks to be done.
 */
public class Task {
    private String desc;
    private boolean isDone;

    /**
     * Creates a Task with desc.
     *
     * @param desc Description of Task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    protected String getDesc() {
        return desc;
    }

    /**
     * Returns int as whether task is done.
     *
     * @return Returns 1 for done, 0 for not done.
     */
    protected int getDoneFlag() {
        if (this.isDone) {
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * Returns Status in String form.
     *
     * @return String "X" if done, " " if not done.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }


    /**
     * Marks Task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks Task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns string to save to txt file.
     *
     * @return String to save to txt file.
     */
    public String toSave() {
        return String.format("? | %d | %s", getDoneFlag(), desc);
    }

    public String toString() {
        return "[" + this.getStatus() + "] " + desc;
    }
}
