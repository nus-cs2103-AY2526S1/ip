package burgerburglar;

/**
 * Represents the "BURGER" bonus command.
 * When executed, it prints the special "BURGER" message.
 */
public class BurgerCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null in execute";
        assert ui != null : "Ui cannot be null in execute";
        assert storage != null : "Storage cannot be null in execute";

        return "BURGER IS BURGER, AND YOU ARE THE FRIES.";
    }
}
