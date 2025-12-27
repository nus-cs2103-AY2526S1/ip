package Dan.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Task {
    private final String description;
    private Boolean isDone;

    /**
     * Constructs a new Task with the specified completion status and description.
     *
     * @param isDone a String representation of the task's completion status ("true" or "false")
     * @param description the description of the task
     */
    public Task(String isDone, String description) {
        this.description = description;
        this.isDone = Boolean.valueOf(isDone);
    }

    /**
     * Creates a Task object from the given task information array.
     * The first element of the array determines the task type:
     * "T" for ToDo, "D" for Deadline, "E" for Event.
     *
     * @param taskInfo an array containing task type and additional task information
     * @return a Task object of the appropriate subtype
     * @throws IllegalArgumentException if the task information is invalid or insufficient
     */
    public static Task createTask(String[] taskInfo) throws IllegalArgumentException {
        if (taskInfo.length < 1) {
            throw new IllegalArgumentException();
        }

        ArrayList<String> taskInfoArr = new ArrayList<String>(Arrays.asList(taskInfo));
        String taskType = taskInfoArr.get(0);
        taskInfoArr.remove(0);

        switch (taskType) {
            case "T":
                return ToDo.create(taskInfoArr);
            case "D":
                return Deadline.create(taskInfoArr);
            case "E":
                return Event.create(taskInfoArr);
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Returns a status icon indicating whether the task is completed.
     *
     * @return "[X] " if the task is done, "[ ] " if the task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ]"); // mark done task with X
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is completed, false otherwise
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as completed by setting its status to done.
     */
    public void mark() {
        this.isDone = !this.isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return the task's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the type of the task.
     * This method must be implemented by subclasses to return their specific task type.
     *
     * @return the TaskType enum value representing this task's type
     */public abstract TaskType getTaskType();

     public abstract LocalDate getReminderDate();

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return a formatted string showing the task's completion status and description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
