package nami.command;
import nami.ui.Ui;
import nami.storage.Storage;
import nami.task.TaskList;
import nami.exception.DukeException;
public class ExitCommand extends Command {

    /**
     * Ends the app
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showGoodbye();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
