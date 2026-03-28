package ember.task;

import ember.exception.MissingInformationException;

/**
 * Represents a task.
 * This task includes a name.
 */
public class TodoTask extends Task {
    public TodoTask(String name) throws MissingInformationException {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String description() {
        return toString();
    }

    @Override
    public String parseFormat() {
        return toString();
    }
}
