package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        u.chatbotPrint("Here are the tasks in your list:" + t.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
