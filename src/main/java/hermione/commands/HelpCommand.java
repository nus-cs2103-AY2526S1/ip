package hermione.commands;

import hermione.storage.TaskStorage;

/**
 * Represents a command to display help information in the Hermione application.
 */
public class HelpCommand extends Command {

    /**
     * Constructor for the HelpCommand class.
     *
     * @param storage  The TaskStorage instance used to manage tasks.
     * @param argument The argument string that contains the command details.
     */
    public HelpCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the command to display help1 information.
     * This method returns a string containing a list of available commands
     * and their descriptions.
     *
     * @return A string containing help information for the user.
     */
    @Override
    public String execute() {
        return """
                Available commands:
                1. help (h) - Show this help message
                2. bye (b) - Exit the application
                3. list (l) - List all items
                4. todo (t) {description} - Add a new ToDo task
                5. deadline (dl) {description} /by {date} - Add a new Deadline task
                6. event (e) {description} /from {start date} /to {end date} - Add a new Event task
                7. fixed (fi) {description} /duration {duration} - Add a new Fixed Duration task
                8. mark (m) {task number} - Mark a task as completed
                9. unmark (um) {task number} - Unmark a task as not completed
                10. delete (d) {task number} - Delete a task
                11. find (fd) {keyword} - Find tasks containing the keyword
                Note: You can use either the full command name or the shorthand in parentheses.
                """;
    }
}
