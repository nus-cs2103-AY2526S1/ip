package snowy.command;

import snowy.ui.Ui;
import snowy.TaskList;
import snowy.Storage;
import snowy.SnowyException;

public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage) throws SnowyException;

    public boolean isEnd() {
        return false;
    }
}
