package command;

import task.Task;

import java.util.List;

public class HelpCommand implements Command {
    @Override
    public String execute(List<Task> list) {
        return """
                
                To add a todo, enter "todo <description>"
                To add a deadline, enter "deadline <description> /by: <deadline>"
                To add an event, enter "event <description> /from: <start> /to: <end>"
                To see a list of all added tasks, enter "list"
                To mark a task as complete, enter "mark <task number>"
                To exit Aurora, enter "bye"
                To see list of existing commands, enter "help"
                """;
    }
}
