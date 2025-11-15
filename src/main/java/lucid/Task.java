package lucid;

/**
 * Stores basic task information including name and completion status
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Creates a incomplete task with the specified name
     * @param s String containing task name
     */
    public Task(String s) {
        this.name = s;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isComplete() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    public String toDataString() {
        return "placeholder";
    }

    /**
     * Marks task as complete
     */
    public void markAsComplete() {
        if (isDone) {
            Ui.taskAlreadyCompletedMessage();
        } else {
            this.isDone = true;
        }
        assert this.isDone;
    }

    /**
     * Marks task as not complete
     */
    public void markAsNotComplete() {
        if (!isDone) {
            Ui.taskNotCompletedYetMessage();
            return;
        } else {
            this.isDone = false;
        }
        assert !this.isDone;
    }
}
