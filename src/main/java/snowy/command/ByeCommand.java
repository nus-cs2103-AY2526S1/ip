package snowy.command;

import snowy.TaskList;
import snowy.Storage;

public class ByeCommand extends Command{
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye! Hope to see you again :D";
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
