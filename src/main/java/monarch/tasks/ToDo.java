package monarch.tasks;

/**
 * Represents a toDo task, consisting of only a description.
 */
public class ToDo extends Task {
    /**
     * Constructor for a toDo task
     *
     * @param description A description for the task.
     */
    public ToDo(String description) {
        super(description, "T");
    }

    @Override
    public String[] getInfo() {
        return new String[] {};
    }

    @Override
    public String toString() {
        return (String.format("[%s][%s] %s", "T",
                super.getStatusIcon(),
                super.getDescription()
        ));
    }

    /**
     * Compares based on type of task, alphabetically
     * @param other the object to be compared.
     * @return 0, 1 or -1.
     */
    @Override
    public int compareTo(Task other) {
        if (other instanceof Event || other instanceof Deadline) {
            return 1;
        }
        if (other instanceof ToDo) {
            return this.getDescription().compareTo(other.getDescription());
        }
        return 1;
    }
}
