import Exceptions.EvansBotException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EvansBotException;
    public boolean isExit() {
        return false;
    }
}