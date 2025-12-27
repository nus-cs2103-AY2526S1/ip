package fatty;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fatty.task.Task;
/**
 * Loads and saves tasklist from/into given filePath.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves file at filePath and loads its contents to create TaskList.
     * If file does not exist, creates a file at the filePath.
     *
     * @return TaskList loaded from filePath
     * @throws FattyException If there is error loading file.
     */
    public ArrayList<Task> loadTasks() throws FattyException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = Task.fromDataString(line);
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("⚠ Failed to load tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves taskList into file at filePath.
     *
     * @param tasks TaskList
     * @throws FattyException Error trying to save.
     */
    public void saveTasks(TaskList tasks) throws FattyException {
        File file = new File(filePath);

        try {
            file.getParentFile().mkdirs();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 1; i < tasks.size() + 1; i++) {
                    Task task = tasks.get(i);
                    writer.write(task.toDataString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("☹ OOPS! Failed to save tasks: " + e.getMessage());
        }
    }
}

