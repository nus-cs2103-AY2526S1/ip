package cattis.exception;

/**
 * Exception thrown when the user enters an invalid or unrecognized command.
 * <p>
 * Provides a default message listing all valid commands supported by the application.
 */
public class CattisInvalidCommandException extends CattisException {

    /**
     * Constructs a new {@code CattisInvalidCommandException} with a default message
     * listing all supported commands in the application.
     */
    public CattisInvalidCommandException() {
        super(String.join("\n",
                "⚠ Invalid command. Here is a list of valid commands:",
                "",
                "calendar          → open calendar view",
                "view [date]       → view all tasks on a specific date",
                "list              → list all tasks",
                "todo [task]       → add a todo task",
                "deadline [task] /by [Deadline] → add a task with a deadline",
                "event [task] /from [Start] /to [End] → add an event with a time range",
                "delete [index]    → remove a task",
                "mark [index]      → mark a task as done",
                "unmark [index]    → mark a task as not done",
                "find [keyword]    → search tasks",
                "bye               → exit the application"
        ));
    }
}
