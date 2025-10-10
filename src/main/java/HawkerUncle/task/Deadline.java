package HawkerUncle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task, which has a description, a status, and a deadline
 */
public class Deadline extends Task { //partially taken from Level4 A-Inheritance https://nus-cs2103-ay2526s1.github.io/website/schedule/week2/project.html
    protected LocalDateTime by;

    /**
     * Initializes the task with a description, deadline date and time, and completino status.
     * @param description The description of the deadline task.
     * @param by The date and time by which the task should be completed.
     * @param isDone A boolean indicating whether the task is completed or not.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Retrieves the deadline date adn time for this task.
     * @return The LocalDateTime representing the deadline of the task.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Converts the Deadline task to a string represented for displaying to the user.
     * The format is: [D] description (by: formatted date).
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy ha");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Converts the Deadline task to a format suitable for saving to storage.
     * The format is: D | isDone | description | deadline
     * @return A string in the format suitable for saving the task to storage.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}
