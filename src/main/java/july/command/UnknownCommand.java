package july.command;

import java.util.ArrayList;

import july.data.Storage;
import july.tasks.Task;
import july.ui.Ui;

/**
 * Command to handle unrecognized or invalid user input.
 * This command is used as a fallback when the user enters a command
 * that is not recognized by the command parser, displaying an appropriate
 * error message through the user interface.
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {
        super();
    }
    /**
     * Executes the unknown command to display an error message for unrecognized input.
     * This method calls the user interface to show a sorry/error message when
     * the user enters an invalid or unrecognized command.
     *
     * @param tasks the ArrayList of tasks (not used in this command)
     * @param ui the user interface component for displaying the error message
     * @param storage the storage component for data persistence (not used in this command)
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        super.addResponses(ui.sorry());
    }
}
