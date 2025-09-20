package command;


/**
 * Represents an invalid command entered by the user.
 *
 * <p>When executed, this command does not modify the task list
 * and simply returns an error message to be displayed to the user.</p>
 */
public class IncorrectCommand extends Command {
    public final String feedbackToUser;

    /**
     * Constructs an {@code IncorrectCommand} with the specified feedback message.
     *
     * @param feedbackToUser the message to be shown to the user
     */
    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Returns the feedback message indicating why the command was invalid.
     *
     * @return the error message to be displayed to the user
     */
    @Override
    public String respond() {
        return this.feedbackToUser;
    }
}