import misc.PepeException;
import state.Storage;
import state.TaskList;
import state.Ui;

/**
 * The main Pepe application class.
 */
public class Pepe {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    private final String welcomeMessage = "Hi Pepe! I am Pepe the Fourth, descendent of Pepe! What can I do for you?";

    /**
     * Public constructor to construct an instance of the Pepe application from a file that contains the task list.
     * @param storageFilePath A path to a file that contains the current task list.
     */
    public Pepe(String storageFilePath) throws PepeException {
        assert(storageFilePath != null && !storageFilePath.isEmpty());
        this.ui = new Ui();
        this.storage = new Storage(storageFilePath);
        this.taskList = new TaskList(this.storage.getTasks());
    }

    public String displayWelcomeMessage() {
        return welcomeMessage;
    }

    /**
     * Getter method for Ui.
     * @return the Ui for Pepe
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Getter method for task list.
     * @return the task list for Pepe
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Getter method for storage.
     * @return the storage for Pepe
     */
    public Storage getStorage() {
        return storage;
    }
}
