package angsoontong.storage;

import angsoontong.task.TaskList;
import angsoontong.task.*;
import angsoontong.TaskDecoder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    /**
     * constructor
     * @param filePath address of the storage file to read/write tasks
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isBlank() : "Storage filePath must not be empty";
        this.filePath = filePath;
    }

    /**
     * Load tasks from the save file.
     * Returns an ArrayList of tasks (decoded).
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            // create directories if they do not exist
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks; // return empty list if no file yet
        }

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            Task t = TaskDecoder.decode(line);
            if (t != null) {
                tasks.add(t);
            }
        }
        return tasks;
    }

    /**
     * Save all tasks to the save file.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getAll()) { // TaskList should expose getAll()
            fw.write(task.toFileFormat() + System.lineSeparator());
        }
        fw.close();
    }
}
