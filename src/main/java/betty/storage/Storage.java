package betty.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import betty.parser.Parser;
import betty.task.Task;
import betty.task.TaskList;

/**
 * Represents a storage object that stores the list of task in a local file
 * Provides operations to create, store and load file
 */
public class Storage {

    private final String filePath;
    private final File taskFile;

    /**
     * Construct a storage with the given file path for storage of task list
     * @param filePath path given to store file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskFile = this.getFile();
    }

    /**
     * Returns a file if path matches filePath provided, else create a new file in given path
     * @return file for storage of task list
     */
    public File getFile() {
        File myFile = new File(this.filePath);
        try {
            // Create directories if not present
            File parent = myFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            // If file does not exist, create a new file
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
        return myFile;
    }

    /**
     * Loads the given file into a task list
     * @return a list of task parsed and loaded from file
     */
    public List<Task> load() {
        List<Task> taskList = new ArrayList<>();
        // Use scanner to read file and parse each line to become a task object
        try {
            Scanner scanner = new Scanner(this.taskFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTask(line);
                taskList.add(task);
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        return taskList;
    }

    /**
     * Stores the given task list into the storage file
     * @param taskList taskList to be stored into file
     */
    public void store(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(taskFile);
            fw.write(taskList.toSaveString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
