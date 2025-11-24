package burgerburglar;

/**
 * Represents a command that displays help information to the user.
 * <p>
 * When executed, this command provides guidance on how to use the BurgerBurglar application,
 * including the list of available commands and their usage formats.
 */
public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return getHelpMessage();
    }

    private String getHelpMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("BURGER HELP MENU:\n")
                .append("1. todo <description> - Add a Todo task\n")
                .append("2. deadline <description> /by <yyyy-MM-dd HHmm> - Add a Deadline task\n")
                .append("3. event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm> - Add an Event task\n")
                .append("4. list - Show all tasks\n")
                .append("5. mark <index> - Mark a task as done\n")
                .append("6. unmark <index> - Unmark a task\n")
                .append("7. delete <index> - Delete a task\n")
                .append("8. find <keyword> - Search tasks\n")
                .append("9. help - Show this help menu\n")
                .append("10. bye - Exit the program\n");
        return sb.toString();
    }
}
