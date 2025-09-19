package bytebot.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import bytebot.ByteException;
import bytebot.parser.Parser;
import bytebot.task.Deadline;
import bytebot.task.Event;
import bytebot.task.Task;
import bytebot.task.TaskList;
import bytebot.task.Todo;

/**
 * Handles task management (unmark, mark, delete, etc) and persistence.
 */
public class Storage {
    private static final String DATA_DIR = "data";
    private static final String FILE_NAME = "byte.txt";
    private final Path filePath;
    private TaskList taskList;

    /**
     * Creates a Storage instance with the default file path.
     */
    public Storage() {
        this.filePath = Paths.get(DATA_DIR, FILE_NAME);
        this.taskList = new TaskList();
    }

    /**
     * Loads tasks from the file.
     * Creates the directory and file if they don't exist.
     *
     * @return TaskList of loaded tasks
     * @throws IOException if there's an error reading the file
     */
    public TaskList load() throws IOException {
        assert DATA_DIR != null && !DATA_DIR.isEmpty() : "Data directory must be defined";
        assert FILE_NAME != null && !FILE_NAME.isEmpty() : "File name must be defined";
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            return new TaskList();
        }

        assert filePath != null : "File path must exist";
        List<String> lines = Files.readAllLines(filePath);
        List<Task> tasks = new ArrayList<>();

        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                try {
                    Task task = Parser.parseTaskFromString(line);
                    tasks.add(task);
                } catch (ByteException e) {
                    System.err.println("Invalid task line: " + line + " - " + e.getMessage());
                }
            }
        }

        this.taskList = new TaskList(tasks);
        return this.taskList;
    }

    /**
     * Gets the current task list.
     *
     * @return The task list
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Returns a copy of all tasks for read iteration.
     * Callers should not mutate returned tasks in a way that breaks invariants.
     */
    public List<Task> getAllTasks() {
        assert taskList != null : "Task list must exist";
        return new ArrayList<>(taskList.asList());
    }

    /**
     * Returns all tasks sorted by due date.
     *
     * @return list of deadlines sorted by due date ascending
     */
    public TaskList getDeadlinesSorted() {
        List<Deadline> deadlines = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task instanceof Deadline) {
                deadlines.add((Deadline) task);
            }
        }
        deadlines.sort(Comparator.comparing(Deadline::getBy));
        List<Task> asTasks = new ArrayList<>(deadlines.size());
        asTasks.addAll(deadlines);
        return new TaskList(asTasks);
    }

    /**
     * Partitions tasks into Events, Deadlines and Todos, sorts each partition,
     * and returns a combined list in that order.
     *
     * - Events sorted by end time
     * - Deadlines sorted by due time
     * - Todos sorted lexicographically by their text representation
     *
     * @return combined sorted list of tasks
     */
    public TaskList getAllTasksSortedByType() {
        List<Event> events = new ArrayList<>();
        List<Deadline> deadlines = new ArrayList<>();
        List<Todo> todos = new ArrayList<>();

        partitionTasksByType(events, deadlines, todos);

        events.sort(Comparator.comparing(Event::getTo));
        deadlines.sort(Comparator.comparing(Deadline::getBy));
        todos.sort(Comparator.comparing(Task::toString));

        List<Task> combined = new ArrayList<>(events.size() + deadlines.size() + todos.size());
        combined.addAll(events);
        combined.addAll(deadlines);
        combined.addAll(todos);
        return new TaskList(combined);
    }

    /**
     * Partitions current tasks into the provided collections by runtime type.
     */
    private void partitionTasksByType(List<Event> events, List<Deadline> deadlines, List<Todo> todos) {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task instanceof Event) {
                events.add((Event) task);
            } else if (task instanceof Deadline) {
                deadlines.add((Deadline) task);
            } else if (task instanceof Todo) {
                todos.add((Todo) task);
            }
        }
    }

    /**
     * Finds tasks whose string representation contains the given keyword.
     *
     * @param keyword text to search for
     * @return list of matching tasks
     */
    public TaskList findTasksByKeyword(String keyword) {
        final String searchText = Optional.ofNullable(keyword)
                .map(String::toLowerCase)
                .orElse("");

        List<Task> matches = taskList.asList().stream()
                .filter(task -> task.toString()
                .toLowerCase().contains(searchText))
                .toList();
        return new TaskList(matches);
    }

    /**
     * Initializes the storage with a given TaskList.
     * This is used when loading from file fails, then start with an empty list.
     *
     * @param taskList The TaskList to initialize with
     */
    public void initializeWithTaskList(TaskList taskList) {
        assert taskList != null : "Provided task list cannot be null";
        this.taskList = taskList;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return Number of tasks persisted
     */
    public int getSize() {
        assert taskList != null : "Task list must exist";
        return taskList.size();
    }

    /**
     * Gets a task at the specified index.
     *
     * @param index Index to get
     * @return The task at the given index
     * @throws ByteException If index is out of range
     */
    public Task getTask(int index) throws ByteException {
        assert taskList != null : "Task list must exist";
        return taskList.get(index);
    }

    /**
     * Adds a task to the list and saves.
     *
     * @param task Task to add
     */
    public void addTask(Task task) {
        assert task != null : "Task to add must exist";
        assert taskList != null : "Task list must exist";
        taskList.add(task);
        saveTasks(taskList);
    }

    /**
     * Marks a task as done and saves.
     *
     * @param index Index of the task to mark
     * @throws ByteException If the index is invalid
     */
    public void markTask(int index) throws ByteException {
        assert taskList != null : "Task list must exist";
        taskList.mark(index);
        saveTasks(taskList);
    }

    /**
     * Marks a task as not done and saves.
     *
     * @param index Index of the task to unmark
     * @throws ByteException If the index is invalid
     */
    public void unmarkTask(int index) throws ByteException {
        assert taskList != null : "Task list must exist";
        taskList.unmark(index);
        saveTasks(taskList);
    }

    /**
     * Deletes a task and saves.
     *
     * @param index Index of the task to delete
     * @return The removed task
     * @throws ByteException If the index is invalid
     */
    public Task deleteTask(int index) throws ByteException {
        assert taskList != null : "Task list must exist";
        Task removed = taskList.delete(index);
        saveTasks(taskList);
        return removed;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks TaskList to save
     */
    public void saveTasks(TaskList tasks) {
        assert tasks != null : "Tasks to save must exist";
        try {
            File dataDir = new File(DATA_DIR);
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            FileWriter writer = new FileWriter(filePath.toFile());
            tasks.writeToFile(writer);
            writer.close();
        } catch (IOException e) {
            System.err.println("Could not save tasks to file: " + e.getMessage());
        }
    }
}
