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
        assert filePath != null : "File path cannot be null";
        assert !filePath.trim().isEmpty() : "File path cannot be empty";
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
            if (!parent.mkdirs()) {
                throw new IOException("Failed to create data directory: " + parent.getAbsolutePath());
            }
        }
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("Failed to create data file: " + file.getAbsolutePath());
            }
            return new ArrayList<>(); // Return empty list for new file
        }

        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) continue;
                
                try {
                    String[] parts = line.split("\\s*\\|\\s*");
                    if (parts.length < 3) {
                        System.err.println("Warning: Skipping malformed line " + lineNumber + ": " + line);
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
                        if (parts.length < 4) {
                            System.err.println("Warning: Skipping incomplete deadline on line " + lineNumber + ": " + line);
                            continue;
                        }
                        task = new Deadline(description, parts[3]);
                        break;
                    case "E":
                        if (parts.length < 5) {
                            System.err.println("Warning: Skipping incomplete event on line " + lineNumber + ": " + line);
                            continue;
                        }
                        task = new Event(description, parts[3], parts[4]);
                        break;
                    default:
                        System.err.println("Warning: Skipping unknown task type on line " + lineNumber + ": " + line);
                        continue;
                }
                if (isDone) {
                    task.markAsDone();
                }
                
                // Load tags if they exist (parts[5] and beyond)
                for (int i = 5; i < parts.length; i++) {
                    if (!parts[i].trim().isEmpty()) {
                        task.addTag(parts[i].trim());
                    }
                }
                    
                    tasks.add(task);
                } catch (Exception e) {
                    System.err.println("Warning: Error processing line " + lineNumber + ": " + line + " - " + e.getMessage());
                    continue; // Skip problematic lines but continue loading
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading data file: " + e.getMessage(), e);
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
        assert tasks != null : "Task list cannot be null";
        File file = new File(filePath);
        assert filePath != null : "File path should not be null";
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                assert task != null : "Task in list should not be null";
                writer.write(serialize(task));
                writer.newLine();
            }
        }
    }

    private String serialize(Task task) {
        assert task != null : "Task cannot be null";
        assert task.getDescription() != null : "Task description cannot be null";
        
        String baseLine;
        String status = task.getStatusIcon().equals("[X]") ? "1" : "0";
        
        if (task instanceof Todo) {
            baseLine = "T | " + status + " | " + escape(task.getDescription());
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            assert d.getBy() != null : "Deadline 'by' time cannot be null";
            baseLine = "D | " + status + " | " + escape(task.getDescription()) 
                    + " | " + escape(d.getBy().format(STORAGE_FORMAT));
        } else if (task instanceof Event) {
            Event e = (Event) task;
            assert e.getFrom() != null : "Event 'from' time cannot be null";
            assert e.getTo() != null : "Event 'to' time cannot be null";
            baseLine = "E | " + status + " | " + escape(task.getDescription()) 
                    + " | " + escape(e.getFrom().format(STORAGE_FORMAT)) 
                    + " | " + escape(e.getTo().format(STORAGE_FORMAT));
        } else {
            return "";
        }
        
        // Add tags to the serialized line
        StringBuilder result = new StringBuilder(baseLine);
        for (String tag : task.getTags()) {
            result.append(" | ").append(escape(tag));
        }
        
        return result.toString();
    }

    private String escape(String s) {
        return s.replace("\n", "\\n");
    }
}


