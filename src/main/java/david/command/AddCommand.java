package david.command;

import david.exception.DavidException;
import david.task.Task;
import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;

/**
 * Adds a task to the list.
 */
public class AddCommand extends Command {
    private String command;

    /**
     * @param command The entire add command.
     */
    public AddCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        Task t = Task.of(command);
        tasks.add(t);
        storage.save(tasks);
        String task = (tasks.size() > 1) ? "tasks" : "task";
        String msg = "Got it. I've added this task:\n  "
                    + t + "\n Now you have " + tasks.size() + " "
                                                + task + " in the list.";
        ui.showMessage(msg);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        Task t = Task.of(command);
        tasks.add(t);
        storage.save(tasks);
        String task = (tasks.size() > 1) ? "tasks" : "task";
        String msg = "Got it. I've added this task:\n  "
                + t + "\n Now you have " + tasks.size() + " "
                + task + " in the list.";
        return msg;
    }
}
