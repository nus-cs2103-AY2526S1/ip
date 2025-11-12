package seedu.haru;

/**
 * Represents a task in the Haru chatbot.
 * A task has a name, completion status and type.
 */
public class Task {
    protected boolean isDone;
    protected String name;
    protected Type type;

    /**
     * Creates a new Task with the given description and type.
     *
     * @param name name of the task
     * @param type type of the task
     */
    public Task(String name, Type type) {
        assert name != null && !name.isBlank() : "Deadline name must not be null or blank";
        assert type != null : "Type must not be null";

        this.name = name;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks this task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }

    /**
     * Converts the task to a format suitable for saving in storage.
     *
     * @return a string representation of the task
     */
    public String toFileString() {
        return (isDone ? 1 : 0) + " | " + name;
    }

    /**
     * Creates a Task object from a string read from a file.
     *
     * @param line a string in the format "Type | isDone | description | [extra fields]"
     *             where Type is "T" (ToDo), "D" (Deadline), or "E" (Event)
     * @return the corresponding Task, or null if the type is invalid
     */
    public static Task fromFileString(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T" -> task = new ToDo(description, Type.TODO);
        case "D" -> task = new Deadline(description, parts[3], Type.DEADLINE);
        case "E" -> task = new Event(description, parts[3], parts[4], Type.EVENT);
        default -> task = null;
        }

        if (task != null && isDone) task.mark();
        return task;
    }

}
