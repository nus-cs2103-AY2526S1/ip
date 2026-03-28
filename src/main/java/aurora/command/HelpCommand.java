package aurora.command;

import aurora.task.TaskList;

/**
 * Command to display all existing commands.
 */
public class HelpCommand implements Command {
    @Override
    public String execute(TaskList list) {
        // Help command text expanded to include all commands supported by CommandReader.
        // AI assistance used for formatting and ensuring consistency with existing commands.
        return """
                Available commands:
                
                To add a todo:               todo <description>
                To add a deadline:           deadline <description> /by: <deadline>
                To add an event:             event <description> /from: <start> /to: <end>
                
                To see all tasks:            list
                To mark a task complete:     mark <task number>
                To delete a task:            delete <task number>
                To find tasks by keyword:    find <keyword>
                
                To tag a task:               tag <task number> <tag>
                To remove a tag:             untag <task number> <tag>
                
                To exit Aurora:              bye / close the app
                To see this help message:    help
                """;
    }
}
