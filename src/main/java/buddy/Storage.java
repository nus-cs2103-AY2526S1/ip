package buddy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private static final String TASK_DELIMITER = " | ";
    private static final String DONE_MARKER = "1";
    private static final String NOT_DONE_MARKER = "0";
    
    private String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    public ArrayList<Task> load() throws BuddyException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Path dataPath = Paths.get(filePath);
            if (!Files.exists(dataPath)) {
                Path dirPath = dataPath.getParent();
                if (dirPath != null && !Files.exists(dirPath)) {
                    Files.createDirectories(dirPath);
                }
                return tasks;
            }
            
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(dataPath));
            for (String line : lines) {
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new BuddyException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
    
    public void save(ArrayList<Task> tasks) throws BuddyException {
        try {
            Path dataPath = Paths.get(filePath);
            Path dirPath = dataPath.getParent();
            if (dirPath != null && !Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            
            ArrayList<String> lines = new ArrayList<>();
            for (Task task : tasks) {
                lines.add(formatTaskForFile(task));
            }
            Files.write(dataPath, lines);
        } catch (IOException e) {
            throw new BuddyException("Error saving tasks: " + e.getMessage());
        }
    }
    
    private String formatTaskForFile(Task task) {
        String isDone = task.isDone() ? DONE_MARKER : NOT_DONE_MARKER;
        if (task instanceof Todo) {
            return "T" + TASK_DELIMITER + isDone + TASK_DELIMITER + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D" + TASK_DELIMITER + isDone + TASK_DELIMITER + deadline.getDescription() 
                   + TASK_DELIMITER + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E" + TASK_DELIMITER + isDone + TASK_DELIMITER + event.getDescription() 
                   + TASK_DELIMITER + event.getFrom() + TASK_DELIMITER + event.getTo();
        }
        return "";
    }
    
    private Task parseTaskFromFile(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) return null;
            
            String type = parts[0];
            boolean isDone = parts[1].equals(DONE_MARKER);
            String description = parts[2];
            
            Task task = null;
            if (type.equals("T")) {
                task = new Todo(description);
            } else if (type.equals("D") && parts.length >= 4) {
                String by = parts[3];
                task = new Deadline(description, by);
            } else if (type.equals("E") && parts.length >= 5) {
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
            }
            
            if (task != null && isDone) {
                task.markAsDone();
            }
            return task;
        } catch (Exception e) {
            return null;
        }
    }
}