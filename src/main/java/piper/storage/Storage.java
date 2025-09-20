package piper.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import piper.PiperException;
import piper.task.Deadline;
import piper.task.Event;
import piper.task.Task;
import piper.task.TaskList;
import piper.task.Todo;

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
     * @param dir the directory that contains the save file, created if missing.
     * @param fileName the save file name.
     * @throws PiperException if the provided path cannot be constructed.
     */
    public Storage(String dir, String fileName) throws PiperException {
        this.savePath = Paths.get(dir, fileName);

        try {
            java.nio.file.Path directory  = savePath.getParent();
            if (directory != null) java.nio.file.Files.createDirectories(directory);
            if (!java.nio.file.Files.exists(savePath)) {
                java.nio.file.Files.createFile(savePath);
            }
            if (!java.nio.file.Files.isReadable(savePath)) {
                throw new PiperException("SQUAWK! I can't read from " + savePath + " (permission denied).");
            }
            if (!java.nio.file.Files.isWritable(savePath)) {
                throw new PiperException("SQUAWK! I can't write to " + savePath + " (permission denied).");
            }
        } catch (PiperException pe) {
            throw pe;
        } catch (Exception e) {
            throw new PiperException("SQUAWK! I can't access " + savePath + ": " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the save file into a TaskList.
     * If the directory or file does not exist, then they are created and an empty Tasklist is returned.
     *
     * @return a TaskList populated with tasks read from disk.
     */
    public TaskList load() throws PiperException {
        TaskList tasks = new TaskList();
        try {
            ensureSaveFileReady();

            List<String> lines = Files.readAllLines(savePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line == null || line.isEmpty()) continue;
                Task task = parseSerializedLine(line);
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
     * @param task the task to serialize.
     * @return the serialized single-line representation of the task.
     */
    private static String serialize(Task task) {
        return task.toSerializedLine();
    }

    /**
     * Writes all tasks to the save file, overwriting any existing content.
     *
     * @param tasks the tasks to save.
     * @throws PiperException if an I/O error prevents writing the file.
     */
    public void saveAll(TaskList tasks) throws PiperException {
        assert tasks != null : "Tasks should be non-null when saving";
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

    /**
     * Ensures that the save file and its parent directories exist and are ready for use.
     * If the directory or file does not exist, they are created.
     *
     * @throws IOException if the file or directories cannot be created.
     */
    private void ensureSaveFileReady() throws IOException {
        Path dir = savePath.getParent();
        if (dir != null && !Files.exists(dir)) {
            Files.createDirectories(dir);
        }
        if (!Files.exists(savePath)) {
            Files.createFile(savePath);
        }
    }

    /**
     * Parses a serialized line from the save file into a Task instance.
     *
     * @param line a single serialized line from the save file.
     * @return a reconstructed Task.
     * @throws PiperException if the task type is unrecognized or fields are missing.
     */
    private Task parseSerializedLine(String line) throws PiperException {
        // Keep your field split and assertions exactly as-is.
        String[] substrings = line.split(" \\| ", 5);
        assert substrings.length >= 3 : "Serialized line should have at least 3 fields";

        String taskType = substrings[0];
        String doneField = substrings[1];
        String description = substrings[2];
        boolean isDone = "1".equals(doneField);

        Task task;
        switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                assert substrings.length >= 4 : "Serialized Deadline line should have 4 fields";
                task = new Deadline(description, substrings[3]);
                break;
            case "E":
                assert substrings.length >= 5 : "Serialized Event line should have 5 fields";
                task = new Event(description, substrings[3], substrings[4]);
                break;
            default:
                throw new PiperException("Unknown task type in storage: " + taskType);
        }

        if (isDone) {
            task.markDone();
        }
        return task;
    }

}
