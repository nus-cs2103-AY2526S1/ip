// Deadline Task

public class Deadline extends Task {

    private String by;

    public Deadline(String description) {
        super(description.split("\\/by ", 2)[0]);

        String[] parts = description.split("\\/by ", 2);

        this.by = parts[1];
    }

    // Used for deserialization
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String serialize() {
        return "D" + "|" + (this.isDone() ? "1" : "0") + "|" + this.getDescription() + "|" + this.by;
    }
}
