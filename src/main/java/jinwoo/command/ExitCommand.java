package jinwoo.command;

import jinwoo.storage.Storage;
import jinwoo.tasks.TaskList;
import jinwoo.util.Ui;

/**
 * Bye command.
 */
public class ExitCommand extends Command {

    /**
     * Exit() method to terminate the program.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        ui.printExit();
        System.exit(0); // Terminates before printing exit message
    }
}
