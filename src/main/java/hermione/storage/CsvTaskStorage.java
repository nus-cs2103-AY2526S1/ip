package hermione.storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import hermione.tasks.Task;
import hermione.tasks.TaskList;
import hermione.utils.FileUtils;
import hermione.utils.StorageUtils;

/**
 * Represents a file-based task storage implementation that persists tasks in
 * CSV format.
 */
public class CsvTaskStorage implements TaskStorage {

    private static final StorageUtils STORAGE_UTILS = new StorageUtils();
    private final Path filePath;
    private TaskList tasks;

    /**
     * Constructor for CsvTaskStorage.
     * Initializes the storage with the specified file path and loads existing
     * tasks.
     *
     * @param filePath The path to the CSV file where tasks are stored.
     */
    public CsvTaskStorage(String filePath) {
        this.filePath = Paths.get(filePath);
        this.tasks = loadTasks();
    }

    private TaskList loadTasks() {
        List<String> lines = FileUtils.readAllLines(this.filePath);
        List<Task> taskList = lines.stream()
                .map(this::parseTaskSafely)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new TaskList(taskList);
    }

    private void saveTasks(TaskList tasks) {
        List<String> lines = tasks.getTasks()
                .stream()
                .map(STORAGE_UTILS::serialize)
                .toList();
        FileUtils.writeAllLines(this.filePath, lines);
        this.tasks = loadTasks();
    }

    @Override
    public TaskList getTasks() {
        return this.tasks;
    }

    @Override
    public void addTask(Task task) {
        assert task != null : "Task cannot be null";

        tasks.addTask(task);
        saveTasks(tasks);
    }

    @Override
    public Task deleteTask(int index) {
        assert index >= 0 && index < tasks.getSize() : "Index out of bounds";

        Task task = tasks.getTask(index);
        tasks.deleteTask(task);
        saveTasks(tasks);
        return task;
    }

    @Override
    public void setTaskCompletion(Task task, boolean isComplete) {
        assert task != null : "Task cannot be null";

        task.setCompleted(isComplete);
        saveTasks(tasks);
    }

    private Task parseTaskSafely(String line) {
        return STORAGE_UTILS.deserialize(line);
    }
}
