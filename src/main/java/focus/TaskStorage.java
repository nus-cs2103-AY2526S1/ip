package focus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles loading tasks from a file and saving tasks to a file.
 */
public class TaskStorage {

    private final String filePath;

    private TaskParser taskParser = new TaskParser();

    /**
     * Constructs a TaskStorage object that reads from and writes to the given file path.
     *
     * @param filePath Relative path to the save file (e.g. data/Focus.txt).
     */
    public TaskStorage(String filePath) {
        ensureDataDirExists("./data");
        this.filePath = filePath;
    }

    /**
     * Saves the given tasks to the storage file.
     *
     * @param taskList Task list to store.
     * @throws IOException If an I/O error occurs while writing.
     */
    public void saveTasks(TaskList taskList) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.toStorageString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }

    }

    /**
     * Loads tasks from the storage file.
     * Missing files and folders are handled by returning an empty list.
     *
     * @return A TaskList containing the loaded tasks.
     * @throws IOException If an I/O error occurs while reading.
     */
    public TaskList loadTasks() throws IOException {

        TaskList taskList = new TaskList();
        File storedFiled = new File(filePath);

        if (!storedFiled.exists()) {
            return taskList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    taskList.addTask(TaskParser.parse(line), true);
                } catch (FocusException fe) {
                    System.err.println("Error loading tasks: " + fe.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return taskList;

    }

    public static void main(String[] args) {

        TaskStorage taskStorage = new TaskStorage("data/Focus.txt");
        TaskList taskList = null;

        try {
            taskList = taskStorage.loadTasks();
        } catch (IOException e) {
            System.out.println("Could not load task list!");
        }

    }

    /**
     * Ensures data directory will always exist during TaskStorage instantiation.
     */
    private void ensureDataDirExists(String directoryString) {
        File dataDir = new File(directoryString);
        if (!dataDir.exists()) {
            boolean created = dataDir.mkdirs();
            if (created) {
                System.out.println("Directory created successfully");
            } else {
                System.out.println("Failed to create directory");
            }
        } else {
            System.out.println("Directory already exists");
        }
    }

}
