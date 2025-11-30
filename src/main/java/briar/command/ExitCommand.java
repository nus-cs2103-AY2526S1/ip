package briar.command;

import briar.exception.BriarException;
import briar.task.TaskList;
import briar.ui.Storage;
import briar.ui.Ui;
import javafx.application.Platform;

/**
 * Represents a command that causes the chatbot to exit.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        String response = ui.showExit();
        Platform.exit();
        return response;
    }

    /**
     * Returns a String containing the format of the command and what it does.
     */
    public static String getCommandInformation() {
        return "bye | Exit the program";
    }

    /**
     * Returns whether the command is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the string representation of the command.
     */
    @Override
    public String toString() {
        return "Exit";
    }
}
