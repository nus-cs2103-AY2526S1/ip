package tuesday.task;

import java.time.LocalDateTime;

/**
 *  Implementation of TodoTask Class
 */
public class TodoTask extends Task {
    /**
     * Construct Deadline task with a description
     * @param description
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Return the String of type
     * @return
     */
    public String getType() {
        return "T";
    }

    /**
     * Return the detailed to-do time format for saving data to storage
     * @return ""
     */
    public String getTime() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                this.isDone() ? "X" : " " ,
                this.getDescription());
    }

    /**
     * Return TaskType
     * @return
     */
    @Override
    public TaskEnums.TaskType getTaskType() {
        return TaskEnums.TaskType.TODO;
    }

    /**
     * Return the LocalDateTime
     * @return
     */
    @Override
    public LocalDateTime getLDTTime() {
        return null;
    }
}
