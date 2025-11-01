package nina;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import nina.task.Task;
import nina.task.TaskList;

/**
 * Handles saving and loading of tasks to and from a storage file.
 */
public class Storage {
    private final Path filePath;
    private final Path dirPath;

    /**
     * Creates a Storage object given a file path string.
     * The directory path is inferred from the given file path.
     *
     * @param path the file path string where tasks will be saved
     */
    public Storage(String path) {
        this.filePath = Paths.get(path).normalize();
        this.dirPath = filePath.getParent() == null ? Paths.get(".") : filePath.getParent();
    }

    /**
     * Ensures that the storage file and its directory exist.
     * Creates them if they do not exist.
     *
     * @throws IOException if the directory or file cannot be created
     */
    private void ensureFile() throws IOException {
        if (Files.notExists(dirPath)) {
            Files.createDirectories(dirPath);
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
        assert !Files.notExists(dirPath) : "dirPath does not exist for data storage";
        assert !Files.notExists(filePath) : "filePath does not exist for data storage";
    }

    /**
     * Reads tasks from the storage file into a TaskList.
     *
     * @return a TaskList containing tasks in the file
     */
    public TaskList read() {
        TaskList list = new TaskList();
        try {
            ensureFile();
            List<String> lines = Files.readAllLines(filePath);
            for (String raw : lines) {
                String line = raw.trim();
                if (!line.isEmpty()) {
                    list.addTask(Task.fromSaveLine(line));
                }
            }

        } catch (IOException e) {
            System.err.println("[nina.Storage] read error: " + e.getMessage());
        }
        return list;
    }

    /**
     * Writes the given TaskList to the storage file.
     * Each task is serialized into a save line and written to the file.
     *
     * @param tasksToStore the TaskList to be saved
     */
    public void write(TaskList tasksToStore) {
        try {
            ensureFile();
            try (BufferedWriter w = new BufferedWriter(new FileWriter(filePath.toFile()))) {
                for (Task t : tasksToStore.items()) {
                    w.write(t.toSaveableLine());
                    w.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("[nina.Storage] write error: " + e.getMessage());
        }
    }
}
