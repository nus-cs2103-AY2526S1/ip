package marcus.task;

public class EventTask extends Task{
    private String start;
    private String end;

    public EventTask(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the task in a format meant for the save file.
     * The format of the string is for easier parsing when reading from the save file.
     */
    @Override
    public String getSaveFileString() {
        return String.format("E|%d|%s|%s|%s", this.isCompleted ? 1 : 0, this.description, this.start, this.end);
    }

    /**
     * Returns a string representation of the task.
     * The format of the string representation is for the user interface.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
