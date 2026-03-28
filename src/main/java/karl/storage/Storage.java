package karl.storage;

import karl.exception.KarlException;
import karl.task.Deadline;
import karl.task.Event;
import karl.task.Task;
import karl.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles loading and saving of task data to and from disk.
 * Responsible for all persistent storage functionality of the chatbot.
 */
public class Storage {
    private String filePath;
    private static final String FOLDER_PATH = "data";

    /**
     * Creates a Storage instance tied to a specific file.
     * @param filePath the file to store/load task data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into a list.
     * Skips malformed lines and returns an empty list if file absent.
     * @return list of tasks loaded from file
     * @throws KarlException if file read errors occur
     */
    public ArrayList<Task> loadTasks() throws KarlException {
        ArrayList<Task> tasks = new ArrayList<>();
        File dataFile = new File(filePath);

        if (!dataFile.exists()) {
            File folder = new File(FOLDER_PATH);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return tasks; // empty list if no file
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    System.out.println("Skipping malformed task line: " + line);
                    continue;
                }
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                try {
                    if (type.equals("T")) {
                        Task t = new Todo(parts[2]);
                        if (isDone) t.markAsDone();
                        tasks.add(t);
                    } else if (type.equals("D")) {
                        if (parts.length < 4) {
                            System.out.println("Skipping malformed Deadline task line: " + line);
                            continue;
                        }
                        Task t = new Deadline(parts[2], parts[3]);
                        if (isDone) t.markAsDone();
                        tasks.add(t);
                    } else if (type.equals("E")) {
                        if (parts.length < 5) {
                            System.out.println("Skipping malformed Event task line: " + line);
                            continue;
                        }
                        Task t = new Event(parts[2], parts[3], parts[4]);
                        if (isDone) t.markAsDone();
                        tasks.add(t);
                    } else {
                        System.out.println("Skipping unknown task type: " + line);
                    }
                } catch (Exception e) {
                    System.out.println("Skipping invalid task data: " + line);
                }
            }
        } catch (IOException e) {
            throw new KarlException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves an entire list of tasks to the storage file.
     * Creates required directories if they don't exist.
     * @param tasks the list of tasks to save
     * @throws KarlException if file write errors occur
     */
    public void saveTasks(ArrayList<Task> tasks) throws KarlException {
        File dataDir = new File(FOLDER_PATH);
        if (!dataDir.exists()) dataDir.mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                String line = "";
                if (t instanceof Todo) {
                    line = String.format("T | %d | %s", t.isDone() ? 1 : 0, t.getDescription());
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    line = String.format("D | %d | %s | %s", t.isDone() ? 1 : 0, t.getDescription(), d.getBy().toString());
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    line = String.format("E | %d | %s | %s | %s", t.isDone() ? 1 : 0, t.getDescription(), e.getFrom().toString(), e.getTo().toString());
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new KarlException("Error saving tasks: " + e.getMessage());
        }
    }
}
