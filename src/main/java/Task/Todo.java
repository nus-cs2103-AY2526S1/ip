package Task;

/**
 * To-do task with description.
 */
public class Todo extends TaskItem {

    /**
     * Initialises to-do task.
     *
     * @param name Description of task.
     * @param isDone Completion status of task.
     */
    public Todo(String name, boolean isDone) {
        super(name);
        if (isDone) {
            super.markDone();
        }
    }

    /**
     * Returns tag to be printed in list.
     */
    @Override
    public String typeTag() {
        return "[T]";
    }

    /**
     * Returns string to save task in storage.
     */
    @Override
    public String toSaveString() {
        assert name.indexOf('|') < 0 : "Save: name must not contain '|'";
        return "T|" + isDone + "|" + name;
    }
}
