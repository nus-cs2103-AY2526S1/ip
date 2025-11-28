package nailongbot.command;

import nailongbot.exception.JkBotException;
import nailongbot.utils.Storage;
import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;

/**
 * Command to add a find word in desc.
 */
public class FindCommand implements Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        StringBuilder message = new StringBuilder("");
        int index = 1;
        if (tasks.isEmpty()) {
            message = new StringBuilder("No tasks in your list yet!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.getTask(i).getDescription().contains(query)) {
                    String taskLine = index + ". " + tasks.getTask(i);
                    message.append(taskLine).append("\n");
                    index++;
                }
            }
        }
        return Ui.LINE + message + Ui.LINE;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
