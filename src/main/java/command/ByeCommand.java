package command;

import javafx.util.Pair;
import misc.PepeException;
import state.Storage;
import state.TaskList;
import state.Ui;

/**
 * Command that terminates the current Pepe session.
 */
public class ByeCommand implements Command {
    private static final String byeResponse = "Bye. Hope to see you again soon!";

    @Override
    public Pair<String, Boolean> execute(Ui ui, Storage storage, TaskList tasks) throws PepeException {
        return new Pair<>(ui.formatMessage(byeResponse), false);
    }
}
