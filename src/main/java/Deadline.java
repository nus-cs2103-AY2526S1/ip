public class Deadline extends Task{
    private final String dueBy;

    public Deadline(String name, String dueBy) {
        super(name);
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", "D", super.toString(), this.dueBy);
    }
}

