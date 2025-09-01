package aurora.command;

import aurora.task.TaskList;

/**
 * Command to display all existing commands.
 */
public class HelpCommand implements Command {
    @Override
    public String execute(TaskList list) {
        return """
                To add a todo, enter "todo <description>"
                To add a deadline, enter "deadline <description> /by: <deadline>"
                To add an event, enter "event <description> /from: <start> /to: <end>"
                To see a list of all added tasks, enter "list"
                To mark a task as complete, enter "mark <task number>"
                To remove a task in the list, enter "delete <task number>"
                To exit Aurora, enter "bye"
                To see list of existing commands, enter "help"
                """;
    }
}
