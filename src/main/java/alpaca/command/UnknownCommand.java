package alpaca.command;

import alpaca.Storage;
import alpaca.task.TaskList;
import alpaca.Ui;

/**
 * Command representing an unrecognized or invalid user input.
 * Displays a helpful message prompting the user to enter a valid command
 * and lists the available commands.
 */
public class UnknownCommand extends Command {

    /**
     * Executes the unknown command by showing a message indicating
     * that the input was not understood and displays the list of valid commands.
     *
     * @param tasks   The task list (not used in this command, can be null).
     * @param ui      The UI handler for displaying messages; must not be null.
     * @param storage The storage handler (not used in this command, can be null).
     * @return A message indicating the command was unrecognized along with usage hints.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert ui != null : "Ui cannot be null";

        String msg = "Invalid command! Enter the command help to find out more!";

        ui.showMessage(msg);
        return msg;
    }
}
