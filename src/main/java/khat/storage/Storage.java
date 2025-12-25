package khat.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import khat.Parser;
import khat.task.Task;
import khat.task.TaskList;

/**
 * Handles loading and saving tasks to the hard disk.
 */
public class Storage {

    private final File khatTasks;

    /**
     * Constructs a Storage object with the given file path.
     * Creates the parent directory if it does not exist.
     *
     * @param path The file path for storing tasks.
     */
    public Storage(String path) {
        this.khatTasks = new File(path);
        File parentDir = khatTasks.getParentFile();
        if (!parentDir.exists()) {
            boolean created = parentDir.mkdirs();
            if (!created) {
                System.out.println("Error creating directory for tasks!");
            }
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws FileNotFoundException If the file does not exist.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!khatTasks.exists()) {
            return tasks;
        }
        try (Scanner scanner = new Scanner(khatTasks)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTask(line);
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Saves the given task list to the file.
     *
     * @param tasks The task list to save.
     */
    public void saveTasks(TaskList tasks) {
        try (FileWriter writer = new FileWriter(khatTasks)) {
            for (Task task : tasks.getAllTasks()) {
                writer.write(task.toSaveString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
