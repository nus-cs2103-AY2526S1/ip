package command;

import exception.MarkException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) throws Exception {
        try {
            int item = Integer.parseInt(input.substring(7));
            t.get(item - 1).markAsUndone();
            u.chatbotPrint("Ok, I've marked this task as not done yet:\n    "
                    + t.get(item - 1));
        } catch (Exception e) {
            throw new MarkException("I'm not sure which item you're trying to unmark. Try again?");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
