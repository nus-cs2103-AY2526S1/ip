package sunday;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import task.Task;

/**
 * Handles persistence of tasks to disk. Creates missing folders/files on first run
 * and reads/writes tasks using a stable, line-based text format.
 */
public class Storage {

    private final Path dataDir;
    private final Path dataFile;

    public Storage(String relativeDataPath) {
        this.dataFile = Paths.get(
                System.getProperty("user.dir")).resolve(relativeDataPath);
        this.dataDir = dataFile.getParent();
    }

    private void ensureFileExist() throws IOException {
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
        if (!Files.exists(dataFile)) {
            Files.createFile(dataFile);
        }
    }

    /**
     * Reads tasks from disk.
     *
     * @return tasks parsed from the save file
     */
    public List<Task> load() {
        try {
            ensureFileExist();
            List<String> list = Files.readAllLines(dataFile);
            List<Task> allTask = new ArrayList<>();
            for (String lines : list) {
                String line = lines.trim();
                if (line.isEmpty()) {
                    continue;
                }
                allTask.add(Parser.parseTaskLine(line));
            }
            return allTask;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves tasks to disk.
     *
     * @param tasks tasks to persist
     */
    public void save(List<Task> tasks) {
        assert tasks != null : "No tasklist is given to save.";
        try {
            ensureFileExist();
            try (FileWriter fileWriter = new FileWriter(dataFile.toFile(), false)) {
                for (Task task : tasks) {
                    fileWriter.write(task.convertor());
                    fileWriter.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
