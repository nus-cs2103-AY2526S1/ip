package evansbot.command;

import evansbot.Exceptions.EvansBotException;
import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EvansBotException;
    public boolean isExit() {
        return false;
    }
}