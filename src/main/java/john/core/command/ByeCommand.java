package john.core.command;

import john.core.exception.ParseException;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

public class ByeCommand implements Command {
    @Override
    public CommandResult execute(TaskList t, Storage s, Ui ui) {
        return CommandResult.exit("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
