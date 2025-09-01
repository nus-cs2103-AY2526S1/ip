public class Deadline extends Task {
    String by;

    // Deadlines
    Deadline(String name, Boolean completed, String by) {
        super(name, completed);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String print() {
        return "[D] [" + (this.completed ? "X" : " ") + "] " + this.name +
                " (by: " + this.by + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsCsv() {
        return "D," + this.completed.toString() + "," + this.name + "," + this.by;
    }
}
