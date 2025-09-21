package command;

import exception.MarkException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

public class MarkCommand extends Command {
    public MarkCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) throws Exception {
        try {
            int item = Integer.parseInt(input.substring(5));
            t.get(item - 1).markAsDone();
            u.chatbotPrint("Nice! I've marked this task as done:\n    "
                    + t.get(item - 1));
        } catch (Exception e) {
            throw new MarkException("I'm not sure which item you're trying to mark. Try again?");
        }

        s.saveToFile(t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
