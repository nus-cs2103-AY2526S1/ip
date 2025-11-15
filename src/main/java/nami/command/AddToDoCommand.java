package nami.command;
import nami.task.ToDo;
import nami.ui.Ui;
import nami.storage.Storage;
import nami.task.TaskList;
import nami.task.Tasks;
import nami.exception.DukeException;
public class AddToDoCommand extends Command {
    private final String description;
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes and gets back a String which will eventually be used to print
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description == null || description.isEmpty()) {
            throw new DukeException("the description of a todo cannot be empty.");
        }
        Tasks t = new ToDo(description);
        tasks.add(t);
        storage.save(tasks.asList());

        return
        "______________________________________ \n" +
        "Got it. I've added this task: \n" +
        t.getResult() +
        "\nNow you have " + tasks.size() + " tasks in this list. \n" +
        "______________________________________";
    }
}
