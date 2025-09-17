package haru.exception;

/**
 * Exception thrown when a task ID is invalid.
 */
public class InvalidTaskIdException extends HaruException {

    /**
     * Constructs an InvalidTaskIdException with the list length.
     *
     * @param length the number of tasks in the list
     */
    public InvalidTaskIdException(int length) {
        super((length == 0)
                ? "Eh?! The list is currently empty!"
                : String.format("Eh?! The task ID must be a number between 1 and %d!", length)
        );
    }
}
