package yang.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import yang.task.Task;

/**
 * Handles loading and saving tasks to a file on disk.
 */
public final class Storage {
    private final Path file;

    /**
     * Creates a storage object for the given file path.
     *
     * @param file the file path to store tasks
     */
    public Storage(Path file) {
        this.file = file;
    }

    /**
     * Creates a storage object with the default path {@code data/yang.txt}.
     *
     * @return a storage object pointing to the default path
     */
    public static Storage defaultStorage() {
        return new Storage(Paths.get("data", "yang.txt"));
    }

    /**
     * Loads tasks from the backing file.
     * Creates the file if it does not exist.
     *
     * @return list of tasks loaded from storage
     * @throws IOException if an I/O error occurs
     */
    public List<Task> load() throws IOException {
        assert file != null : "storage path can only be set";
        if (Files.notExists(file)) {
            Files.createDirectories(file.getParent());
            Files.createFile(file);
            return new ArrayList<>();
        }

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        List<Task> out = new ArrayList<>();
        for (String ln : lines) {
            String s = ln.trim();
            if (!s.isEmpty()) {
                Task t = Task.fromStorage(s);
                if (t != null) {
                    out.add(t);
                }
            }
        }
        return out;
    }

    /**
     * Saves the given tasks to the backing file.
     *
     * @param tasks list of tasks to save
     * @throws IOException if an I/O error occurs
     */
    public void save(List<Task> tasks) throws IOException {
        assert tasks != null : "tasks to save must not be null";
        Files.createDirectories(file.getParent());
        List<String> lines = new ArrayList<>(tasks.size());
        for (Task t : tasks) {
            lines.add(t.toStorage());
        }
        Files.write(file, lines, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
