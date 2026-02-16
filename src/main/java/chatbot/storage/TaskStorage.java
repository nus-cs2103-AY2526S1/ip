package chatbot.storage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import chatbot.task.Task;

/**
 * TaskStorage handles storage of tasks for B33PBOP.
 * On first usage, it creates a new DIRECTORY with a file tasks.txt which stores tasks in String format.
 * Subsequent usage overwrites the existing file to update TaskStorage.
 */
public class TaskStorage {
    private final File storageFile;

    /**
     * Initializes DIRECTORY and storageFile variables.
     * Creates a new directory and file if they do not exist.
     *
     * @throws IOException If storage creation fails.
     */
    public TaskStorage() throws IOException {
        File directory = new File("data");
        directory.mkdirs();

        storageFile = new File(directory, "tasks.txt");
        if (!storageFile.exists() && !storageFile.createNewFile()) {
            throw new IOException("Failed to create storage file");
        }
    }

    public File getStorageFile() {
        return this.storageFile;
    }

    /**
     * Updates tasks.txt file with a new task list.
     *
     * @param tasks List of tasks added by the user.
     * @throws IOException If update fails.
     */
    public void updateStorage(List<Task> tasks) throws IOException {
        try (PrintWriter pw = new PrintWriter(this.storageFile)) {
            for (Task t : tasks) {
                pw.println(t.toSaveFormat());
            }
        }
    }
}
