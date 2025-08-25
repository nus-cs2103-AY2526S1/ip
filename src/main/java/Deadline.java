public class Deadline extends Task{
    private final String dueBy;

    public Deadline(String name, String dueBy) {
        super(name);
        this.dueBy = dueBy;
    }

    public Deadline(String name, String dueBy, boolean isDone) {
        this(name, dueBy);
        setDone(isDone);
    }

    @Override
    public String taskToString() {
        return String.format("%s | %b | %s | %s", "D", getDone(), getName(), this.dueBy);
    }


    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", "D", super.toString(), this.dueBy);
    }
}

