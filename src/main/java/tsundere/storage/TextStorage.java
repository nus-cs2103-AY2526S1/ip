package tsundere.storage;

import static tsundere.storage.ValidateTask.validateTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tsundere.TsundereException;
import tsundere.task.Task;
import tsundere.task.TaskList;

/**
 * Handles the loading and storing of tasks.
 * Checks storage paths for existing storages. If it does not exist, attempts to create one.
 * Uses the alternate storage path if default fails to initialize.
 */
public class TextStorage extends AbstractStorage {
    private static final String DEFAULT_STORAGE_PATH = "./src/main/java/tsundere/storage/tsundereStorage.txt";
    private static final String ALT_STORAGE_PATH = "./tsundereStorage.txt";

    private String path;

    private TextStorage() {
    }

    /**
     * Sets the path to create or store task data.
     * @param path Relative path from the ip folder
     */
    private void initialize(String path) throws TsundereStorageFileException {
        try {
            File file = new File(path);
            if (file.exists()) {
                if (!(file.canRead() && file.canWrite())) {
                    throw new TsundereStorageFileException();
                }
            } else {
                if (!file.createNewFile()) {
                    throw new TsundereStorageFileException();
                }
            }
            this.path = path;
        } catch (IOException e) {
            throw new TsundereStorageFileException();
        }
    }

    /**
     * Factory method to try and create a new storage file
     * @return new TextStorage
     * @throws TsundereStorageFileException Thrown if none of the specified storage paths work
     */
    public static TextStorage of() throws TsundereStorageFileException {
        TextStorage storage = new TextStorage();
        try {
            storage.initialize(DEFAULT_STORAGE_PATH);
        } catch (TsundereStorageFileException e) {
            storage.initialize(ALT_STORAGE_PATH);
        }
        return storage;
    }

    /**
     * Saves the task list to storage.
     * @param tasks Tasks to be stored.
     */
    @Override
    public void storeAll(TaskList tasks) throws TsundereException {
        try {
            FileWriter fw = new FileWriter(path, false);
            StringBuilder sb = new StringBuilder();

            for (Task task : tasks) {
                sb.append(task.toStorageString());
                sb.append('\n');
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            throw new TsundereReadWriteException();
        }
    }
    /**
     * Fetches all the tasks from storage. Runs on initialization of application.
     *
     * @return new {@link TaskList}
     */
    @Override
    public TaskList retrieveAll() throws TsundereException {
        try {
            File file = new File(path);

            Scanner sc = new Scanner(file);
            TaskList tasks = new TaskList();

            while (sc.hasNext()) {
                String line = sc.nextLine();
                Task task = validateTask(line);
                tasks.add(task);
            }
            sc.close();
            return tasks;
        } catch (IOException e) {
            throw new TsundereReadWriteException();
        }
    }

    /**
     * Deletes all data stored in the specified storage. Used for testing.
     */
    @Override
    public void clear() {
        try {
            FileWriter fw = new FileWriter(path);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
