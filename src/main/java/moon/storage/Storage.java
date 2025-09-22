package moon.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import moon.models.Task;
import moon.models.TaskList;
import moon.parser.exceptions.ParseException;
import moon.parser.storage.AddFromStorageParser;

/**
 * Handles persistent storage of tasks for the Moon chatbot.
 * <p>
 * The {@code Storage} class is responsible for:
 * <ul>
 *   <li>Ensuring the storage file and parent directories exist.</li>
 *   <li>Loading tasks from the storage file into a {@link TaskList}.</li>
 *   <li>Rewriting the storage file with the current tasks.</li>
 * </ul>
 */
public class Storage {
    private final Path storageFile;

    /**
     * Creates a {@code Storage} instance for the specified file path.
     * Ensures the storage file exists, creating directories and the file if necessary.
     *
     * @param filepath Path to the storage file
     * @throws IOException If the file or directories cannot be created
     */
    public Storage(String filepath) throws IOException {
        this.storageFile = Paths.get(filepath);
        initialiseStorageIfNotExist();
    }

    /**
     * Ensures the storage file and its parent directories exist.
     * <p>
     * If the parent directory does not exist, it is created. If the storage
     * file does not exist, an empty file is created.
     *
     * @throws IOException If the file or directories cannot be created
     */
    public void initialiseStorageIfNotExist() throws IOException {
        // Ensure parent folder exists
        if (this.storageFile.getParent() != null) {
            Files.createDirectories(this.storageFile.getParent());
        }

        // If the file doesn’t exist, create an empty one
        if (Files.notExists(this.storageFile)) {
            Files.createFile(this.storageFile);
        }
    }

    /**
     * Loads all tasks from the storage file into a {@link TaskList}.
     * <p>
     * Each non-blank line is parsed into a {@link Task} using
     * {@code AddFromStorageParser}.
     *
     * @return A {@code TaskList} containing tasks from storage
     * @throws IOException     If the storage file cannot be read
     * @throws ParseException  If any line cannot be parsed into a valid task
     */
    public TaskList load() throws IOException, ParseException {
        initialiseStorageIfNotExist();
        List<String> lines = Files.readAllLines(storageFile);
        TaskList tasks = new TaskList();
        for (String line : lines) {
            if (!line.isBlank()) {
                Task t = AddFromStorageParser.parse(line);
                assert t != null : "Parsed task should not be null";
                tasks.add(t);
            }
        }
        return tasks;
    }

    /**
     * Rewrites the storage file with the given list of tasks.
     * <p>
     * Existing content is truncated before writing. Each task is
     * converted into its storage string using {@link Task#toStorageString()}.
     *
     * @param tasks The task list to save
     * @throws IOException If the file cannot be written to
     */
    public void rewrite(TaskList tasks) throws IOException {
        initialiseStorageIfNotExist();
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            lines.add(tasks.get(i).toStorageString());
        }
        Files.write(this.storageFile, lines,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }
}
