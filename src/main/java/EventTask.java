public class EventTask extends Task {
    private final String start;
    private final String end;

    public EventTask(String name, String start, String end) {
        super(name, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (from: %s to: %s) (id:%d)", type.getSymbol(), status.getSymbol(), name, start, end, id);
    }
}