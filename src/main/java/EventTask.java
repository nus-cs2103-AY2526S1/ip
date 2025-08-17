public class EventTask extends Task {
    private final String start;
    private final String end;

    public EventTask(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTypeSymbol() { return "E"; }

    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        return String.format("[%s]%s %s (from: %s to: %s) (id:%d)", getTypeSymbol(), status, name, start, end, id);
    }
}