package nami.command;
import nami.ui.Ui;
import nami.storage.Storage;
import nami.task.TaskList;
import nami.exception.DukeException;
public class UnmarkCommand extends Command {
    private final int index; // 0-based

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * unmarks the specific task
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
        tasks.get(index).unmarkAsDone();
        storage.save(tasks.asList());
        return
        "___________________________________\n" +
        "OK, I've marked this task as not done yet:\n" +
        "[] " + tasks.get(index).getDescription() +
        "\n_________________________________";
    }
}
