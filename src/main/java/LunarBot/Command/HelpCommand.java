package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Ui;

public class HelpCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        String toPrint = "Hi! Here are the following commands you can use\n" +
                "<task_name>\n" +
                "todo <task_name>\n" +
                "deadline <task_name> /by <task_deadline>\n" +
                "event <task_name> /from <task_begin> /to <task_end>\n" +
                "find <first_few_characters_of_task_name>\n" +
                "list\n" +
                "mark <task_id>\n" +
                "unmark <task_id>\n" +
                "bye\n" +
                "For more info on each command, refer to our user guide!\n";
        return ui.showMessage(toPrint);
    }
}
