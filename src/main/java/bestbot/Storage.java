package bestbot;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import bestbot.task.Task;
import bestbot.exception.BestbotException;

/**
 * Handles saving and loading tasks from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object bound to the specified file.
     *
     * @param filePath The path to the storage file. Must not be null or empty.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isBlank() : "File path must not be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return List of tasks loaded from file.
     * @throws BestbotException If file cannot be read or parsed.
     */
    public List<Task> load() throws BestbotException {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                assert !line.isBlank() : "Save file contains an empty line";
                tasks.add(Task.fromSaveFormat(line));
            }
        } catch (IOException e) {
            throw new BestbotException("Error loading tasks from file.");
        }
        return tasks;
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks List of tasks to save. Must not be null.
     */
    public void save(List<Task> tasks) {
        assert tasks != null : "Tasks list must not be null";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                assert task != null : "Task in list must not be null";
                bw.write(task.toSaveFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
