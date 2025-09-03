package evansbot.Exceptions;

/**
 * Represents an InvalidTaskIndex exception that occurs when an invalid Task Index is given.
 */
public class InvalidTaskIndexException extends EvansBotException {
    private final int maxIndex;

    /**
     * Constructs an Invalid TaskIndex Exception with the specified error message.
     *
     * @param max maximum Task Index value that can be accepted.
     */
    public InvalidTaskIndexException(int max) {
        super(max == 0
                ? "There are currently no tasks inside the task list. Please add a task first!"
                : "There are only " + max + " tasks in the list! Please choose a number between 1 to " + max);
        this.maxIndex = max;
    }

    public int getMaxIndex() {
        return maxIndex;
    }
}
