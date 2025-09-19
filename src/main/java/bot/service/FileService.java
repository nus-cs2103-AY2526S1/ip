package bot.service;

import bot.task.TaskList;
import bot.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A utility class for handling file-based operations, specifically for managing a list of tasks.
 * This class uses the `java.nio.file` API for file system access.
 */
public class FileService {
    private final Path path;

    /**
     * Constructs a {@code bot.service.FileService} instance to operate on a file at the specified path.
     * The file does not need to exist when this object is created.
     *
     * @param path The string representation of the file path.
     */
    public FileService(String path) {
        this.path = Paths.get(path);
    }

    /**
     * Writes a list of tasks to the file specified during construction.
     * <p>
     * The method first ensures that the file and its parent directories exist.
     * It then iterates through the provided task list, converts each task into
     * a string representation suitable for file storage, and writes the combined
     * string to the file, overwriting its contents.
     *
     * @param taskList The list of {@code bot.task.Task} objects to be written to the file.
     * @throws IOException If an I/O error occurs during file or directory creation,
     *                     or during the writing process.
     */
    public void writeToFile(TaskList taskList) throws IOException {
        // Check if file exist. Create path + file if it doesn't exist
        ensureFileExists();

        // Format task list into String for writing to file
        StringBuilder data = new StringBuilder();
        for (Task task : taskList.getTaskList()) {
            data.append(task.toFileString());
        }

        // Write to data to file
        Files.writeString(path, data.toString());
    }

    /**
     * Reads tasks from the file specified and returns them
     * as a list of bot.task.Task objects.
     * <p>
     * The method uses {@code Files.readAllLines()} to read all lines from the file.
     * It then iterates through the list of strings, converting each line back into
     * a bot.task.Task object
     *
     * @return A list of {@code bot.task.Task} objects read from the file.
     * @throws IOException If an I/O error occurs during the file reading process.
     */
    public List<Task> readFromFile() throws IOException, IllegalArgumentException {
        // Check if file exist. Create path + file if it doesn't exist
        ensureFileExists();

        // Read all lines from the file
        List<String> lines = Files.readAllLines(path);

        // Process each line and create bot.task.Task objects
        return lines.stream().map(Task::createTaskFromFileString).toList();
    }

    /**
     * Ensures that the file specified by the {@code path} and its parent directories exist.
     * If the file does not exist, it creates the necessary parent directories and then creates
     * an empty file.
     *
     * @throws IOException If an I/O error occurs while creating directories or the file.
     */
    private void ensureFileExists() throws IOException {
        if (Files.exists(path)) {
            // File exist, do nothing
            return;
        }

        // Create parent folder directories if it doesn't exist
        Files.createDirectories(path.getParent());

        // Create an empty file
        Files.createFile(path);
    }
}

