package tension.task;

import java.util.ArrayList;

/**
 * creates Todo task for tasks with description only
 */
public class Todo extends Task {

    /**
     * returns Todo object
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * returns Todo object with tags
     */
    public Todo(String description, ArrayList<String> tags) {
        super(description);
        this.tags = tags;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String makeStoreString() {
        return "T|" + super.isDone + "|" + description + "|" + super.tags;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + getTagsAsString();
    }
}
