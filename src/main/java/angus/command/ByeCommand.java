package angus.command;

import angus.exception.AngusException;
import angus.storage.Storage;
import angus.task.TaskList;
import angus.ui.Ui;

/**
 * Represents command to exit the chatbot application Angus.
 * <p>
 * This class is responsible for saving the tasks stored in the TaskList and
 * displaying the goodbye message to the user before ending the program loop.
 */
public class ByeCommand extends Commands {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs a new instance of the ByeCommand with the given Ui, Storage and TaskList.
     * @param ui The instance of the user interface class Ui to interact with the user.
     * @param storage The instance of the Storage class to save the tasks into a txt file.
     * @param tasks The current list of tasks to be saved into a txt file.
     */
    public ByeCommand(Ui ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    @Override
    public String execute() throws AngusException {
        storage.save(tasks);
        return ui.printGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
