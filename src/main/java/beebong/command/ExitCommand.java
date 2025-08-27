package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.TaskList;
import beebong.ui.UI;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws BBongException {
        // Try to save tasks to file before exiting
        taskList.writeTasksToFile(storage);
        ui.showMessage("Bing Bing! Tasks saved successfully!");
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
