package marcus.task;

public class ToDoTask extends Task{
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task in a format meant for the save file.
     * The format of the string is for easier parsing when reading from the save file.
     */
    @Override
    public String getSaveFileString() {
        return String.format("T|%d|%s", this.isCompleted ? 1 : 0, this.description);
    }

    /**
     * Returns a string representation of the task.
     * The format of the string representation is for the user interface.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
