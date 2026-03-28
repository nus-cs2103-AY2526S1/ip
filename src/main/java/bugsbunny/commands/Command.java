package bugsbunny.commands;

import java.io.IOException;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.TaskList;

/**
 * Abstract parent class for all other commands supported by the chatbot.
 * Subclasses implement {@link #execute} and may override {@link #isExit}.
 */
public abstract class Command {
    protected String args;

    /**
     * Creates an object of Command type.
     *
     * @param args The args which are the user's inputs.
     */
    public Command(String args) {
        this.args = args;
    }
    /**
     * Executes this command against the given task list and storage.
     *
     * @param tasks The collection of tasks.
     * @param ui The UI used for printing messages to the chatbot.
     * @param storage The storage used for saving and loading from the hard disk.
     * @return Output string of the chatbot's response
     * @throws BugsBunnyException if command validation fails.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException;

    /**
     * Checks if the command is an Exit Command.
     *
     * @return {@code true} if the command should terminate the app.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns an empty string if there is no error saving to the hard disk.
     * Else a new line + error message will be returned.
     *
     * @param tasks The collection of tasks.
     * @param ui The UI used for printing messages to the chatbot.
     * @param storage The storage used for saving and loading from the hard disk.
     * @return Output string to append to the chatbot response.
     */
    public String saveOrAppendError(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
            return "";
        } catch (IOException e) {
            return "\n" + ui.showSavingError();
        }
    }

    /**
     * Does a basic check if the input string by the user is valid.
     *
     * @param args Input string by the user
     * @param usageError Usage guide string to tell the user how to use this command.
     * @throws BugsBunnyException If the input string is invalid
     */
    public void ensureValidArgs(String args, String usageError) throws BugsBunnyException {
        if (args == null || args.isBlank()) {
            throw new BugsBunnyException(usageError);
        }
    }
}
