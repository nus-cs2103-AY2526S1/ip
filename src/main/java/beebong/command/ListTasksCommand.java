package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.TaskList;
import beebong.ui.UI;

public abstract class ListTasksCommand extends Command {
    protected abstract TaskList createTaskList(TaskList taskList);

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws BBongException {
        String emptyListMessage = "Bong! I searched high and low… still nothing to show right now.";

        // If there are no Tasks to list
        if (taskList.length() == 0) {
            ui.showMessage(emptyListMessage);
            return;
        }

        TaskList updatedList = createTaskList(taskList);
        // If there are no Tasks to list
        if (updatedList.length() == 0) {
            ui.showMessage(emptyListMessage);
            return;
        }

        ui.printBorder();
        System.out.println("Bing! Found these tasks for you:");
        System.out.println(updatedList.toString().trim());
        ui.printBorder();
    }
}
