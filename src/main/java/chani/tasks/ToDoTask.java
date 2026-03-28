package chani.tasks;

import java.util.List;

/**
 * Represents a ToDo task with a description.
 */
public class ToDoTask extends Task {
    /**
     * Constructor for ToDoTask.
     *
     * @param description The task description; must not be null or empty.
     */
    public ToDoTask(String description) {
        super(description, "T");
        assert description != null && !description.isEmpty()
                : "ToDoTask description cannot be null or empty";
    }

    @Override
    public List<String> toAttributeList() {
        return super.toAttributeList();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString(); // mark done task with X
    }

}
