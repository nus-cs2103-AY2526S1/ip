package cookie.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import cookie.exception.CookieException;
import cookie.task.Task;
import cookie.task.TaskList;

/**
 * Manages the saving and loading of tasks to and from the text file respectively.
 */
public class Storage {

    /** File path to text file where the list data is stored */
    private final Path filePath;

    /**
     * Creates new instance of Storage with specified file path.
     *
     * @param filePath Path to specified text file where data is to be stored.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Converts each task in the list to the save format.
     * Writes this new list of tasks in saved format to text file.
     *
     * @param listOfTasks List of tasks to save to specified text file.
     */
    public void save(TaskList listOfTasks) {
        ArrayList<String> listOfTasksInStoredFormat = new ArrayList<>();

        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            listOfTasksInStoredFormat.add(task.toStoredFormat());
        }

        try {
            Files.write(filePath, listOfTasksInStoredFormat);
        } catch (IOException e) {
            System.out.println("Encountered an error when attempting to save the file.");
        }
    }

    /**
     * Loads list of tasks from saved file.
     * Converts each task in the loaded list to the original format.
     * Creates new directory and file if file is not present.
     *
     * @return List of tasks loaded from specified text file.
     * @throws CookieException If there is an error converting task from stored format to original format.
     */
    public ArrayList<Task> load() throws CookieException {
        ArrayList<Task> listOfTasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                return listOfTasks;
            } catch (IOException e) {
                System.out.println("Error in creating file.");
            }
        }

        try {
            List<String> listOfTasksInStoredFormat = Files.readAllLines(filePath);
            for (String taskInStoredFormat : listOfTasksInStoredFormat) {
                if (taskInStoredFormat == null || taskInStoredFormat.isBlank()) {
                    continue;
                }
                Task task = Task.toOriginalFormat(taskInStoredFormat);
                listOfTasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error in reading from file.");
        }
        return listOfTasks;
    }
}
