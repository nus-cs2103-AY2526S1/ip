package nerpbot;

import nerpbot.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage class handles loading and saving of tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an I/O error occurs.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        // Better to take in ArrayList<nerpbot.task.Task> as a parameter than nerpbot.TaskList
        // to reduce coupling between classes
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : tasks) {
            fileWriter.write(task.saveFormat() + System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Loads tasks from the file.
     *
     * @return The list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs(); // create /data directory if it does not exist
            file.createNewFile(); // create nerpbot.txt file if it does not exist
            return tasks;
        }

        // read from file and populate tasks
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            tasks.add(Task.fromSaveFormat(line));
        }
        assert tasks != null : "Tasks should not be null";
        br.close();
        return tasks;
    }
}
