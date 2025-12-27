package ip.commands;

import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to exit application
 */
public class ExitCommand implements Command {
    private static final String KEYWORD = "bye";

    /**
     * Causes the application to exit
     *
     * @throws UnknownInputException if input contains other words
     * @inheritDoc
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws UnknownInputException {
        if (!input.equals(KEYWORD)) {
            throw new UnknownInputException("Just enter 'bye' by itself");
        }

        return ui.showExitMsg();
    }

    /**
     * Returns true to identify that it is an exit command
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
