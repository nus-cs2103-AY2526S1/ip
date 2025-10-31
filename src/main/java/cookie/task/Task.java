package cookie.task;

import cookie.exception.CookieException;

/**
 * Represents an abstract class describing a generic task.
 * Serves as a base class for all other tasks to inherit from.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates new task with the given description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description.strip();
        this.isDone = false;
    }

    /**
     * Returns icon indicating whether the task is done.
     *
     * @return X if task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts task to format for saving.
     * Abstract method to ensure all subclasses of task implement
     * this method in a way appropriate to their classes.
     *
     * @return Saved format of task.
     */
    public abstract String toStoredFormat();

    /**
     * Returns task in original format from saved format.
     *
     * @return Task in original format.
     * @throws CookieException If task provided is not in saved format.
     */
    public static Task toOriginalFormat(String storedFormat) throws CookieException {
        String[] splitStoredFormat = storedFormat.split("\\|");
        String typeOfTask = splitStoredFormat[0].strip();
        boolean isDone = splitStoredFormat[1].strip().equals("1");
        String description = splitStoredFormat[2].strip();

        switch (typeOfTask) {
        case "T":
            Task todo = new Todo(description);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;

        case "D":
            String by = splitStoredFormat[3].strip();
            Task deadline = new Deadline(description, by);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;

        case "E":
            String from = splitStoredFormat[3].strip();
            String to = splitStoredFormat[4].strip();
            Task event = new Event(description, from, to);
            if (isDone) {
                event.markAsDone();
            }
            return event;

        default:
            throw new IllegalArgumentException("Task not in proper format");
        }
    }

    /**
     * Updates task information.
     * Abstract method to ensure all subclasses of task implement
     * this method in a way appropriate to their classes.
     *
     * @param newInformation Information to update task
     * @throws CookieException if input not in proper format.
     */
    public abstract void update(String newInformation) throws CookieException;

    /**
     * Returns task description.
     *
     * @return String form of task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns task in Sting format with its completion status and description.
     *
     * @return String form of task.
     */
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + this.description);
    }
}
