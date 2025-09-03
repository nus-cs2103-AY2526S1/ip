package sam.task;

public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description);
        if (isDone) this.markDone();
        this.by = by;
    }

    @Override
    protected String kind() {
        return "[D]";
    }

    @Override
    public String toString() {
        return kind() + status() + " " + description + " (by: " + by + ")";
    }

    public String getByRaw() {
        return by;
    }
}
