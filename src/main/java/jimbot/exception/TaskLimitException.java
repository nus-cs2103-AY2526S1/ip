package jimbot.exception;

/**
 * Represents a TaskLimit exception that occurs when the number of task in the task list is more than 100.
 */
public class TaskLimitException extends JimbotException {
    /**
     * Constructs a TaskLimit exception with the specified error message.
     */
    public TaskLimitException() {
        super("""
                !(◎_◎;)  Too many tasks!!!
                """);
    }
}
