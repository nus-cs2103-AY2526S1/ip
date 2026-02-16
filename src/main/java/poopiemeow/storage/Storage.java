package poopiemeow.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import poopiemeow.task.Deadline;
import poopiemeow.task.Event;
import poopiemeow.task.Task;
import poopiemeow.task.Todo;

/**
 * Manages the persistence of tasks to and from files in the PoopieMeow application.
 * This class handles saving tasks to disk and loading them back into memory,
 * providing a simple file-based storage solution.
 * @author tch1001
 * @version 1.0
 */
public class Storage {
    /** The file path where tasks will be stored and loaded from */
    private final Path filePath;

    /**
     * Constructs a new Storage instance with the specified file path.
     * The file path is converted to a Path object for easier manipulation.
     *
     * @param filePath the string representation of the file path
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Saves the current list of tasks to the storage file.
     * This method creates the parent directories if they don't exist and
     * writes each task in its file format representation.
     *
     * <p>Each task is written on a separate line using the format defined
     * by the task's {@link poopiemeow.task.Task#toFileString()} method.</p>
     *
     * @param tasks the list of tasks to save
     * @throws IOException if there's an error writing to the file or creating directories
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(filePath.getParent());
        FileWriter writer = new FileWriter(filePath.toFile());
        for (Task task : tasks) {
            writer.write(task.toFileString() + "\n");
        }
        writer.close();
    }

    /**
     * Loads tasks from the storage file into memory.
     * This method reads the file line by line, parses each line according to
     * the task format, and creates the appropriate Task objects.
     *
     * <p>If the file doesn't exist, an empty list is returned. If the file
     * contains corrupted or malformed data, those lines are skipped and
     * only valid tasks are loaded.</p>
     *
     * <p>The method handles the three task types:</p>
     * <ul>
     *   <li><strong>T</strong> - Creates a Todo task</li>
     *   <li><strong>D</strong> - Creates a Deadline task</li>
     *   <li><strong>E</strong> - Creates an Event task</li>
     * </ul>
     *
     * @return an ArrayList containing all successfully loaded tasks
     * @throws IOException if there's an error reading from the file
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            return tasks;
        }

        Scanner fileScanner = new Scanner(new File(filePath.toString()));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            try {
                String[] parts = line.split("\\|");
                if (parts.length < 3) continue;

                Task task = null;
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        if (parts.length < 4) continue;
                        task = new Deadline(description, parts[3]);
                        break;
                    case "E":
                        if (parts.length < 5) continue;
                        task = new Event(description, parts[3], parts[4]);
                        break;
                }

                if (task != null) {
                    if (isDone) task.markAsDone();
                    tasks.add(task);
                }
            } catch (Exception e) {
                continue;
            }
        }
        fileScanner.close();
        return tasks;
    }
}
