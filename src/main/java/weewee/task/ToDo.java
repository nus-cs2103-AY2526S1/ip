package weewee.task;

/** Represents a todo task. */
public class ToDo extends Task {

    /**
     * Constructs todo task with the given details.
     *
     * @param taskName Name/description of the todo.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", this.getIsdone(), super.getTaskName());
    }
}
