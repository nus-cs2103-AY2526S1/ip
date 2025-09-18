package chiikawa.task;

/**
 * Class for ToDos which do not have any date/time attached to it.
 */
public class ToDoTask extends Task {
    /**
     * Constructor for creating new to do tasks by user.
     *
     * @param name Name of the task.
     */
    public ToDoTask(String name) {
        super(name);
    }

    /**
     * Constructor for creating new to do tasks as they are loaded from save file.
     *
     * @param name Name of the task.
     * @param isCompleted Status of the task.
     */
    public ToDoTask(String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    @Override
    public String toString() {
        return "T " + super.toString();
    }
}
