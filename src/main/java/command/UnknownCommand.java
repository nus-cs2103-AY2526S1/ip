package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        u.showError("I'm not sure what command you're trying to run. Try again?");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
