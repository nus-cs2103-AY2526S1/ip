package mochi;

/**
 * Implements a ToDo task, which is a type of Task that only has a description and no associated date/time.
 */
public class ToDo extends Task {
    /**
     * Creates an uncompleted ToDo task with a description.
     *
     * @param desc the description of the todo item
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Creates a ToDo task with a description and the specified completion status.
     *
     * @param desc the description of the todo item
     * @param status the completion status of the todo item
     */
    public ToDo(String desc, boolean status) {
        super(desc, status);
    }

    @Override
    public String getSaveString() {
        return String.format("T | %d | %s", this.completed ? 1 : 0, super.getDescriptionSaveString());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
