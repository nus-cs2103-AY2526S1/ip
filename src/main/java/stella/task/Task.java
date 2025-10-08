package stella.task;

import stella.Priority;

/**
 * Contains a String description and a boolean isDone.
 * description describe the task.
 * If isDone is true, the task is completed.
 * If isDone is false, the task is not completed).
 * Task is the parent of 3 classes: Deadline, Event and ToDo.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Priority taskPriority;

    /**
     * Constructs a new Task object with a specified description,
     * isDone status and taskPriority status.
     * The isDone status is set to false by default.
     * The taskPriority status is set to UNDECIDED.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskPriority = Priority.UNDECIDED;
    }

    /**
     * Constructs a new Task object with a specified description,
     * isDone status and taskPriority status.
     * The isDone status is set to false by default.
     *
     * @param description The description of the task.
     * @param taskPriority The priority of the task.
     */
    public Task(String description, Priority taskPriority) {
        this.description = description;
        this.isDone = false;
        this.taskPriority = taskPriority;
    }

    /**
     * Indicates whether a task has been completed or not.
     *
     * @return "X" represent that item is complete, while " " represent item is not completed.
     */
    public String getCurrentStatus() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Marks a task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks a task as uncompleted.
     */
    public void markUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getCurrentStatus() + "] " + this.description;
    }

    // JavaDoc comment for countParameter method is adapted from AI.
    /**
     * Returns the number of inputs (excluding task name) found in user's task creation command,
     * such as event open house/06-07-2025/07-07-2025/HIGH.
     *
     * @param command User's full command for task creation.
     * @return Number of inputs (excluding task name).
     */
    public static long countParameter(String command) {
        return command.chars().filter(c -> c == '/').count();
    }
}
