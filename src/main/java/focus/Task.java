package focus;


/**
 * Represents a generic task with a description and completion status.
 */

public class Task {

    protected String description;
    protected boolean isDone;
    private boolean isTagged = false;
    private Tag tag;

    /**
     * Initializes task with task description and whether the task is done
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public boolean isTagged() {
        return this.isTagged;
    }

    public void setTag(Tag tag) {
        System.out.println("     Nice! I have tagged this task!");
        this.isTagged = true;
        this.tag = tag;
    }

    public Tag getTag() {
        return this.tag;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Prints whether task is done along with task name
     * @return A string of form: [whether task done] task name
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a representation of this task for storage.
     *
     * @return String to show in the task list.
     */
    public String toStorageString() {
        return String.format("| %s | %s", this.isDone ? "1" : "0", this.description);
    }

}
