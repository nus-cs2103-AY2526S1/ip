package command;

import exception.DeleteException;
import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) throws Exception {
        try {
            int item = Integer.parseInt(input.substring(7));
            Task taskToDelete = t.get(item - 1);
            t.remove(item - 1);
            u.chatbotPrint("I've deleted the task:\n      " + taskToDelete);
            u.chatbotPrint("Now you have " + t.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DeleteException("I'm not sure which item you're trying to delete. Try again?");
        }

        s.saveToFile(t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
