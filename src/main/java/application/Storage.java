package application;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import exception.RomidasException;
import tasks.Task;

/**
 * Handles file I/O operations for persisting and loading task data.
 * Provides methods to save tasks to a file and load tasks from a file.
 */
public class Storage {

    public Storage() {
    }

    /**
     * Loads tasks from a file at the specified path.
     * Creates an empty task list if the file doesn't exist.
     * Handles various I/O exceptions gracefully and returns an empty list on error.
     *
     * @param path The file path to load tasks from.
     * @return ArrayList of loaded tasks, or empty list if file doesn't exist or error occurs.
     */
    public ArrayList<Task> loadTasks(String path) {
        ArrayList<Task> tasks = new ArrayList<>();
        Path pathy = Paths.get(path);
        if (!Files.exists(pathy)) {
            return tasks;
        }
        try {
            ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(pathy);
            return TextTaskConverter.convertToTask(lines);
        } catch (FileNotFoundException e) {
            System.out.println("Could not retrieve tasks from file");
            return tasks;
        } catch (IOException | RomidasException e) {
            System.out.println(e.getMessage());
            return tasks;
        }
    }

    /**
     * Saves the current task list to a file at the specified path.
     * Writes each task in its text representation format to the file.
     * Creates the file if it doesn't exist, overwrites if it does.
     *
     * @param path The file path to save tasks to.
     * @param tasks The list of tasks to save.
     * @throws RomidasException If an I/O error occurs during file writing.
     */
    public void saveTasks(String path, ArrayList<Task> tasks) throws RomidasException {
        Path pathe = Paths.get(path);
        try (BufferedWriter writer = Files.newBufferedWriter(pathe)) {
            for (Task task : tasks) {
                writer.write(task.toText());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RomidasException("Error while saving tasks in file");
        }
    }
}
