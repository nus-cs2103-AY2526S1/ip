package luna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import luna.tasks.Deadline;
import luna.tasks.Event;
import luna.tasks.Task;
import luna.tasks.Todo;

/**
 * Handles reading from and writing to the task data file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file and returns them as a list.
     * If the file or its parent directory does not exist, they are created automatically.
     *
     * @return A list of tasks loaded from the file. Returns an empty list if the file is new.
     * @throws IOException If an I/O error occurs while reading from the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return taskList;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            taskList.add(parseTask(line));
        }
        br.close();
        return taskList;
    }

    /**
     * Parses a line from the storage file into a Task object.
     * This method identifies the task type and reconstructs
     * the appropriate subclass.
     *
     * @param line A line from the file representing a single task.
     * @return A Task object parsed from the line.
     * @throws IllegalArgumentException If the task type is unknown.
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");

        Task task;

        switch (type) {
        case "T":
            task = new Todo(parts[2], isDone);
            if (parts.length > 3) {
                addTagFromStorage(task, parts[3]);
            }
            break;
        case "D":
            task = new Deadline(parts[2], isDone, parts[3]);
            if (parts.length > 4) {
                addTagFromStorage(task, parts[4]);
            }
            break;
        case "E":
            task = new Event(parts[2], isDone, parts[3], parts[4]);
            if (parts.length > 5) {
                addTagFromStorage(task, parts[5]);
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown task type in file: " + type);
        }
        return task;
    }

    /**
     * Helper function to add tags from storage
     *
     * @param task Task that the tags are to be tagged to
     * @param tags Tags to be added to the specified task
     */

    public void addTagFromStorage(Task task, String tags) {
        String[] tagSplit = tags.split(" ");
        for (String tag : tagSplit) {
            task.addTag(tag.substring(1));
        }
    }


    /**
     * Saves the list of tasks to the storage file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks) {
            bw.write(task.toFileString());
            bw.newLine();
        }
        bw.close();
    }
}
