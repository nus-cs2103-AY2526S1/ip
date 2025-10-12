package simon.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import simon.parser.Parser;
import simon.task.Task;

/**
 * Handles loading and saving of tasks to a file on disk.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath Path to the file for saving/loading tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file in the filepath.
     * Each line is parsed into a Task object.
     *
     * @return ArrayList of Task objects loaded from the file. Empty list if file does not exist.
     * @throws IOException If an I/O error occurs when reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            return tasks;
        }
        Scanner fs = new Scanner(f);
        while (fs.hasNextLine()) {
            String line = fs.nextLine();
            Task task = Parser.parseTaskFromFile(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        fs.close();
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file.
     * Each task is saved as a line in the file using its own file string representation.
     *
     * @param tasks The list of Task objects to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        File dir = new File(filePath).getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(Parser.taskToFileString(task) + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
            throw e;
        }
    }
}