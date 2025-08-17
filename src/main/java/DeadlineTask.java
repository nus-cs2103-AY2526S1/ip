public class DeadlineTask extends Task {
    private final String deadline;

    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String getTypeSymbol() { return "D"; }

    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        return String.format("[%s]%s %s (by: %s) (id:%d)", getTypeSymbol(), status, name, deadline, id);
    }
}