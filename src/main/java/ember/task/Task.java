package ember.task;

import ember.exception.MissingInformationException;

import java.time.LocalDate;

/**
 * Represents a basic task with a name and completion status.
 * Supports marking and unmarking the task as done.
 */
public class Task {
    private String name;
    private boolean isMarked;

    /**
     * Constructs a new Task with the specified name.
     *
     * @param name the description of the task; must not be blank
     * @throws MissingInformationException if the task description is empty or blank
     */
    public Task(String name) throws MissingInformationException {
        if (name.isBlank()) {
            throw new MissingInformationException("The description cannot be empty.");
        }
        this.name = name;
        this.isMarked = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isMarked = false;
    }

    public String toString() {
        String sign = (isMarked) ? "X" : " ";
        return "[" + sign + "] " + this.name;
    }

    public LocalDate getDate(){
        return LocalDate.MIN;
    }

    /**
     * Returns the description of the task.
     * By default, this is the same as the string representation.
     *
     * @return the task description
     */
    public String description() {
        return toString();
    }

    public String getName() {
        return name;
    }

    public String parseFormat() {
        return toString();
    }
}
