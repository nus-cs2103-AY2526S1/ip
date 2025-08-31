package piper.storage;

import piper.PiperException;
import piper.task.TaskList;
import piper.task.Task;
import piper.task.Todo;
import piper.task.Deadline;
import piper.task.Event;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles storage of tasks from a UTF-8 text file.
 * Each task is stored on a single line.
 * The storage path is provided via the constructor.
 * On first use, the parent directory and file are created if they do not already exist.
 */
public class Storage {
    /** Absolute or relative path to the save file. */
    private final Path savePath;

    /**
     * Constructs a Storage for the save file located under savePath.
     *
     * @param dir the directory that contains the save file, created if missing
     * @param fileName the save file name
     * @throws PiperException if the provided path cannot be constructed
     */
    public Storage(String dir, String fileName) throws PiperException {
        this.savePath = Paths.get(dir, fileName);
    }

    /**
     * Loads tasks from the save file into a TaskList.
     * If the directory or file does not exist, then they are created and an empty Tasklist is returned.
     *
     * @return a TaskList populated with tasks read from disk
     * @throws PiperException if the file cannot be created or read
     */
    public TaskList load() throws PiperException {
        TaskList tasks = new TaskList();
        try {
            Path dir = savePath.getParent();
            if (dir != null && !Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            if (!Files.exists(savePath)) {
                Files.createFile(savePath);
            }

            List<String> lines = Files.readAllLines(savePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line == null || line.isEmpty()) {
                    continue;
                }
                String[] substrings = line.split(" \\| ", 5);
                String taskType = substrings[0];
                String doneField = substrings[1];
                String description = substrings[2];
                boolean isDone = "1".equals(doneField);

                Task task = null;
                switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    task = new Deadline(description, substrings[3]);
                    break;
                case "E":
                    task = new Event(description, substrings[3], substrings[4]);
                    break;
                default:
                    throw new PiperException("Unknown task type in storage: " + taskType);
                }

                if (isDone) {
                    task.markDone();
                }
                tasks.addTask(task);
            }
        } catch (IOException e) {
            throw new PiperException("SQUAWK! Can't seem to read the save file at " + savePath + ": " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Converts a Task into its on-disk line representation.
     *
     * @param task the task to serialize
     * @return the serialized single-line representation of the task
     */
    private static String serialize(Task task) {
        return task.toSerializedLine();
    }

    /**
     * Writes all tasks to the save file, overwriting any existing content.
     *
     * @param tasks the tasks to save
     * @throws PiperException if an I/O error prevents writing the file
     */
    public void saveAll(TaskList tasks) throws PiperException {
        try {
            List<String> out = new ArrayList<>();
            for (int i = 0; i < tasks.getSize(); i++) {
                out.add(serialize(tasks.getTask(i)));
            }
            Files.write(savePath, out, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PiperException("SQUAWK! Can't seem to write tasks to " + savePath + ": " + e.getMessage());
        }
    }
}
