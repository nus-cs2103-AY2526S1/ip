package burgerburglar;

/**
 * Represents the exit command which terminates the BurgerBurglar program.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null in execute";
        assert ui != null : "Ui cannot be null in execute";
        assert storage != null : "Storage cannot be null in execute";

        return "GOODBYE, GOODBURGER.";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
