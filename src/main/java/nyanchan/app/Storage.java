package nyanchan.app;

import nyanchan.exceptions.NyanException;
import nyanchan.tasks.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving of task data through the {@code Save} utility.
 */
public class Storage {
    private final String filepath;

    /**
     * Creates a {@code Storage} object with the specified file path.
     *
     * @param filepath path to the save file
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the save file into memory.
     *
     * @return list of loaded tasks
     * @throws NyanException if file cannot be read or parsed
     */
    public List<Task> load() throws NyanException {
        List<Task> tasks = new ArrayList<>();
        try {
            Save.read(tasks);
        } catch (FileNotFoundException e) {
            throw new NyanException("Could not find save file at: " + filepath);
        } catch (Exception e) {
            throw new NyanException("Error while loading tasks.");
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks tasks to save
     * @throws NyanException if writing to file fails
     */
    public void save(List<Task> tasks) throws NyanException {
        try {
            Save.write(tasks);
        } catch (Exception e) {
            throw new NyanException("Error while saving tasks.");
        }
    }
}
