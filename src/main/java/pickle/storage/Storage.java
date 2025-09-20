package pickle.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import pickle.task.Deadline;
import pickle.task.Event;
import pickle.task.Fixed;
import pickle.task.Task;
import pickle.task.ToDos;



/**
 * Saves the list of tasks into a text file.
 * Loads the list of tasks by an existing text file to keep track of tasks that the user has to do.
 */
public class Storage {

    private static final String SEP_REGEX = "\\s*\\|\\s*";
    private static final String TYPE_TODO = "T";
    private static final String TYPE_DEADLINE = "D";
    private static final String TYPE_EVENT = "E";
    private static final String TYPE_FIXED = "F";
    private final Path fileLocation;

    /**
     * Creates and instance of the storage.
     * @param filePath the file path used to store the list of tasks.
     */
    public Storage(String filePath) {
        this.fileLocation = Paths.get(filePath);

    }

    /**
     * Saves the given tasks list into the disk via a text file.
     * @param list tasks to save.
     * @throws IOException if the saving fails.
     */
    public void save(ArrayList<Task> list) throws IOException {
        Files.createDirectories(Paths.get("data"));

        List<String> lists = new ArrayList<>();

        for (Task t : list) {
            String line = t.writeToFile();
            lists.add(line);
        }

        Files.write(fileLocation, lists, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

    }
    /**
     * Loads tasks from disk as an ArrayList.
     * @return List of tasks.
     * @throws IOException Exception raised.
     */
    public ArrayList<Task> load() throws IOException {
        ensureFileExists();
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : Files.readAllLines(fileLocation)) {
            Task t = parseTask(line);
            if (t != null) {
                tasks.add(t);
            }
        }
        return tasks;
    }
    /**
     * Ensure that the file being read exists, if not throw exception.
     * @throws IOException thrown when file does not exist.
     */
    private void ensureFileExists() throws IOException {
        Files.createDirectories(fileLocation.getParent());
        if (Files.notExists(fileLocation)) {
            Files.write(fileLocation, java.util.List.of(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }


    /**
     * Parse the tasks that is mentioned in the input.
     * @param line Input of what the task is.
     * @return Task that has been created and add into the task list.
     */
    private Task parseTask(String line) {
        if (line == null || line.isBlank()) {
            return null;
        }

        String[] parts = line.split(SEP_REGEX);
        if (parts.length < 3) {
            return null; // need: type | done | description
        }

        boolean done = "1".equals(parts[1]);
        Task t = buildTask(parts);
        if (t == null) {
            return null;
        }

        if (done) {
            t.mark();
        }
        return t;
    }

    /**
     * Builds a Task from split parts. Returns null if malformed/unknown.
     * Formats:
     *  T | 0/1 | desc
     *  D | 0/1 | desc | by
     *  E | 0/1 | desc | at
     *  F | 0/1 | desc | when
     * @param p Take an array of string that has been split to properly create the tasks.
     * @return A task that has been created.
     */
    private Task buildTask(String[] p) {
        switch (p[0]) {
        case TYPE_TODO:
            return new ToDos(p[2]);
        case TYPE_DEADLINE:
            if (p.length < 4) {
                return null;
            }
            return new Deadline(p[2], p[3]);
        case TYPE_EVENT:
            if (p.length < 4) {
                return null;
            }
            return new Event(p[2], p[3], p[4]);
        case TYPE_FIXED:
            if (p.length < 4) {
                return null;
            }
            return new Fixed(p[2], p[3]);
        default:
            return null;
        }
    }

}
