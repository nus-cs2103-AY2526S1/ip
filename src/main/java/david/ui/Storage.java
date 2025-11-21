package david.ui;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import david.exception.DavidException;
import david.exception.SaveException;
import david.task.Task;

/**
 * Deals with file storage issues.
 */
public class Storage {
    private final Path path;

    /**
     * @param p Path name of the file to be overwritten.
     */
    public Storage(String p) {
        this.path = Paths.get(p);
    }

    /**
     * Initialize the storage object.
     *
     * @throws IOException If initialization fails.
     */
    public void init() throws IOException {
        if (Files.notExists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
    }

    /**
     * Save the tasks to the data file.
     *
     * @param tasks The list of tasks.
     * @throws DavidException If saving tasks fails.
     */
    public void save(TaskList tasks) throws DavidException {
        try {
            ArrayList<Task> list = tasks.getList();
            FileWriter fw = new FileWriter(path.toFile(), false);
            for (Task t : list) {
                fw.write(t.serialize() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new SaveException("cannot save changes.");
        }
    }

    /**
     * Load tasks from the data file to an array list.
     *
     * @return An ArrayList of tasks.
     * @throws IOException If loading fails.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> list = new ArrayList<>();
        if (Files.notExists(path)) {
            return list; //empty list
        }
        BufferedReader br = Files.newBufferedReader(path);
        String line;
        while ((line = br.readLine()) != null) {
            loadTasks(list, line);
        }
        return list;
    }

    private static void loadTasks(ArrayList<Task> list, String line) {
        try {
            Task t = Task.create(line);
            list.add(t);
        } catch (DavidException e) {
            System.out.println(Formatter.format("Corrupted input: " + e.getMessage()));
        }
    }
}
