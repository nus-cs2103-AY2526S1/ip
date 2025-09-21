package thopz;

/**
 * Stops the chatbot from running
 */

public class ExitCommand extends Command {

    /**
     * Executes the exit command by changing boolean to true
     *
     */

    @Override
    public boolean isDone() {
        return true;
    }

    /**
     * Executes the exit command, doesnt perform anything
     *
     * @param ls   The task list that the event will be added to.
     * @param ui      The UI that handles user interaction.
     * @param storage The storage that saves task data.
     */

    @Override
    public String perform(TaskList ls, Storage storage, Ui ui) {
        return "";
    }

}

