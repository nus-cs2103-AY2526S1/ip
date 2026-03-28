package bugsbunny.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;

/**
 * Handles persistence of tasks to/from a plain text file.
 * <p>
 * Creates missing directories/files on first run and skips corrupted lines on load.
 */
public class Storage {
    private Path filePath;
    private String filePathString;

    /**
     * @param filePath Relative path to the save file, i.e. {@code data/tasks.txt}.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        this.filePathString = filePath;
    }

    /**
     * Loads tasks from disk. Creates the file if it does not exist.
     *
     * @return A {@link TaskList} populated from disk (possibly empty).
     * @throws IOException if I/O fails while accessing the file system.
     */
    public TaskList load() throws IOException {
        // Check if file exists
        if (Files.notExists(this.filePath)) {
            Path parent = this.filePath.getParent();

            if (parent != null) {
                // Creates directory if not present, else do nothing
                Files.createDirectories(parent);
            }

            Files.createFile(this.filePath);
            return new TaskList();
        }

        // file exists already
        Scanner scanner = new Scanner(this.filePath);
        ArrayList<Task> list = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isBlank()) {
                continue;
            }

            try {
                Task task = Task.convertFromStorageFormat(line);
                list.add(task);
            } catch (Exception e) {
                System.out.println("Skipping bad line: " + e.getMessage());
            }
        }
        return new TaskList(list);
    }

    /**
     * Persists the given task list to disk, overwriting the existing file.
     *
     * @param taskList Tasks to write.
     * @throws IOException If writing fails.
     */
    public void save(TaskList taskList) throws IOException {
        assert taskList != null : "TaskList to save should not be null";
        FileWriter fw = new FileWriter(this.filePathString);
        ArrayList<Task> list = taskList.getList();

        for (Task task : list) {
            fw.write(task.convertToStorageFormat());
            fw.write(System.lineSeparator());
        }

        fw.close();
    }
}
