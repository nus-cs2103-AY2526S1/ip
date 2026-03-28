package com.arnavjhajharia.penguin.model.task;

public abstract class Task {
    protected final int id;
    protected final String name;
    protected boolean isDone;


    /**
     * Constructs a new task with the given description and identifier.
     *
     * @param task the description of the task
     * @param id   the unique ID of the task
     */
    public Task(String task, int id) {
        name = task;
        this.id = id;
    }

    /**
     * Marks this task as not completed.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markDone() {
        isDone = true;
    }


    /**
     * Returns a string representation of the task for display purposes.
     * Format: {@code [X] description} if done,
     * or {@code [ ] description} if not done.
     *
     * @return a user-facing string representation of the task
     */
    @Override
    public String toString() {

        return String.format("[%s] %s",
                isDone ? "X" : "",
                name
        );

    }

    /**
     * Converts this task into a line suitable for storage in a file.
     * <p>
     * Subclasses must implement this to define how their additional
     * fields (e.g., deadlines, event times) are serialized.
     * </p>
     *
     * @return a string representation of the task for storage
     */
    public abstract String toStorageLine();

    /**
     * Returns a storage-friendly flag for the completion status.
     * <p>
     * {@code "1"} if the task is marked as done,
     * {@code "0"} otherwise.
     * </p>
     *
     * @return the done-flag string for this task
     */
    protected String doneFlag() { return isDone ? "1" : "0"; }



}
