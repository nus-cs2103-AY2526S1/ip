package focus;

/**
 * Exits the application.
 */
public class ByeCommand extends FocusCommand {

    /**
     * Returns true to indicate the application should exit after execution.
     *
     * @return Always true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command by showing a farewell message.
     *
     * @param tasks Task list (unused).
     */
    @Override
    public void execute(TaskList tasks) {
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

}
