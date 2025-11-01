package nina.task;

import java.io.Serializable;

/**
 * Represents a generic task with a description and completion status.
 */
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    private static final long serialVersionUID = 10L;

    /**
     * Constructs a Task object with the specified description.
     * The task is initially marked as not done.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return X if done, blank space if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the base prefix for serialization.
     * Used by subclasses when generating save lines.
     *
     * @param type the short type identifier: T/D/E
     * @return a string prefix containing the type, done status, and description
     */
    protected String basePrefix(String type) {
        return type + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Converts this task into a save line string for storage.
     *
     * @return the save line string representation of the task
     */
    public abstract String toSaveableLine();

    /**
     * Check if the description of a task contains a certain keyword
     * @param keyword the word to be checked for
     * @return a boolean value indicating whether the two tasks match in keyword
     */
    public boolean match(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Creates a Task object from a saved line string.
     *
     * @param line the saved line string
     * @return a Task object parsed from the saved line
     * @throws IllegalArgumentException if the line is not well formatted or task type is unknown
     */
    public static Task fromSaveLine(String line) {
        String[] p = line.split("\\|");
        for (int i = 0; i < p.length; i++) {
            p[i] = p[i].trim();
        }

        if (p.length < 3) {
            throw new IllegalArgumentException("Bad line: " + line);
        }

        String type = p[0];
        boolean done = "1".equals(p[1]);
        String des = p[2];

        Task t;
        switch (type) {
        case "T":
            if (p.length != 3) {
                throw new IllegalArgumentException("Todo needs 3 fields");
            }
            t = new TodoTask(des);
            break;
        case "D":
            if (p.length != 4) {
                throw new IllegalArgumentException("Deadline needs 4 fields");
            }
            t = new DeadlineTask(des, p[3]);
            break;
        case "E":
            if (p.length != 5) {
                throw new IllegalArgumentException("Event needs 5 fields");
            }
            t = new EventTask(des, p[3], p[4]);
            break;
        default:
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        if (done) {
            t.markDone();
        }
        return t;
    }

    /**
     * Normalizes a string by removing trailing spaces and convert all letters to lowercase
     * @param str string input to be normalized
     * @return normalized String str with all letters converted to lowercase and with trailing spaces trimmed.
     */
    public String normalizeKey(String str) {
        String s = str == null ? "" : str.trim().toLowerCase();
        return s;
    }

    /**
     * Generates a genetic deDupKey for a task
     * @return a key with task type and task description which are to be used for de-duplication
     */
    public String deDupKey() {
        return this.getClass().getName() + "|" + normalizeKey(this.description);
    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
