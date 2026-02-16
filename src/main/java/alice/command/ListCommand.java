package alice.command;

import alice.Storage;
import alice.TaskList;
import alice.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.getList();
    }
}
