package balloon.task;

/**
 * Represents a task. This is an abstract class.
 * A task can either be a {@link Todo},
 * a {@link Deadline}, or an {@link Event}.
 * <p>
 * Each task has a description and a status indicating whether it is done.
 */
public abstract class Task {

    protected String description;
    private boolean isDone = false;

    /**
     * Constructs a Task with the specified description.
     *
     * @param desc the description of the task
     */
    public Task(String desc) {
        this.description = desc;
    }

    /**
     * Returns a string representation of this task for display purposes.
     * <p>
     * Format: "[status] description", where status is "[X]" if done, "[ ]" otherwise.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        String doneIndicator = isDone ? "[X] " : "[ ] ";
        return doneIndicator + description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the status indicator of this task for the purpose of writing to the save file.
     *
     * @return "1" if done, "0" otherwise
     */
    protected String getDoneStatusIndicator() {
        return (isDone ? "1" : "0");
    }

    /**
     * @return a line that represents this Task in the save file.
     */
    public abstract String toSaveFormat();

    /**
     * Checks whether the description of this task contains a given word.
     *
     * @param word the word to search for
     * @return true if the description contains the word, false otherwise
     */
    public boolean containsWord(String word) {
        return description.contains(word);
    }
}
