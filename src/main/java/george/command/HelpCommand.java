package george.command;

import george.exceptions.GeorgeException;
import george.task.TaskManager;

/**
 * Represents a command to display help information about available commands.
 * Shows a concise list of all supported commands and their usage.
 */
public class HelpCommand extends Command {
    /**
     * Constructs a HelpCommand.
     */
    public HelpCommand() {
        // No parameters needed for help command
    }

    @Override
    public String execute(TaskManager manager) throws GeorgeException {
        return getHelpMessage();
    }

    @Override
    public String getCommandWord() {
        return "help";
    }

    /**
     * Returns the help message containing all available commands.
     *
     * @return A formatted string with command information
     */
    private String getHelpMessage() {
        return "Here are the commands I understand:\n"
                + "1. todo <description> - Add a todo task\n"
                + "2. deadline <description> /by <datetime> - Add a deadline task\n"
                + "3. event <description> /from <start> /to <end> - Add an event task\n"
                + "4. list - Show all tasks\n"
                + "5. mark <number> - Mark task as done\n"
                + "6. unmark <number> - Mark task as not done\n"
                + "7. delete <number> - Remove a task\n"
                + "8. find <keyword> - Search for tasks\n"
                + "9. format - Show date/time formats\n"
                + "10. help - Show this message\n"
                + "11. bye - Exit the application";
    }
}
