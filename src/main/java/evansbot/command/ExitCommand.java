package evansbot.command;

import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}