package hachiware;
//AI helped to generate javadoc comments

/**
 * Represents the main application class for Hachiware.
 * <p>
 * This class manages the core components of the application, including:
 * <ul>
 *   <li>{@link TaskList} for storing tasks.</li>
 *   <li>{@link StoreFile} for loading and saving tasks to persistent storage.</li>
 *   <li>{@link Ui} for interacting with the user.</li>
 * </ul>
 * It initializes these components and handles errors during task loading.
 * </p>
 */
public class Hachiware {
    /** Error message displayed if tasks fail to load. */
    private static final String LOAD_ERROR = "Failed to load tasks!";

    /** Handles storage operations such as loading and saving tasks. */
    private StoreFile storage;

    /** The list of tasks managed by the application. */
    private TaskList tasks;

    /** The user interface for displaying messages and receiving input. */
    private Ui ui;



    /**
     * Constructs a new {@code Hachiware} instance.
     * <p>
     * Initializes the UI, storage, and task list. Attempts to load existing tasks
     * from the provided file path. If loading fails, an empty task list is created
     * and an error message is shown.
     * </p>
     *
     * @param filePath the file path to load and save tasks
     */
    public Hachiware(String filePath) {
        ui = new Ui();
        storage = new StoreFile(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (HachiwareException e) {
            ui.showError(LOAD_ERROR);
            tasks = new TaskList();
        }
    }

    /**
     * Returns the current task list.
     *
     * @return the {@link TaskList} managed by this application
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Returns the storage handler.
     *
     * @return the {@link StoreFile} used for loading and saving tasks
     */
    public StoreFile getStorage() {
        return storage;
    }


}
