package aries.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import aries.task.TaskList;

/**
 * Manages the loading and saving of tasks to a file, using serialization.
 */
public class Storage {
    private File file;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the file where tasks will be stored
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the file. If the file does not exist or an error occurs,
     * returns an empty TaskList.
     *
     * @return the loaded TaskList
     */
    public TaskList loadTaskList() {
        if (!file.exists()) {
            return new TaskList();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (TaskList) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Aries is not able to load now:) " + e.getMessage());
            return new TaskList();
        }
    }

    /**
     * Saves the given TaskList to the file.
     *
     * @param tasks the TaskList to save
     */
    public void saveTaskList(TaskList tasks) {
        assert tasks != null : "tasks to save cannot be null";
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            throw new RuntimeException("Error saving tasks: " + e.getMessage());
        }
    }
}
