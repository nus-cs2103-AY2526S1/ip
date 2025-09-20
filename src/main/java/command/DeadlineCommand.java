package command;

import task.Deadline;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.DeadlineException;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) throws Exception {
        try {
            Deadline td = new Deadline(input.substring(9, input.indexOf("/by") - 1), input.substring(input.indexOf("/by") + 4));
            t.add(td);
            u.chatbotPrint("Got it. I've added this task:\n      " + td);
            u.chatbotPrint("Now you have " + t.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DeadlineException("To create a new deadline item, the command is: deadline /by [due date (yyyy-mm-dd)]");
        }

        s.saveToFile(t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
