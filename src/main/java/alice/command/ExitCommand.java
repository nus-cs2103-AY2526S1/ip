package alice.command;

import alice.Storage;
import alice.TaskList;
import alice.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
