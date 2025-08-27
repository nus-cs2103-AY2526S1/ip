package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.TaskList;
import beebong.ui.UI;

public class ListAllTasksCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws BBongException {
        // If there are no Tasks to list
        if (taskList.length() == 0) {
            ui.showMessage("Bong! I searched high and low… still nothing to show right now.");
            return;
        }
        ui.printBorder();
        System.out.println("Bing! Here’s what’s buzzing in your list, courtesy of B. Bong:");
        System.out.print(taskList);
        ui.printBorder();
    }
}
