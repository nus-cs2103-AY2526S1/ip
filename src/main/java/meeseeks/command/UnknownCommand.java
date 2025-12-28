package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("I'm not sure about that, how about we talk about something else.");
    }
}
