package sunoo.command;

import sunoo.task.TaskList;
import sunoo.ui.Ui;

/**
 * Represents an executable command that instructs the chatbot to exit and say goodbye.
 */
public class ByeCommand extends Command {

    /**
     * {@inheritDoc}
     * <p>Particularly, this method instruct Ui to show a Goodbye message.</p>
     */
    @Override
    public String execute(TaskList tasks) {
        return Ui.getExitMessage();
    }

    /**
     * {@inheritDoc}
     *
     * @return true.
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
