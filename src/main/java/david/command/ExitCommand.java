package david.command;

import david.storage.Storage;
import david.task.TaskList;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Gets Ui to show goodbye message.
     *
     * @param tasks List of Tasks.
     * @return Text to be displayed.
     * @param storage User's data storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "    I zao liao ah. See you again soon!";
    }

    /**
     * Makes chatbot end.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
