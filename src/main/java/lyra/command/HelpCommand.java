package lyra.command;

import lyra.task.TaskList;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

/**
 * Command implementation for displaying help information to the user.
 * Shows all available commands and their usage examples.
 */
public class HelpCommand extends Command {
    
    /**
     * Executes the help command by displaying a comprehensive list of available commands
     * and their usage examples to the user.
     *
     * @param tasks the task list (not used in this command)
     * @param ui the user interface for displaying the help information
     * @param storage the storage component (not used in this command)
     * @throws LyraException never thrown by this command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        StringBuilder helpText = new StringBuilder();
        helpText.append("Here are all the commands you can use with Lyra:\n\n");
        
        helpText.append("📝 TASK MANAGEMENT:\n");
        helpText.append("  todo <description>           - Add a new todo task\n");
        helpText.append("  deadline <task> /by <when>   - Add a deadline task\n");
        helpText.append("  event <task> /from <start> /to <end> - Add an event task\n\n");
        
        helpText.append("📋 TASK OPERATIONS:\n");
        helpText.append("  list                         - Show all tasks\n");
        helpText.append("  mark <number>                - Mark task as done\n");
        helpText.append("  unmark <number>              - Mark task as not done\n");
        helpText.append("  delete <number>              - Delete a task\n\n");
        
        helpText.append("🔍 SEARCH & ORGANIZE:\n");
        helpText.append("  find <keyword>               - Search for tasks\n");
        helpText.append("  sort <criteria>              - Sort tasks by criteria\n");
        helpText.append("    Available criteria: description, deadline, event, type, status\n\n");
        
        helpText.append("ℹ️  UTILITY:\n");
        helpText.append("  help                         - Show this help message\n");
        helpText.append("  bye                          - Exit the application\n\n");
        
        helpText.append("💡 TIPS:\n");
        helpText.append("  • Task numbers start from 1\n");
        helpText.append("  • Use quotes for descriptions with spaces\n");
        helpText.append("  • Dates can be in various formats (e.g., 2024-12-25, Dec 25 2024)\n");
        helpText.append("  • Search is case-insensitive\n");
        
        ui.showMessage(helpText.toString());
    }
}
