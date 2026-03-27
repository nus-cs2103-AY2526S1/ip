package borat.task;

public class Deadline extends Task {

    private final String end;

    /**
     * Creates a deadline task with a description and a due date/time.
     *
     * @param description task description
     * @param end formatted due date/time
     */
    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + end;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + end + ")";
    }
}