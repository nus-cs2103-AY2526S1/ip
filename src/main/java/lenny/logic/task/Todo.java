package lenny.logic.task;

/**
 * Represents a simple task without a specific deadline or event duration.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task.
     *
     * @param taskName Description of the todo task.
     * @param isDone True if the todo is already completed, otherwise false.
     */
    public Todo(String taskName, Boolean isDone) {
        super(taskName);
        this.setIsDone(isDone);
    }

    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + (this.getIsDone() ? "[X] " : "[ ] ") + this.getTaskName();
    }


}
