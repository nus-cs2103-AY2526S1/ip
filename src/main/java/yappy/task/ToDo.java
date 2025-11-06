package yappy.task;

/**
 * Represents a {@code ToDo} task which is a type of <code>Task</code>.
 * A {@code ToDo} is the simplest form of task that only requires
 * a description and a completion status.
 *
 * <p>This class extends the <code>Task</code> class and overrides methods
 * for file storage and string representation.</p>
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the given description
     * and completion status.
     *
     * @param description Textual description of the task.
     * @param isCompleted {@code true} if the task has been completed,
     *                    {@code false} otherwise.
     */
    public ToDo(String description, Boolean isCompleted) {
        super(description, isCompleted);
    }


    @Override
    public String toFileString() {
        return "T|" + this.isCompleted() + "|" + this.getDescription() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
