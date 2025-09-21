package command;

import exception.FindException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) throws FindException {
        try {
            if (input.substring(5).isEmpty()) throw new Exception();
            TaskList filtered = t.filter(input.substring(5));
            u.chatbotPrint("Here are the matching tasks in your list:" + filtered.toString());
        } catch (Exception e) {
            throw new FindException("I'm not sure what you're trying to find. Try again?");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
