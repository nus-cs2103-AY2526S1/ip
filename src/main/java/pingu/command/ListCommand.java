package pingu.command;

import pingu.Storage;
import pingu.TaskList;
import pingu.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.toString();
    }
}
