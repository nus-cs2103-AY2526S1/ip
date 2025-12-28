package meeseeks.command;

import meeseeks.storage.Storage;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
    
    public String executeAndGetResponse(TaskList tasks, Storage storage) throws Exception {
        // Create a temporary UI to capture output
        Ui tempUi = new Ui();
        execute(tasks, tempUi, storage);
        return tempUi.getLastMessage();
    }
    
    public boolean isExit() {
        return false;
    }
}
