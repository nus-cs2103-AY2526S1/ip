package state;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import misc.PepeException;
import tasks.Task;

/**
 * Class in charge of file I/O.
 */
public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Deserialize from file path and return the list of tasks, or an empty list otherwise.
     * @return list of tasks stored at the specified file path
     */
    public List<Task> getTasks() throws PepeException {
        Path path = Paths.get(this.path);
        if (!Files.exists(path)) {
            createFile();
            return new ArrayList<>();
        }
        try {
            List<String> lines = Files.readAllLines(path);
            return Serderializer.deserializeTasks(lines);
        } catch (IOException | PepeException e) {
            System.out.println("Error while reading tasks from " + this.path + " : " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void createFile() throws PepeException {
        File file = new File(this.path);
        File dir = file.getParentFile();
        boolean hasCreatedDir = dir.mkdir();
        if (!hasCreatedDir) {
            throw new PepeException("Could not create directory " + dir.getAbsolutePath());
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new PepeException("Could not create file " + file.getAbsolutePath() + " because: " + e.getMessage());
        }
    }

    /**
     * Save the task list into the specified file path.
     * @param tasks The list of tasks to save from a Pepe session.
     * @throws PepeException if an error occurred while saving.
     */
    public void saveTasks(TaskList tasks) throws PepeException {
        Path path = Paths.get(this.path);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Task task : tasks) {
                writer.write(task.toFileInput());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new PepeException("Error while saving tasks in " + this.path + " : " + e.getMessage());
        }
    }
}
