package jimbot.exception;

/**
 * Represents an InvalidToDo exception that occurs when an empty ToDo command is given.
 */
public class InvalidToDoException extends JimbotException {
    /**
     * Constructs an InvalidToDo exception with the specified error message.
     */
    public InvalidToDoException() {
        super("""
                   Your input is empty!
                    ┻━┻︵╰(‵□′)╯︵┻━┻
                   """);
    }
}
