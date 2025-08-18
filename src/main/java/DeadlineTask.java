public class DeadlineTask extends Task {
    private final String deadline;

    public DeadlineTask(String name, String deadline) {
        super(name, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (by: %s) (id:%d)", type.getSymbol(), status.getSymbol(), name, deadline, id);
    }
}