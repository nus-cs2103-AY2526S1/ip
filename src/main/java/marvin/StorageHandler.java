package marvin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import marvin.task.TaskList;

/**
 * Manages loading/saving a given TaskList to the disk.
 * Serializes the TaskList object.
 */
public class StorageHandler {
    private static final String FILE_PATH = "./save.mrvn";

    /**
     * Loads a task list from a static predefined save location.
     * Simply creates a new task list if IO errors were encountered
     *
     * @return A TaskList object representing the loaded file
     */
    public static TaskList loadTaskList() {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (TaskList) ois.readObject();
        } catch (FileNotFoundException e) {
            return new TaskList();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("(failed to load data - resetting)");
            return new TaskList();
        }
    }

    /**
     * Stores a task list into the predefined save location.
     * Prints a warning if the Task List failed to save.
     *
     * @param taskList The Task List to save.
     */
    public static void storeTaskList(TaskList taskList) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
            assert (new java.io.File(FILE_PATH).exists()) : "Save File should exist after writing to disk.";
        } catch (IOException e) {
            System.out.println("(failed to save data - data will not persist.)");
        }
    }
}
