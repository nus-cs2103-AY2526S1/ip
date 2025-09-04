package chash.command;

import chash.storage.ChashDb;
import chash.task.TaskList;
import chash.ui.ChashUi;

public class ExitCommand extends Command {
    public ExitCommand() {}

    @Override
    public void execute(TaskList tasks, ChashUi ui, ChashDb db) {
        ui.printMsg("Bye. Hope to see you again soon!");
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
