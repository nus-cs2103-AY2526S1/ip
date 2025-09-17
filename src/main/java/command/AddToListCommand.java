package command;

import javafx.util.Pair;
import misc.PepeException;
import state.Storage;
import state.TaskList;
import state.Ui;
import tasks.Task;

/**
 * Command that adds a Task to the task list.
 */
public class AddToListCommand implements Command {
    private final Task task;

    public AddToListCommand(Task task) {
        this.task = task;
    }

    @Override
    public Pair<String, Boolean> execute(Ui ui, Storage storage, TaskList tasks) throws PepeException {
        tasks.add(task);
        storage.saveTasks(tasks);
        String message = "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n".formatted(task,
                tasks.size());
        return new Pair<>(ui.formatMessage(message), true);
    }
}
