package task;

/**
 * ToDos: tasks without any date/time attached to it e.g., visit new theme park
 */
public class ToDos extends Task {
    private static final String commandCode = "T";

    /**
     * Initialises fields of class and parent class instances.
     */
    public ToDos(String description) {
        super(description, description, commandCode);
    }

    @Override
    public String toString() {
        return "[" + commandCode + "]" + super.toString();
    }

    /**
     * Creates a ToDos Task
     * @param taskInfo Description of task
     * @return ToDos Task
     */
    public static ToDos createTask(String taskInfo) {
        String removedWhitespace = taskInfo.trim();
        return new ToDos(removedWhitespace);
    }
}
