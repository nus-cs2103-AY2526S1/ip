package vicky.command;

import vicky.storage.Storage;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Class responsible for printing a love command.
 */
public class LoveCommand extends Command {

    private String str;

    public LoveCommand(String str) {
        this.str = str;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showLove(this.str);
    }
}
