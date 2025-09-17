package command;

import javafx.util.Pair;
import misc.PepeException;
import state.Storage;
import state.TaskList;
import state.Ui;

/**
 * Command that sorts the current TaskList.
 */
public class SortCommand implements Command {
    private static final String SORTED = "Pepe has sorted your tasks, my fellow Pepe!";

    @Override
    public Pair<String, Boolean> execute(Ui ui, Storage storage, TaskList tasks) throws PepeException {
        tasks.sort();
        return new Pair<>(SORTED, true);
    }
}
