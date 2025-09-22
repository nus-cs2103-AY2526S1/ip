package klalopz.tasks;

import klalopz.enums.Tag;

import java.time.LocalDate;

/**
 * Represents a Deadline task with a specific due date.
 * Inherits from Task and adds a due date.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Constructs a Deadline task with the given description, completion status, and due date.
     *
     * @param description The details of the Deadline task.
     * @param isCompleted True if the task is completed, false otherwise.
     * @param dueDate The date by which the task is due.
     */
    public Deadline(String description, Boolean isCompleted, LocalDate dueDate) {
        super(description, isCompleted);

        assert description != null && !description.isBlank() : "Description must not be null or blank";
        assert dueDate != null : "Due date must not be null";

        this.dueDate = dueDate;
    }

    @Override
    public String getTaskLogo() {
        return "[D]";
    }

    @Override
    public String toString() {
        Tag tag = this.getTag();
        String tagString = (tag != null && tag != Tag.NONE) ? " " + tag : "";
        return this.getTaskLogo() + this.getCompletedLogo() + " " + this.getDetails()
                + " (by: " + getDueDate().format(Task.DATE_FORMATTER) + ")"
                + tagString;
    }

    @Override
    public String serialize() {
        return this.getTaskLogo() + " | "  + this.getDetails() + " | "
                + this.getCompleted() + " | " + this.getDueDate().format(Task.DATE_FORMATTER)
                + " | " + this.getTag().getId();
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

}
