package hachiware;
//AI helped to generate javadoc comments

/**
 * Represents a command that terminates the program.
 * <p>
 * The {@code ByeCommand} does not modify the task list or storage.
 * It simply returns a farewell message and signals that the program should exit.
 * </p>
 */
public class ByeCommand extends Command {
    /** The farewell message displayed when the program exits. */
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Executes the bye command by returning a farewell message.
     * <p>
     * This command does not modify the task list or save to storage.
     * </p>
     *
     * @param tasks   the current task list (unused in this command)
     * @param ui      the user interface (unused in this command)
     * @param storage the storage handler (unused in this command)
     * @return the farewell message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, StoreFile storage) {

        return BYE_MESSAGE;
    }

    /**
     * Indicates whether this command will cause the program to exit.
     *
     * @return {@code true}, since this command ends the program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
