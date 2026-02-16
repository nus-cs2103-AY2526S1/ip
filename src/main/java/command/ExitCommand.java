package command;

import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return "Thank you, Master Bruce. See you soon.";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
