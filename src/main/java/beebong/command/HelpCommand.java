package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.TaskList;
import beebong.ui.UI;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws BBongException {
        ui.showCommands();
    }
}
