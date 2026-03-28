package mel.tasks;

import mel.exceptions.MelException;

/**
 * Represents the task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this.isDone = false;
    }

    /**
     * Constructor for task
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");

    }

    /**
     * Set a new description for the task
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;

    }

    /**
     * Returns the output when a task is marked as done.
     *
     * @return output when task is marked done
     */
    public String markAsDone() {
        this.isDone = true;
        String output = String.format("Nice! I've marked this task as done:\n  %s",
                this.toString());
        return output;

    }

    /**
     * Returns the output when a task is unmarked.
     *
     * @return output when task is unmarked
     */
    public String undo() {
        this.isDone = false;
        String output = String.format("OK, I've marked this task as not done yet:\n  %s",
                this.toString());
        return output;
    }

    /**
     * Returns the task string
     *
     * @return a string of the task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the task converted into a string to be saved into a text file
     * @return a string to be saved in text file
     */
    public String toSaveString() {
        String done = isDone ? "1" : "0";
        return " | " + done + " | " + description;

    }

    /**
     * Returns the task converted from the string in the text file
     * @param savedString
     * @return the task convert from the saved string
     * @throws MelException
     */
    public static Task fromSavedString(String savedString) throws MelException {
        String[] saved = savedString.split(" | ");
        if (saved.length < 1) {
            throw new MelException("Saved line is empty!");

        } else if (saved[0].equals("T")) {
            return Todo.fromSavedString(savedString);

        } else if (saved[0].equals("D")) {
            return Deadline.fromSavedString(savedString);

        } else if (saved[0].equals("E")) {
            return Event.fromSavedString(savedString);

        } else {
            throw new MelException("Error reading saved data!");

        }
    }


}
