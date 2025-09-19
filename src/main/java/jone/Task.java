package jone;

/**
 * Represents a generic task with a description and completion status.
 * This is an abstract base class for specific task types such as
 * {@code Todo}, {@code Deadline}, and {@code Event}.
 */
public abstract class Task {
    private static final String SEPARATOR = " \\| ";
    protected static final String STATUS_TICK = "X";
    protected static final String STATUS_CROSS = " ";

    private final String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * Used when creating a brand-new task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the given description and completion status.
     * Used when loading tasks that were previously stored.
     *
     * @param description The description of the task.
     * @param done Whether the task has been completed.
     */
    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns whether this task is completed.
     *
     * @return {@code true} if task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of this task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status symbol of this task.
     *
     * @return "X" if done, otherwise a blank space.
     */
    protected String status() {
        return isDone ? STATUS_TICK : STATUS_CROSS;
    }

    /**
     * Returns the string representation of this task.
     *
     * @return A formatted string showing task status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", status(), description);
    }

    /**
     * Converts this task into a string suitable for saving to file.
     *
     * @return Save format string.
     */
    public abstract String toSaveFormat();

    /**
     * Creates a {@code Task} object from a saved line in storage format.
     *
     * @param line A line from the save file.
     * @return A corresponding {@code Task} object.
     * @throws JoneException If the line cannot be parsed properly.
     */
    public static Task fromSaveFormat(String line) throws JoneException {
        String[] parts = line.split(SEPARATOR);
        String type = parts[0];
        boolean done = parts[1].equals("1");
        String desc = parts[2];

        switch (type) {
            case "T":
                return new Todo(desc, done);
            case "D":
                return new Deadline(desc, parts[3], done);
            case "E":
                return new Event(desc, parts[3], parts[4], done);
            default:
                throw new JoneException("Invalid task type in save file.");
        }
    }
}
