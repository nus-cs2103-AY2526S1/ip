package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an unknown command (i.e. an invalid input).
 * Will result in an error response.
 */
public class UnknownCommand extends Command {
    /**
     * Constructs a {@link UnknownCommand} with the relevant user input.
     *
     * @param input the raw user input that triggered the command
     */
    public UnknownCommand(String input) {
        super(input);
    }

    /**
     * Executes the unknown command: displays an error message via the provided
     * {@link Ui}.
     *
     * @param tasklist the {@link TaskList} where tasks are stored
     * @param ui       the {@link Ui} that the user interacts with
     * @param storage  the {@link Storage} that retrieves and updates the save file
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.showError("I'm not sure what command you're trying to run. Try again?");
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
