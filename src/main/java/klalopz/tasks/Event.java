package klalopz.tasks;

import klalopz.enums.Tag;

import java.time.LocalDate;

/**
 * Represents an Event task with a start date and end date.
 * Inherits from Task and adds date range information.
 */
public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs an Event task with the given description, completion status, start date, and end date.
     *
     * @param description The details of the Event task.
     * @param isCompleted True if the task is completed, false otherwise.
     * @param startDate Start date of the event.
     * @param endDate End date of the event.
     */
    public Event(String description, Boolean isCompleted, LocalDate startDate, LocalDate endDate) {
        super(description, isCompleted);

        assert description != null && !description.isBlank() : "Description must not be null or blank";
        assert startDate != null : "Start date must not be null";
        assert endDate != null : "End date must not be null";
        assert !endDate.isBefore(startDate) : "End date must be on or after start date";

        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTaskLogo() {
        return "[E]";
    }

    @Override
    public String toString() {
        Tag tag = this.getTag();
        String tagString = (tag != null && tag != Tag.NONE) ? " " + tag : "";
        return this.getTaskLogo() + this.getCompletedLogo() + " " + this.getDetails()
                + " (from: " + this.getStartDate().format(Task.DATE_FORMATTER)
                + " to: " + this.getEndDate().format(Task.DATE_FORMATTER) + ")"
                + tagString;
    }

    @Override
    public String serialize() {
        return this.getTaskLogo() + " | "  + this.getDetails() + " | " +
                this.getCompleted() + " | " + this.getStartDate().format(Task.DATE_FORMATTER)
                + " | " + this.getEndDate().format(Task.DATE_FORMATTER)
                + " | " + this.getTag().getId();
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
