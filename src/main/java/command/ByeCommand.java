package command;

import task.TaskList;
import ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String arg, TaskList tasklist) {
        super(arg, tasklist);
    }

    /**
     * Exits the program
     */
    public void execute() {
        Ui.bye();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
