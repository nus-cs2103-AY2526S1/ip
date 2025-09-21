package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public abstract class Command {
    protected final String input;
    public Command(String input) {
        this.input = input;
    }
    public abstract void execute(TaskList t, Ui u, Storage s) throws Exception;
    public abstract boolean isExit();
}
