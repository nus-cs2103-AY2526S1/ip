package LeeKuanYew.Command;

import LeeKuanYew.Ui;
import LeeKuanYew.Storage;
import LeeKuanYew.Task.TaskList;

public class ByeCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showMessage("""
                Goodbye fellow countryman! Majulah Singapura!""");
    }

    @Override
    public boolean checkExit() {
        return true;
    }
}
