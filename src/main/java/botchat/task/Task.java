package botchat.task;

/**
 * Represents a general task that cannot be instantiated.
 */
public abstract class Task {
    /** Description of the task.*/
    protected String description;

    /** Completion status of task*/
    protected boolean isDone;

    /**
     * Constructs a Task with a description.
     *
     * @param description the String description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Converts completion of task into user interface X.
     *
     * @return string to be displayed in user interface.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of a task for storing.
     * Each subclass of Task implements their own format.
     *
     * @return string representation of a task for storing.
     */
    public abstract String toStorage();

    /**
     * Constructs a Task from a string in storage.
     * @param line the storage string.
     * @return the task as an object.
     */
    public static Task convFromStorage(String line) {
        String[] parts = line.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task = createTask(type, parts, description);
        task.applyDone(task, isDone);

        return task;
    }

    /**
     * Creates a Task instance based on the given task type and its parts.
     *
     * @param type the task type code (e.g., "T", "D", "E", "B")
     * @param parts the split components of the serialized task string; must contain
     *              sufficient elements depending on the task type
     * @param description the task description text
     * @return a new Task object of the specified type
     */
    private static Task createTask(String type, String[] parts, String description) {
        switch (type) {
        case "T":
            return new Todo(description);
        case "D":
            String by = parts[3].trim();
            return new Deadline(description, by);
        case "E":
            String from = parts[3].trim();
            String to = parts[4].trim();
            return new Event(description, from, to);
        case "B":
            String condition = parts[3].trim();
            return new DoAfter(description, condition);
        default:
            throw new IllegalArgumentException("Invalid task type");
        }
    }

    private static void applyDone(Task task, boolean isDone) {
        if (isDone) {
            task.markAsDone();
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
