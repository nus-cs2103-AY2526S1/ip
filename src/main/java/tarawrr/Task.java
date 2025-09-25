package tarawrr;

/**
 * Task Class - Represents a task with a description and completion status
 */
public abstract class Task {
    private boolean status;
    private String name;

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public void complete() {
        this.status = true;
    }

    public void uncomplete() {
        this.status = false;
    }

    // Getters
    public boolean isDone() {
        return status;
    }
    public String getDescription() {
        return name;
    }

    // Get the String representation of a task to be stored in storage
    public abstract String toStorageString();

    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
