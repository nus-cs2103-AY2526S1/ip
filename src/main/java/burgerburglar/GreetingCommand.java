package burgerburglar;

/**
 * Represents a command that greets the user.
 * <p>
 * When executed, this command displays a friendly greeting message.
 */
public class GreetingCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null in GreetingCommand";
        assert ui != null : "Ui cannot be null in GreetingCommand";
        assert storage != null : "Storage cannot be null in GreetingCommand";

        return "GOODDAY, GOODBURGER.";
    }
}
