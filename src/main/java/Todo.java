public class Todo extends Task {

    public Todo(String description) { 
        super(description); 
    }
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    protected String kind() { 
        return "[T]"; 
    }
    @Override
    public String toSaveFormat() {
        return String.format("T | %d | %s", isDone() ? 1 : 0, description);
    }
}