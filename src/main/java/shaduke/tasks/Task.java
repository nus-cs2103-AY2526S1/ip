package shaduke.tasks;

import shaduke.clients.Client;

/**
 * Represents a generic task in the task management system.
 */
public class Task {
    private String description;
    private boolean isDone = false;
    private Client client;

    /**
     * Constructs a Task with the given description.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        assert description != null : "Task Description should not be null";
        this.description = description;
    }

    /**
     * Constructs a Task with the given description and
     * whether it has been done.
     *
     * @param description the description of the task.
     * @param isDone whether the task has been marked as done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task has been done or not.
     *
     * @return true if task is done, false if not done.
     */
    public boolean isDone() {
        return isDone;
    }

    public void addClient(Client client) {
        this.client = client;
    }

    public void deleteClient() {
        this.client = null;
    }

    /**
     * Returns a string representation of the task.
     */
    @Override
    public String toString() {
        String check = isDone ? "[X]" : "[ ]";
        return check + " " + this.description;
    }

    public Client getClient() {
        if (client != null) {
            return client;
        } else {
            return null;
        }
    }

    public String getClientString() {
        if (client != null) {
            return client.toString();
        } else {
            return "";
        }
    }

    /**
     * Converts the task into a format for storage.
     *
     * @return the task in a storable format.
     */
    public String store() {
        return " | " + (isDone ? "1" : "0") + " | " + getDescription();
    }
}