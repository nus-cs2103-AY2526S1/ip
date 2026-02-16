package july.tasks;

import july.datetime.StringTime;

/**
 * Represents a basic todo task without any specific deadline or time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this todo task.
     * Includes the task type marker "[T]" followed by the parent task information.
     *
     * @return a formatted string showing the todo task details
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns the save format representation of this todo task.
     * Creates a string suitable for file storage containing the task type,
     * completion status, and description.
     *
     * @return a formatted string for saving the todo task to storage
     */
    public String toSave() {
        return String.format("T %s %s", super.isDone ? "1" : "0", super.description);
    }

    /**
     * Checks if this todo task should be included in date-specific queries.
     *
     * @param o the StringTime object representing the date to check against (not used for todos)
     * @return true if the todo task is not done, false if it's already completed
     */
    public boolean check(StringTime o) {
        return !super.isDone;
    }
}
