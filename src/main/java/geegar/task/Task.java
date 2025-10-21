package geegar.task;

import java.time.format.DateTimeFormatter;

/**
 * The Abstract class that defines the commonalities of Todo, Deadline and Event types
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    // Machine-friendly format (for saving/reading)
    public static final DateTimeFormatter SAVE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // Human-friendly format (for displaying to users)
    public static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Creates a Task instance based on the given description input
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task instance based on teh given description input and sets if the task has been completed
     *
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Getter to check if this task instance has been completed
     *
     * @return the string indication of the taskbeing comepleted or not
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Getter to get description of task
     *
     * @return the task's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is done or not
     * @return
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the tasks as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Method to convert this task into the respective String format for easier saving and reading from file
     * @return
     */
    public abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
