package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class ExitCommand extends Command {
    public ExitCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        u.bye();
        u.close();

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
