package ryuji.task;

/**
 * Represents a simple to-do task without any dates.
 * <p>The {@code ToDo} class is a concrete subclass of the {@code Task} class. It
 * represents tasks that don't require any specific date or time to be specified.
 * The primary characteristic of a {@code ToDo} task is that it has only a label (description)
 * and a completion status (marked or unmarked).</p>
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} task with the specified label.
     *
     * @param label the task label or description
     */
    public ToDo(String label) {
        super(label);
    }

    /**
     * Constructs a {@code ToDo} task with the specified label and completion status.
     *
     * @param label  the task label or description
     * @param isMarked the completion status (true if completed, false otherwise)
     */
    public ToDo(String label, boolean isMarked) {
        super(label, isMarked);
    }

    /**
     * Checks if the task is valid. A valid task must contain at least one word.
     * <p>This validation is done by ensuring that the task label contains at least one space.
     * If there is no space, it is considered invalid (empty label or only a single word).</p>
     *
     * @return {@code true} if the task label contains at least one space, {@code false} otherwise.
     */
    @Override
    boolean checkValid() {
        return this.label.split(" ").length >= 1;
    }

    /**
     * Converts this {@code ToDo} task to a CSV-compatible string format.
     * <p>The CSV format is as follows: "T,[status icon],label". The status icon is
     * "X" if the task is marked, otherwise a space character " ".</p>
     *
     * @return a CSV string representing the task
     */
    @Override
    public String toCsvRow() {
        return "T," + getStatusIcon() + "," + this.label;
    }

    /**
     * Returns a string representation of this {@code ToDo} task.
     * <p>The string format is: "[T][status icon] label", where the status icon is either
     * "X" (completed) or a space " " (incomplete), and the label is the task description.</p>
     *
     * @return the formatted string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
