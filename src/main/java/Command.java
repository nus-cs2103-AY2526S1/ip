import java.util.ArrayList;

public abstract class Command {
    public enum CommandType {
        LIST, EVENT, TODO, DEADLINE, MARK, UNMARK, DELETE, BYE;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isBye();
}
