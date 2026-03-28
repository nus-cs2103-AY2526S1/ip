package jerry.exceptions;

/**
 * An exception that is thrown when a user tries to access a task number
 * that is greater than the task list size.
 * This exception is to prevent invalid operations on task list and provide users with
 * appropriate error messages.
 */
public class ListIndexOutOfBoundException extends JerryException {
    public ListIndexOutOfBoundException(String message) {
        super(message);
    }
}
