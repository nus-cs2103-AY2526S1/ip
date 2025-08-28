public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override 
    protected String kind() { 
        return "[D]"; 
    }

    @Override 
    public String toString() {
        return kind() + status() + " " + super.description + " (by: " + by + ")";
    }
    @Override
    public String toSaveFormat() {
        return String.format("D | %d | %s | %s", isDone() ? 1 : 0, description, by);
    }
}