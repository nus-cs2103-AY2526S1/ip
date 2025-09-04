package chash.command;

import chash.storage.ChashDb;
import chash.task.Task;
import chash.task.TaskList;
import chash.ui.ChashUi;

public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, ChashUi ui, ChashDb db) {
        if (tasks.size() == 0) {
            ui.printMsg("No tasks in your list!");
            return;
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int counter = 0;
        for (Task task : tasks.getAll()) {
            counter += 1;
            sb.append(String.format("%d. %s\n", counter, task));
        }

        ui.printMsg(sb.toString().stripTrailing());
    }
}
