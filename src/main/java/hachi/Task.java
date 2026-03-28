package hachi;

import java.time.LocalDateTime;

/**
 * Represents a generic task with a description and a completion status. This class provides
 * basic functionality for tasks, including the ability to mark and unmark a task as completed.
 * The task also includes a description of the task and its completion state (either completed or not).
 */
public class Task {
    Boolean isCompleted = false;
    String description;
    Note note;

    /**
     * Constructs a new task with the specified description. The task is initially marked as incomplete.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Constructs a new task with the specified description and completion status.
     *
     * @param description the description of the task
     * @param completed   the completion status of the task (true if completed, false if incomplete)
     */
    public Task(String description, Boolean completed) {
        this.description = description;
        this.isCompleted = completed;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        isCompleted = false;
    }

    /**
     * Adds an instance of a note object that contains more information about the task
     */
    public void addNote(String information, LocalDateTime time) {
        this.note = new Note(information, time);
    }

    /**
     * Returns a string representation of the task. The string includes the completion status
     * and the description of the task. If the task is completed, an "X" is displayed, otherwise
     * a space is shown to indicate that the task is incomplete.
     *
     * @return a string representing the task with its completion status and description
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isCompleted ? "X" : " ", description);
    }

    /**
     * Returns the string representation of the note if it exists
     * else return null
     */
    public String printNote() {
        if (this.note == null) {
            return null;
        }
        return this.note.toString();
    }

    /**
     * Returns reference of the associated note
     */
    public Note getNote() {
        return note;
    }
}
