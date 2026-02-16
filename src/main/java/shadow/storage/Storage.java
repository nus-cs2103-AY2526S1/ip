package shadow.storage;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import shadow.parsers.DateTimeAdapter;
import shadow.parsers.FilePathParser;
import shadow.tasks.DeadLine;
import shadow.tasks.Event;
import shadow.tasks.Task;
import shadow.tasks.ToDo;


/**
 * A singleton class responsible for managing the storage of tasks.
 * Provides methods to load, save, add, remove, and modify tasks.
 * Tasks are persisted in a JSON file in the user's home directory.
 */
public class Storage {

    private static final String DEFAULT_FILE_HOME = System.getProperty("user.home");
    private static final Path DEFAULT_FILE_DIRECTORY = Paths.get(DEFAULT_FILE_HOME, ".shadowData");
    private static final Path DEFAULT_FILE_PATH = DEFAULT_FILE_DIRECTORY.resolve("tasks.json");

    private static Storage instance;

    private final Gson gson;
    private Path filePath;
    private final Type taskListType = new TypeToken<List<Task>>() {}.getType();
    private List<Task> tasks;


    /**
     * Constructs a new {@code Storage} instance.
     * <p>
     * Initializes the storage directory and file path in the user's home directory,
     * sets up Gson serialization with custom adapters for {@link Task} and {@link LocalDateTime},
     * and attempts to load existing tasks from disk.
     * </p>
     * <p>
     * If the storage directory does not exist, it will be created.
     * </p>
     */
    private Storage() {
        this.filePath = DEFAULT_FILE_PATH;

        this.gson = new GsonBuilder()
                .registerTypeAdapterFactory(
                        RuntimeTypeAdapterFactory.of(Task.class, "type")
                                .registerSubtype(ToDo.class, "todo")
                                .registerSubtype(DeadLine.class, "deadline")
                                .registerSubtype(Event.class, "event")
                )
                .registerTypeAdapter(LocalDateTime.class, new DateTimeAdapter())
                .setPrettyPrinting()
                .create();
        this.tasks = new ArrayList<>();

        try {
            if (!Files.exists(DEFAULT_FILE_DIRECTORY)) {
                Files.createDirectories(DEFAULT_FILE_DIRECTORY);
            }
        } catch (IOException e) {
            System.err.println("Failed to create storage directory: " + e.getMessage());
        }

        load();
    }


    /**
     * Returns the singleton instance of the {@code Storage} class.
     * <p>
     * Creates a new instance if it does not already exist.
     * </p>
     *
     * @return the singleton {@code Storage} instance
     */
    public static Storage getInstance() {
        if (Storage.instance == null) {
            Storage.instance = new Storage();
        }
        return Storage.instance;
    }

    /**
     * Loads tasks from the JSON file into memory.
     * <p>
     * If the file does not exist, no action is taken.
     * If an error occurs during reading, an error message is printed to standard error.
     * </p>
     */
    private void load() {
        if (!Files.exists(filePath)) {
            return;
        }
        try (Reader reader = Files.newBufferedReader(filePath)) {
            List<Task> load = gson.fromJson(reader, taskListType);
            if (load != null) {
                this.tasks = load;
            }
        } catch (IOException e) {
            System.err.println("Failed to load tasks: " + e.getMessage());

        }
    }

    /**
     * Saves the current list of tasks to the JSON file.
     * <p>
     * If an error occurs during writing, an error message is printed to standard error.
     * </p>
     */
    public void save() {
        try (Writer writer = Files.newBufferedWriter(filePath)) {
            gson.toJson(tasks, taskListType, writer);
        } catch (IOException e) {
            System.err.println("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Adds a new task to the list and persists the updated list.
     *
     * @param task the {@link Task} to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        save();
    }

    /**
     * Removes the task at the specified index and persists the updated list.
     *
     * @param i the index of the task to remove
     * @return the removed {@link Task}
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task removeTask(int i) {
        Task removed = this.tasks.remove(i);
        save();
        return removed;
    }

    /**
     * Returns the current list of tasks stored in memory.
     *
     * @return a {@link List} of {@link Task} objects
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks the task at the specified index as done and persists the updated list.
     *
     * @param i the index of the task to mark
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public void markTask(int i) {
        this.tasks.get(i).mark();
        save();
    }

    /**
     * Unmarks the task at the specified index as not done and persists the updated list.
     *
     * @param i the index of the task to unmark
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public void unmarkTask(int i) {
        this.tasks.get(i).unmark();
        save();
    }

    /**
     * Sets a temporary source file path for storing tasks and initializes
     * a new task list. The provided path is resolved and validated using
     * {@link FilePathParser#resolveUserPath(String, boolean, boolean)}.
     *
     * @param path the temporary source file path to be set. This path is resolved
     *             to an absolute, normalized form and must not contain illegal characters.
     * @throws IOException if an error occurs while resolving the path or creating
     *                     necessary parent directories.
     */
    public void setTemporarySourcePath(String path) throws IOException {
        this.filePath = FilePathParser.resolveUserPath(path, true, true);
        this.tasks = new ArrayList<>();
        load();

    }

    /**
     * Sets the default source file path for storing tasks and initializes a new task list.
     * This method resets the file path to the predefined default value and replaces the
     * current in-memory list of tasks with an empty list. It also attempts to load any
     * existing tasks from the default file path into memory.
     */
    public void setDefaultSourcePath() {
        this.filePath = DEFAULT_FILE_PATH;
        this.tasks = new ArrayList<>();
        load();

    }
}
