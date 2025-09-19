package jone;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles saving and loading of tasks to and from a file.
 */
public class Storage {
    private static final String FILE_ERROR = "Unable to access save file.";

    private final String filePath;

    /**
     * Creates a Storage object that manages saving/loading to the given file path.
     *
     * @param filePath The file path of the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the save file.
     *
     * @return A list of tasks loaded from the save file.
     * @throws JoneException If there is an error accessing the file.
     */
    public ArrayList<Task> load() throws JoneException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                assert line != null && !line.trim().isEmpty() : "Empty or null line in save file";
                tasks.add(Task.fromSaveFormat(line));
            }
        } catch (IOException e) {
            throw new JoneException(FILE_ERROR);
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the save file.
     *
     * @param tasks The tasks to save.
     * @throws JoneException If there is an error accessing the file.
     */
    public void save(ArrayList<Task> tasks) throws JoneException {
        assert tasks != null : "Task list cannot be null when saving";
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new JoneException(FILE_ERROR);
        }
    }
}
