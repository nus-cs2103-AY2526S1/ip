package briar.command;

import briar.exception.BriarException;
import briar.task.TaskList;
import briar.ui.Storage;
import briar.ui.Ui;
/**
 * Represents a command that user can give.
 */
public abstract class Command {

    public Command() {

    }

    /**
     * Executes a command defined by subclasses.
     * @param tasks Task list of the chatbot.
     * @param ui Ui used by the chatbot to display.
     * @param storage Storage used to save and load the task list.
     * @return Response from Briar after completing the command
     * @throws BriarException If the command cannot be executed properly.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BriarException;

    /**
     * Returns whether the command is an ExitCommand.
     */
    public boolean isExit() {
        return false;
    }
}
