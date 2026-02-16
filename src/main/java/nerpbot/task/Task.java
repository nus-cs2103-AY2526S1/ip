package nerpbot.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task object from a saved string format.
     *
     * @param data The saved string representation of the task.
     * @return The corresponding Task object.
     */
    public static Task fromSaveFormat(String data) {
        String[] parts = data.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "E":
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                return null;
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Converts the task to a string format suitable for saving to a file.
     *
     * @return The string representation of the task for saving.
     */
    public String saveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
