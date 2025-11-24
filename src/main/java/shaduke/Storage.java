package shaduke;

import shaduke.tasks.Task;
import shaduke.tasks.TaskList;
import shaduke.tasks.TaskLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving task data from and to a specified file path
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with the given file path
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks from the storage file
     *
     * @return a list of tasks read from the file
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            tasks.add(TaskLoader.load(line));
        }
        scanner.close();
        return tasks;
    }

    /**
     * Saves the current list of tasks
     *
     * @param tasks list of tasks to be saved
     * @throws IOException
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks.getTasks()) {
            writer.write(task.store() + System.lineSeparator());
        }
        writer.close();
    }
}
