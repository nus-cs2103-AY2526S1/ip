// Deadline Task

public class Deadline extends Task {

    private String by;

    public Deadline(String description) {
        super(description.split("\\/by ", 2)[0]);

        String[] parts = description.split("\\/by ", 2);

        this.by = parts[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
