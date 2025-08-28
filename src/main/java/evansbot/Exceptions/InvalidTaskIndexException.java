package evansbot.Exceptions;

public class InvalidTaskIndexException extends EvansBotException {
    private int maxIndex;

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
