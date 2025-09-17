package command;

import javafx.util.Pair;
import misc.PepeException;
import state.Storage;
import state.TaskList;
import state.Ui;

/**
 * Command to display the current task list stored by Pepe application.
 */
public class DisplayListCommand implements Command {
    @Override
    public Pair<String, Boolean> execute(Ui ui, Storage storage, TaskList tasks) throws PepeException {
        return new Pair<>(ui.displayTaskList(tasks), true);
    }
}
