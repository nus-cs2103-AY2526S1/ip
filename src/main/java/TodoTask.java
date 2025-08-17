public class TodoTask extends Task {
    public TodoTask(String name) { super(name); }

    @Override
    public String getTypeSymbol() { return "T"; }

    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        return String.format("[%s]%s %s (id:%d)", getTypeSymbol(), status, name, id);
    }
}