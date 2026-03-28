package tasks;

import exceptions.DukeyException;

/** Represents ToDo task */
public class ToDo extends Task {

    /**
     * Initialises a Todo task.
     *
     * @param text the description of task.
     * @param isMarked whether the task is marked as completed.
     * @throws DukeyException if no task description provided.
     */
    public ToDo(String text, boolean isMarked) throws DukeyException {
        super();
        this.type = "T";
        this.isMarked = isMarked;

        if (text.isEmpty()) {
            throw new DukeyException("Description Missing!"); // Handle missing description
        } else {
            this.text = text;
        }
    }
}
