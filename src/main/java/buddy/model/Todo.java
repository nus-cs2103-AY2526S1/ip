package buddy.model;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        assert !description.isBlank() : "Todo description cannot be blank";
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

}
