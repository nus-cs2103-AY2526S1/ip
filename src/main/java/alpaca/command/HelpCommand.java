package alpaca.command;

import alpaca.Storage;
import alpaca.task.TaskList;
import alpaca.Ui;

/**
 * Command to display a list of possible commands and their syntax.
 */
public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String helpMessage = """
                Here are the commands you can use:

                1. todo <description>                     - Add a todo task
                2. deadline <desc> /by <yyyy-MM-dd>      - Add a deadline task
                3. event <desc> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm> - Add an event
                4. within <desc> /from <yyyy-MM-dd> /to <yyyy-MM-dd> - Add a task to be done within a period
                5. list                                   - List all tasks
                6. mark <task number>                     - Mark a task as done
                7. unmark <task number>                   - Mark a task as not done
                8. delete <task number>                   - Delete a task
                9. find <keyword>                         - Find tasks containing keyword
                10. help                                  - Show this help message
                11. bye                                   - Exit the app
                """;

        ui.showMessage(helpMessage);
        return helpMessage;
    }
}
