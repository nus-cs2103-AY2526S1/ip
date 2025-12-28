package thecoolerduke.feature;

import java.time.format.DateTimeFormatter;

import thecoolerduke.main.Priority;

/**
 * Represents a task with a name and an editable completion flag of isDone.
 * Additional datetime information can be saved using DATETIME_OUTPUT_FORMAT and DATETIME_INPUT_FORMAT.
 */
public class Task {
    public static final DateTimeFormatter DATETIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
    public static final DateTimeFormatter DATETIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    private final String name;
    private boolean isDone;
    private Priority priority;

    /**
     * Initialises a Task with a name and isDone flag.
     *
     * @param name Name of the task
     * @param isDone Boolean for if the task is done
     * @param priority Integer-based priority of the task
     */
    public Task(String name, boolean isDone, Priority priority) {
        this.name = name;
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * Marks task as done.
     *
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Unmarks task as done.
     *
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Returns the priority level of the task
     *
     * @return Priority level of the task
     */
    public int getPriorityLevel() {
        return priority.getLevel();
    }

    /**
     * Sets priority of the task to the new priority level
     *
     * @param newPriority New priority to be set
     */
    public void setPriorityLevel(Priority newPriority) {
        priority = newPriority;
    }

    /**
     * Checks if the task name is similar to the search name provided, using String.contains()
     *
     * @return true if task name contains the search name, else false
     */
    public boolean isSimilarTaskName(String searchName) {
        return name.contains(searchName);
    }

    /**
     * Formats task information for displaying.
     *
     * @return Task information as a formatted String
     */
    public String showTask() {
        String doneIcon = isDone ? "X" : " ";
        return String.format("(%s) [%s] %s", priority.getLabel(), doneIcon, name);
    }

    /**
     * Formats task information for saving in history.
     *
     * @return Task information as a formatted String
     */
    public String saveTask() {
        int doneFlag = isDone ? 1 : 0;
        return String.format("%d,%s,%d", doneFlag, name, priority.getLevel());
    }
}
