package jimbot.exception;

/**
 * Represents a NoSuchTask exception that occurs when an invalid task index is given.
 */
public class NoSuchTaskException extends JimbotException {
    /**
     * Constructs a NoSuchTask exception with the specified error message.
     */
    public NoSuchTaskException() {
        super("""
                  (╥﹏╥ )  I can't find that task!  ( ╥﹏╥)
                """);
    }
}
