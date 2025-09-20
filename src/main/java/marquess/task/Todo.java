package marquess.task;

import marquess.exception.MarquessException;

/**
 * A simple task with only a description.
 */
public class Todo extends Task {

    public Todo(String description) throws MarquessException {
        super(description);
    }

    public Todo(boolean isDone, String description) throws MarquessException {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String exportTask() {
        return "T," + super.exportTask();
    }
}
