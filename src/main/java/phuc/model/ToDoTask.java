package phuc.model;

import java.time.LocalDateTime;

/**
 * Represents a simple to-do task without any date/time constraints.
 * Extends the base Task class for basic task functionality.
 */
public class ToDoTask extends Task {
    /**
     * Constructs a new ToDoTask with the specified description.
     *
     * @param description the to-do task description
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Constructs a ToDoTask with completion status.
     *
     * @param description the to-do task description
     * @param isDone whether the task is completed
     */
    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the to-do task to a string format suitable for file storage.
     *
     * @return a string representation for file storage
     */
    @Override
    public String writeToFile() {
        return "T | " + isDone + " | " + description;
    }

    /**
     * Gives the information of the date
     *
     * @return the maximum date since the task can do anytime
     */
    @Override
    public LocalDateTime getSortDateTime() {
        //To do task without date so should appear after dated task
        return LocalDateTime.MAX; // Very distant future
    }

    /**
     * Returns a string representation of the to-do task for display.
     *
     * @return a formatted string showing task type, status, and description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
