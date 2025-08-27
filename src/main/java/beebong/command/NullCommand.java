package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.TaskList;
import beebong.ui.UI;

public class NullCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws BBongException {
        ui.showErrorMessage("Something went boom in B. Bong’s circuits.");
    }
}
