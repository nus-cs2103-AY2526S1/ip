package benn.storage;

import benn.exceptions.BennException;
import benn.patterns.TaskStoragePattern;
import benn.tasks.Deadline;
import benn.tasks.Event;
import benn.tasks.Task;
import benn.tasks.Todo;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Handles the persistent storage of tasks in Benn the Chatbot.
 *
 * <p>The {@code TaskStorage} class is responsible for loading tasks
 * from a save file at startup, writing tasks to disk whenever the
 * task list changes, and providing helper methods to add, remove,
 * or retrieve tasks. The storage format is line-based, with each
 * line describing a single task in a pipe-delimited form defined
 * by {@link benn.patterns.TaskStoragePattern}.</p>
 *
 * <p>The save file is stored at {@code ./temporary_datastore/tasks.txt}.
 * If the file or its parent directory does not exist, they will be
 * created automatically. If the path points to a directory instead of
 * a file, a {@link benn.exceptions.BennException} is thrown.</p>
 */
public class TaskStorage {
    private static final String TASK_STORAGE_PATH = "./temporary_datastore/tasks.txt";

    private List<Task> tasks;

    private TaskStorage(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Initializes the storage system. Loads tasks if the save file already
     * exists, or creates a new save file otherwise.
     *
     * @return a {@code TaskStorage} object representing the loaded or new state
     * @throws IOException if the file cannot be created or accessed
     * @throws BennException if the path is invalid (e.g. a directory instead of a file)
     */
    public static TaskStorage start() throws IOException, BennException {
        File taskStorage = new File(TASK_STORAGE_PATH);
        if (taskStorage.isDirectory()) {
            throw new BennException("Directory detected, can't create task storage file!");
        } else if (taskStorage.exists() && taskStorage.isFile()) {
            return TaskStorage.loadFromExisting(taskStorage);
        } else {
            return TaskStorage.createNew(taskStorage);
        }
    }

    /**
     * Adds a task to the in-memory list and persists the updated list to disk.
     *
     * @param task the task to add
     * @throws IOException if writing to the save file fails
     */
    public void add(Task task) throws IOException {
        this.tasks.add(task);
        this.flush();
    }

    /**
     * Retrieves the task at the specified index in the in-memory list.
     *
     * @param index the index of the task (0-based)
     * @return the task at the specified index
     */
    public Task getTaskLocatedAt(int index) {
        assert index >= 0 && index < this.tasks.size()
                : "TaskStorage.getTaskLocatedAt: index out of bounds";
        return this.tasks.get(index);
    }

    /**
     * Removes the task at the specified index, persists the updated list
     * to disk, and returns the removed task.
     *
     * @param index the index of the task (0-based)
     * @return the removed task
     * @throws IOException if writing to the save file fails
     */
    public Task removeTaskLocatedAt(int index) throws IOException {
        assert index >= 0 && index < this.tasks.size()
                : "TaskStorage.removeTaskLocatedAt: index out of bounds";
        Task task = this.tasks.remove(index);
        this.flush();
        return task;
    }

    /**
     * Finds all tasks whose descriptions contain the given keyword.
     *
     * <p>The search is case-sensitive and will return tasks in the order
     * they are currently stored. If no tasks match, an empty list is returned.</p>
     *
     * @param keyword the keyword to search for in task descriptions
     * @return a list of {@link benn.tasks.Task} objects whose descriptions
     *         contain the keyword; the list may be empty if no matches are found
     */
    public List<Task> findAllTasksContaining(String keyword) {
        assert keyword != null : "TaskStorage.findAllTasksContaining: keyword must not be null";
        return this.tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of tasks currently stored in memory.
     *
     * @return the number of tasks
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Creates a new storage file at the specified location,
     * along with any required parent directories.
     *
     * @param file the file to create
     * @return a {@code TaskStorage} initialized with an empty task list
     * @throws IOException if the file cannot be created
     */
    private static TaskStorage createNew(File file) throws IOException {
        File parent = file.getParentFile();
        parent.mkdirs();
        file.createNewFile();
        return new TaskStorage(new ArrayList<>());
    }

    /**
     * Loads tasks from an existing storage file by parsing each line.
     *
     * @param file the file to load tasks from
     * @return a {@code TaskStorage} initialized with the loaded tasks
     * @throws IOException if the file cannot be read
     * @throws BennException if a line in the file cannot be parsed
     */
    private static TaskStorage loadFromExisting(File file) throws IOException, BennException {
        List<Task> taskList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             Scanner scanner = new Scanner(fis, java.nio.charset.StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                Task task = parseLine(line);
                taskList.add(task);
            }
        }
        return new TaskStorage(taskList);
    }

    /**
     * Writes all tasks from memory to the storage file,
     * overwriting the previous contents.
     *
     * @throws IOException if writing to the save file fails
     */
    public void flush() throws IOException {
        List<String> lines = this.tasks.stream()
                .map(Task::toStorageFormat)
                .toList();
        try (FileWriter writer = new FileWriter(TASK_STORAGE_PATH, false)) {
            for (String line : lines) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        }
    }

    /**
     * Parses a line of text into a {@link benn.tasks.Task} object,
     * based on {@link benn.patterns.TaskStoragePattern}.
     *
     * @param line the line of text representing a stored task
     * @return the parsed {@code Task}
     * @throws BennException if the line does not match any known task format
     */
    private static Task parseLine(String line) throws BennException {
        Matcher matcher;
        if ((matcher = doesPatternMatch(TaskStoragePattern.TODO, line)) != null) {
            String description = matcher.group("description");
            String done = matcher.group("done");
            boolean isDone = "1".equals(done);
            return new Todo(description, isDone);
        }

        if ((matcher = doesPatternMatch(TaskStoragePattern.DEADLINE, line)) != null) {
            String description = matcher.group("description");
            String by = matcher.group("by").replace('T', ' ');
            String done = matcher.group("done");
            boolean isDone = "1".equals(done);
            return new Deadline(description, by, isDone);
        }

        if ((matcher = doesPatternMatch(TaskStoragePattern.EVENT, line)) != null) {
            String description = matcher.group("description");
            String from = matcher.group("from").replace('T', ' ');
            String to = matcher.group("to").replace('T', ' ');
            String done = matcher.group("done");
            boolean isDone = "1".equals(done);
            return new Event(description, from, to, isDone);
        }

        throw new BennException("Unknown task format: " + line);
    }

    /**
     * Attempts to match the given line against a regex pattern.
     *
     * @param p the pattern to test
     * @param line the line of text to match
     * @return the matcher if a match is found, or {@code null} otherwise
     */
    private static Matcher doesPatternMatch(Pattern p, String line) {
        Matcher m = p.matcher(line);
        return m.find() ? m : null;
    }

}
