package tweety.tasks;

import tweety.exceptions.TweetyException;

/**
 * Represents Todo task
 * Extends the Task class to include Todo functionality.
 */
public class ToDo extends Task {

    /**
     * Creates a new Todo task with the specified description and deadline.
     *
     * @param description the task description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public void editField(String field, String newValue) throws TweetyException {
        switch (field) {
            case "description":
                setDescription(newValue);
                break;
            default:
                throw new TweetyException("Aww, hon! For todo tasks, ya can only edit the description!");
        }
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + getDescription();
    }
}
