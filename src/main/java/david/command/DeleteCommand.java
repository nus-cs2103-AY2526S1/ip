package david.command;

import david.exception.DavidException;
import david.exception.IndexException;
import david.exception.NumberException;
import david.task.Task;
import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;

/**
 * Deletes a task from the list.
 */
public class DeleteCommand extends Command {
    private String command;

    /**
     * @param command The entire delete command.
     */
    public DeleteCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        String[] strArr = command.split(" ");
        if (strArr.length != 2 || !isInteger(strArr[1])) {
            throw new NumberException("the value you entered after delete");
        }
        int index = Integer.parseInt(strArr[1]) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new IndexException("the value you entered after delete");
        }
        Task t = tasks.get(index);
        tasks.delete(index);
        storage.save(tasks);
        String task = (tasks.size() > 1) ? "tasks" : "task";
        String msg = "Noted, I've removed this task:\n  " + t
                            + "\n Now you have " + tasks.size() + " "
                                                    + task + " in the list.";
        ui.showMessage(msg);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        String[] strArr = command.split(" ");
        if (strArr.length != 2 || !isInteger(strArr[1])) {
            throw new NumberException("the value you entered after delete");
        }
        int index = Integer.parseInt(strArr[1]) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new IndexException("the value you entered after delete");
        }
        Task t = tasks.get(index);
        tasks.delete(index);
        storage.save(tasks);
        String task = (tasks.size() > 1) ? "tasks" : "task";
        String msg = "Noted, I've removed this task:\n  " + t
                            + "\n Now you have " + tasks.size() + " "
                                                    + task + " in the list.";
        return msg;
    }

    private static boolean isInteger(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
