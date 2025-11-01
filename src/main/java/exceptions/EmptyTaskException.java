package exceptions;

import tasks.Task;

/**
 * Exception for when the task description for a task is empty
 */
public class EmptyTaskException extends UserInputException {
    private final String message;

    public EmptyTaskException(Task t) {
        this.message = String.format("%s task cannot be EMPTY RAHHHHHHH!!!", t.getTaskType());
    }

    @Override
    public String toString() {
        return this.message;
    }
}
