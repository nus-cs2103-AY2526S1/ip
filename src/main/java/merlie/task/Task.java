package merlie.task;

import merlie.exception.MerlieException;
import merlie.parser.ParsedDate;

/**
 * Abstract class representing a generic task. Contains common attributes and
 * methods shared by all tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks whether this task has the same description as another task.
     *
     * @param other Task to compare with.
     * @return True if have same description, false otherwise.
     */
    public boolean isSameDescription(Task other) {
        return this.description.equalsIgnoreCase(other.description);
    }

    /**
     * Checks whether this task is a duplicate of another task. Considered duplicate
     * if same type and exact fields OR if different type and same description
     *
     * @param other Task to compare with.
     * @return true If duplicate, false otherwise.
     */
    public boolean isDuplicate(Task other) {
        return this.isSameDescription(other);
    }

    /**
     * Updates this task if both are of the same type.
     *
     * @param other Task to update from
     * @return True if updated, false otherwise
     */
    public abstract boolean isUpdateSuccessful(Task other);

    /**
     * Processes a line from list file into a Task object.
     *
     * @param line the string line from the list file
     * @return the created Task object
     * @throws MerlieException if the line is malformed or cannot be parsed
     */
    public static Task process(String line) throws MerlieException {
        try {
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String task = parts[2].trim();

            switch (type) {
            case "T":
                Task t = new Todo(task);
                t.isDone = isDone;
                return t;
            case "D":
                ParsedDate by = ParsedDate.parseDate(parts[3].trim());
                Task d = new Deadline(task, by.getDate(), by.getHasTime());
                d.isDone = isDone;
                return d;
            case "E":
                String[] timeline = parts[3].split(" /to ");
                ParsedDate from = ParsedDate.parseDate(timeline[0].trim());
                ParsedDate to = ParsedDate.parseDate(timeline[1].trim());
                Task e = new Event(task, from.getDate(), from.getHasTime(), to.getDate(), to.getHasTime());
                e.isDone = isDone;
                return e;
            default:
                throw new MerlieException("incorrect/missing task type");
            }
        } catch (Exception e) {
            throw new MerlieException(e.getMessage());
        }
    }

    /**
     * Returns the formatted string for saving to file.
     *
     * @return formatted string
     */
    public abstract String format();

    /**
     * Returns the status icon of this task.
     *
     * @return "X" if done, " " otherwise.
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
