package seedu.haru;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles loading and saving of Task objects to a file.
 */
public class Storage {
    private Path filePath;

    /**
     * Constructs a Storage object with the given file path.
     * Creates the parent directory and file if they do not exist.
     *
     * @param filePathStr the path of the file to store tasks in
     */
    public Storage(String filePathStr) {
        this.filePath = Paths.get(filePathStr);
        try {
            File file = filePath.toFile();
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error initializing storage: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file into memory.
     * Each line in the file is converted into a Task.
     *
     * @return a list of tasks read from the file; returns an empty list if an error occurs
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(filePath.toFile())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Task.fromFileString(line); // implement in Haru.Task class
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     * Each task is written in the format produced by Task.toFileString().
     *
     * @param tasks the list of tasks to save
     */
    public void saveTasks(List<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
