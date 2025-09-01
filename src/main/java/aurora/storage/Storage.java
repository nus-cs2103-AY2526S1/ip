package aurora.storage;

import aurora.task.InvalidTaskException;
import aurora.task.Task;
import aurora.task.TaskList;
import aurora.task.TaskReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;


/**
 * Handles storage of tasks on disk.
 * <p>
 * The {@code Storage} class saves the current {@link TaskList} to a text file
 * and loads when the program starts. Each {@link Task} is serialized
 * to text using {@link Task#toText()}, and deserialized using
 * {@link TaskReader#fromText(String)}.
 * </p>
 */
public class Storage {
    private final File file;

    /**
     * Creates a new storage handler for the given file path.
     *
     * @param filePath path to the file used for saving and loading tasks
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Saves the given task list to disk.
     *
     * @param list the task list to save
     * @throws RuntimeException if the file cannot be written
     */
    public void save(TaskList list) {
        try {
            File parentDir = file.getParentFile();

            if (!parentDir.exists() && !parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir);
            }

            try (FileWriter fw = new FileWriter(file)) {
                for (Task task : list.getTasks()) {
                    fw.write(task.toText());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads tasks from disk into a new {@link TaskList}.
     *
     * @return a task list containing all tasks loaded from disk,
     *         or an empty list if the file does not exist
     * @throws InvalidTaskException if the file contains invalid task data
     */
    public TaskList load() {
        TaskList result = new TaskList();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                result.add(TaskReader.fromText(s.nextLine()));
            }
        } catch (FileNotFoundException e) {
            return result;
        }

        return result;
    }
}
