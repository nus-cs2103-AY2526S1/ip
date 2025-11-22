package models;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getFormattedString() {
        return "T | " + super.getFormattedString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
