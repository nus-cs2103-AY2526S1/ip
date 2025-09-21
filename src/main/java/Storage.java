package sofi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Handles file I/O operations for persisting task data.
 * Provides methods to load tasks from file and save tasks to file.
 */
public class Storage {
    private final String filePath;
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Storage instance with the specified file path.
     * 
     * @param filePath the path to the file for storing tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * 
     * @return ArrayList of loaded tasks
     * @throws IOException if there is an error reading the file
     */
    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs(); // Create data directory if it doesn't exist
        }
        if (!file.exists()) {
            file.createNewFile();
            return new ArrayList<>(); // Return empty list for new file
        }

        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s*\\|\\s*");
                if (parts.length < 3) {
                    continue; // Skip malformed lines
                }
                String type = parts[0];
                boolean isDone = "1".equals(parts[1]);
                String description = parts[2];
                Task task;
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
                    default:
                        continue;
                }
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     * 
     * @param tasks the list of tasks to save
     * @throws IOException if there is an error writing to the file
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(serialize(task));
                writer.newLine();
            }
        }
    }

    private String serialize(Task task) {
        if (task instanceof Todo) {
            String status = task.getStatusIcon().equals("[X]") ? "1" : "0";
            return "T | " + status + " | " + escape(task.description);
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            String status = task.getStatusIcon().equals("[X]") ? "1" : "0";
            return "D | " + status + " | " + escape(task.description) 
                    + " | " + escape(d.getBy().format(STORAGE_FORMAT));
        } else if (task instanceof Event) {
            Event e = (Event) task;
            String status = task.getStatusIcon().equals("[X]") ? "1" : "0";
            return "E | " + status + " | " + escape(task.description) 
                    + " | " + escape(e.getFrom().format(STORAGE_FORMAT)) 
                    + " | " + escape(e.getTo().format(STORAGE_FORMAT));
        }
        return "";
    }

    private String escape(String s) {
        return s.replace("\n", "\\n");
    }
}


