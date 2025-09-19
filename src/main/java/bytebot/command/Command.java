package bytebot.command;

import bytebot.ByteException;
import bytebot.storage.Storage;
import bytebot.ui.Ui;

/**
 * Base class for all user commands.
 * Concrete subclasses implement execute
 */
public abstract class Command {

    /**
     * Executes the command with access to the UI and storage
     * @param ui UI instance for interacting with the user
     * @param storage Storage instance for reading/writing tasks
     * @return Response message from the command execution
     * @throws ByteException If the command cannot be executed
     */
    public abstract String execute(Ui ui, Storage storage) throws ByteException;

    /**
     * Indicates whether executing this command should exit the app.
     *
     * @return if the app should exit after execution
     */
    public boolean isExit() {
        return false;
    }
}


