package dyke.tasks;

/**
 * Houses methods for the Tasks recognized by DYKE.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a {@code Task} object
     * @param desc Description of {@code Task}
     */
    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    /**
     * Asserts that {@code Task} has been marked as done.
     * @return {@code Task}
     */
    public Task isDone() {
        this.isDone = true;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Encodes Tasks into a suitable format for {@code Storage}.
     * @return The message after {@code encode} operation.
     */
    public String encode() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Represents Tasks into a suitable format for {@code Ui}.
     * @return {@code String}
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + this.description;
    }
}
