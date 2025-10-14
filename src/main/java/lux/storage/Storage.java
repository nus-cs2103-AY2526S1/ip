package lux.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import lux.data.AliasList;
import lux.data.Task;
import lux.data.TaskList;
import lux.exception.LuxException;

/**
 * Responsible for persisting and restoring application data to disk.
 *
 * <p>Storage serializes and deserializes task lists and alias mappings to the
 * file system. The constructor accepts a directory-like path and appends
 * filenames for tasks and aliases.
 */
public class Storage {
    private String taskPath;
    private String aliasPath;

    /**
     * Create a Storage instance that reads/writes files under the provided
     * base path. The implementation appends fixed filenames for tasks and
     * aliases.
     *
     * @param filePath base folder path (e.g. "data/") where serialized
     *                 files will be stored
     */
    public Storage(String filePath) {
        this.taskPath = filePath + "tasks.ser";
        this.aliasPath = filePath + "aliases.ser";
    }

    /**
     * Load tasks from disk. If the tasks file does not exist an empty list is
     * returned.
     *
     * @return an ArrayList of deserialized tasks (never null)
     * @throws LuxException when deserialization fails
     */
    public ArrayList<Task> loadTasks() throws LuxException {
        File f = new File(taskPath);
        if (!f.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fileIn = new FileInputStream(taskPath);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            @SuppressWarnings("unchecked")
            ArrayList<Task> loadedTasks = (ArrayList<Task>) in.readObject();
            return loadedTasks;
        } catch (IOException | ClassNotFoundException c) {
            throw new LuxException("Failed to load tasks from disk: " + c.getMessage());
        }
    }

    /**
     * Serialize the provided {@link TaskList} to disk. The method creates the
     * parent directory if necessary.
     *
     * @param tasks task list to persist
     * @throws LuxException when an IO error occurs while saving
     */
    public void saveTasks(TaskList tasks) throws LuxException {
        File f = new File(taskPath);
        f.getParentFile().mkdirs();

        try (FileOutputStream fileOut = new FileOutputStream(taskPath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(tasks.getTasks());
        } catch (IOException i) {
            i.printStackTrace();
            throw new LuxException("Failed to save tasks to disk.");
        }
    }

    /**
     * Load custom command aliases from disk. Returns an empty {@link
     * AliasList} if no alias file is present.
     *
     * @return the loaded AliasList (never null)
     * @throws LuxException when deserialization fails
     */
    public AliasList loadAliases() throws LuxException {
        File f = new File(aliasPath);
        if (!f.exists()) {
            return new AliasList();
        }

        try (FileInputStream fileIn = new FileInputStream(aliasPath);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            AliasList aliases = (AliasList) in.readObject();
            return aliases;
        } catch (IOException | ClassNotFoundException c) {
            throw new LuxException("Failed to load aliases from disk: " + c.getMessage());
        }
    }

    /**
     * Persist the provided alias mappings to disk.
     *
     * @param aliases alias mapping to save
     * @throws LuxException when an IO error occurs
     */
    public void saveAliases(AliasList aliases) throws LuxException {
        File f = new File(aliasPath);
        f.getParentFile().mkdirs();

        try (FileOutputStream fileOut = new FileOutputStream(aliasPath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(aliases);
        } catch (IOException i) {
            i.printStackTrace();
            throw new LuxException("Failed to save aliases to disk.");
        }
    }
}
