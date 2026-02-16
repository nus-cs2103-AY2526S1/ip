package chatbot.task;

import chatbot.exception.BotException;
import chatbot.exception.InvalidArgumentException;

/**
 * Task represents an abstract task with a name and a completion status.
 * A Task has a description and can be marked as complete or incomplete.
 * Subclasses of Task must override the toSaveFormat() method.
 */
public abstract class Task {
    private boolean isComplete = false;
    private final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Marks task as complete.
     *
     * @throws BotException If task is already complete
     */
    public void markTaskComplete() throws BotException {
        if (this.isComplete) {
            throw new InvalidArgumentException("You already marked this as complete...\n");
        }

        this.isComplete = true;
    }

    /**
     * Unmarks task as complete.
     *
     * @throws BotException If task is already not marked as complete
     */
    public void unmarkTaskComplete() throws BotException {
        if (!this.isComplete) {
            throw new InvalidArgumentException("This task isn't even complete...\n");
        }

        this.isComplete = false;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    /**
     * Returns a String representation of the complete status of a Task.
     *
     * @return A string representing completion status of task.
     */
    public String stringFormatCompleteStatus() {
        return this.isComplete ? "[X] " : "[ ] ";
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns true if a keyword exists in a task description.
     *
     * @param keyword What the user is searching for.
     * @return True if keyword exists else false.
     */
    public abstract boolean existsInTaskDescription(String keyword);

    public abstract String toSaveFormat();

    @Override
    public String toString() {
        return stringFormatCompleteStatus() + getTaskName();
    }
}
