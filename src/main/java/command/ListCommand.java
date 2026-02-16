package command;

import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.toString();
    }
}
