package nami.command;
import nami.ui.Ui;
import nami.storage.Storage;
import nami.task.TaskList;
import nami.exception.DukeException;
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the specific task
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number out of range.");
        }
        tasks.get(index).markAsDone();
        storage.save(tasks.asList());
        return
        "____________________________________\n" +
        "Nice! I have marked this task as done: \n" +
        "[X] " + tasks.get(index).getDescription() +
        "\n__________________________________";
    }
}
