package bobbywasabi.tasks;

public class ToDo extends Task {
    public ToDo(String description, boolean isMarked) {
        super(description, isMarked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getData() {
        return String.format("T|%s|%s",
                super.getDescription(), super.checked());
    }
}
