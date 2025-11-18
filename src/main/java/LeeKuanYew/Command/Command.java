package LeeKuanYew.Command;

import LeeKuanYew.Ui;
import LeeKuanYew.Storage;
import LeeKuanYew.Task.TaskList;

public abstract class Command {

    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    public boolean checkExit() {
        return false;
    }
}
