package talkgpt.task;

import java.util.Objects;

import talkgpt.TalkgptException;

/**
 * Represents a generic task in the TalkGPT application.
 * Provides common properties and methods for all task types.
 */
public abstract class Task {
    private final String task;
    private boolean done;
    private String tag;

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param task The description of the task.
     * @param done The completion status of the task.
     * @param tag  The tag associated with the task.
     */
    public Task(String task, boolean done, String tag) {
        this.task = task;
        this.done = done;
        this.tag = tag;
    }

    /**
     * Gets the tag associated with this task.
     *
     * @return The tag string for this task.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getStatus() {
        return this.done;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.task;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Deserializes a string into a Task object.
     *
     * @param input The serialized task string.
     * @return The deserialized Task object.
     * @throws TalkgptException If the task type is invalid.
     */
    public static Task deserialize(String input) throws TalkgptException {
        String[] parts = input.split("\\s*\\|\\s*");
        String type = parts[0];

        switch(type) {
        case "T" -> {
            return ToDo.deserialize(parts);
        }
        case "D" -> {
            return Deadline.deserialize(parts);
        }
        case "E" -> {
            return Event.deserialize(parts);
        }
        default -> throw new TalkgptException("Invalid task type");
        }
    }

    /**
     * Serializes the task into a string representation.
     *
     * @return The serialized task string.
     */
    public abstract String serialize();

    /**
     * Returns the string representation of the task, including its status.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String checkbox = done ? "[X]" : "[ ]";
        return checkbox + " " + task;
    }

    /**
     * Checks if this task is equal to another object based on their string representations.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (Objects.equals(o.toString(), this.toString())) {
            return true;
        } else {
            return false;
        }
    }
}
