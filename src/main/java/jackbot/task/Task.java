package jackbot.task;

import jackbot.JackbotException;

/** Abstract class for all Jackbot tasks (Todo, Deadline, Event). */
public abstract class Task {

    /** Human-readable description. */
    private final String description;

    /** Completion flag. */
    private boolean isDone;

    /**
     * Creates a new task with the given description (initially not done).
     *
     * @param description human-readable description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the checkbox prefix used in UI lists.
     *
     * @return {@code "[X]"} if done, else {@code "[ ]"}
     */
    public String checkbox() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns whether this task is marked done.
     *
     * @return {@code true} if done, else {@code false}
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the human-readable description of this task.
     *
     * @return the description text
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return checkbox() + " " + description;
    }

    /**
     * Serializes this task into a single-line record compatible with
     * {@link #deserialize(String)}.
     *
     * @return wire-format string (no newline)
     */
    public abstract String serialize();

    /**
     * Deserializes a task from a single pipe-delimited line.
     *
     * @param data line to parse
     * @return a concrete {@link Task}
     * @throws JackbotException if the line is malformed or the type is unknown
     */
    public static Task deserialize(String data) throws JackbotException {
        if (data == null) {
            throw new JackbotException("Null task record");
        }

        String[] parts = data.split("\\|", -1);
        if (parts.length < 3) {
            throw new JackbotException("Malformed task record: too few fields");
        }

        String type = parts[0].trim();
        String doneStr = parts[1].trim();

        final boolean isDoneFlag;
        if ("1".equals(doneStr)) {
            isDoneFlag = true;
        } else if ("0".equals(doneStr)) {
            isDoneFlag = false;
        } else {
            throw new JackbotException("Malformed task record: done flag must be 0 or 1");
        }

        final Task task;
        switch (type) {

        case "T": {
            // T|d|desc
            String desc = parts[2].trim();
            task = new Todo(desc);
            break;
        }
        case "D": {
            // D|d|desc|due
            if (parts.length < 4) {
                throw new JackbotException("Malformed Deadline record: missing due");
            }
            String desc = parts[2].trim();
            String due = parts[3].trim();
            task = new Deadline(desc, due);
            break;
        }
        case "E": {
            // E|d|desc|from|to
            if (parts.length < 5) {
                throw new JackbotException("Malformed Event record: missing time range");
            }
            String desc = parts[2].trim();
            String from = parts[3].trim();
            String to = parts[4].trim();
            task = new Event(desc, from, to);
            break;
        }
        default: {
            throw new JackbotException("Unknown task type: " + type);
        }

        }

        if (isDoneFlag) {
            task.mark();
        }

        return task;
    }
}
