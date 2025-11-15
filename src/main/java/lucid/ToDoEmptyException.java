package lucid;

/**
 * Exception resulting from incorrect usage of the todo command
 */
public class ToDoEmptyException extends LucidException {
    public ToDoEmptyException() {
        super("You can't do that! A todo can't have an empty description!");
    }
}
