package tasks;

import java.time.format.DateTimeFormatter;

/**
 * Abstract class representing a Task in our Pepe application.
 */
public abstract class Task implements Comparable<Task> {
    protected static final DateTimeFormatter SERDE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static final DateTimeFormatter USER_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    private final String name;
    private boolean isDone;

    protected Task(String name) {
        this(name, false);
    }

    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Checks if the Task matches the provided phrase.
     * @return true if there is a match
     */
    public boolean matchesPhrase(String matchPhrase) {
        return name.contains(matchPhrase);
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    protected String getStatusFileIcon() {
        return this.isDone ? "1" : "0";
    }

    /**
     * Getter method for name
     * @return the name of the abstract Task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Serializes the Task class into a string suitable for saving to the file.
     * @return the serialized string for file saving.
     */
    public abstract String toFileInput();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name;
    }
}
