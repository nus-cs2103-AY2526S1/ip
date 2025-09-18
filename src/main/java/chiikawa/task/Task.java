package chiikawa.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that represents tasks in general.
 */
public class Task {
    private static final Pattern PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    private static int taskCount = 0;

    public enum Priority {HIGH, LOW};

    protected boolean isCompleted = false;
    protected boolean isHidden = false;
    protected String name;

    private Matcher matcher;
    private Priority priority = Priority.LOW;


    /**
     * Constructor for creating a task, and increasing the TASK_COUNT.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        taskCount++;
    }

    /**
     * Constructor for creating a task that is being loaded from save files.
     *
     * @param name Name of the task.
     * @param isCompleted Status of the task.
     */
    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
        taskCount++;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Returns String representation of the completion status of the task.
     *
     * @return 1 if task is complete else 0.
     */
    public String getStatusIcon() {
        return (this.isCompleted ? "1" : "0");
    }

    /**
     * Marks the task as completed.
     */
    public void markTask() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmarkTask() {
        this.isCompleted = false;
    }

    /**
     * Changes the priority of the task depending on user input.
     * @param newPriority either HIGH or LOW.
     */
    public void updatePriority(Priority newPriority) {
        this.priority = newPriority;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Decrements the number of tasks that has not been deleted.
     */
    public void deleteTask() {
        taskCount--;
    }

    /**
     * Hides the task from the display.
     */
    public void hideTask() {
        this.isHidden = true;
    }

    /**
     * Unhides the task from the display.
     */
    public void unhideTask() {
        this.isHidden = false;
    }

    public boolean isHidden() {
        return this.isHidden;
    }

    /**
     * Reformats the time from one format to another.
     *
     * @param time String representation of the time.
     * @return Reformatted String representation of the time.
     */
    protected String reformatTime(String time) {
        matcher = PATTERN.matcher(time);
        LocalDate localDateTime = null;
        String formattedDate = "";

        if (matcher.find()) {
            time = matcher.group();
            localDateTime = LocalDate.parse(time);
            formattedDate = localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        return formattedDate;
    }

    @Override
    public String toString() {
        return "| " + this.getStatusIcon() + " | " + this.priority + " | " + this.name;
    }
}
