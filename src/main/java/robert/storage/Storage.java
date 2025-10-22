package robert.storage;

import robert.task.Task;
import robert.task.TaskList;
import robert.task.Todo;
import robert.task.Deadline;
import robert.task.Event;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Handles loading and saving of tasks to a file for persistence.
 */
public class Storage {
    private final File file;
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Storage object for the given file path.
     *
     * @param filePath Path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the file.
     *
     * @return ArrayList of loaded tasks.
     * @throws IOException If an I/O error occurs.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            createFileIfNeeded();
        } else {
            loadTasksFromFile(tasks);
        }
        return tasks;
    }

    private void createFileIfNeeded() throws IOException {
        assert file.getParentFile() != null : "Parent directory should not be null";
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    private void loadTasksFromFile(ArrayList<Task> tasks) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                parseAndAddTask(line, tasks);
            }
        } catch (Exception e) {
            // Silent fail - return empty list on corruption
        }
    }

    private void parseAndAddTask(String line, ArrayList<Task> tasks) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return;
        }

        validateParts(parts);
        Task task = createTaskFromParts(parts);

        if (task != null) {
            tasks.add(task);
        }
    }

    private void validateParts(String[] parts) {
        assert parts[0] != null : "Task type should not be null";
        assert parts[1] != null : "Task status should not be null";
        assert parts[2] != null : "Task description should not be null";
    }

    private Task createTaskFromParts(String[] parts) {
        try {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task task = parseTaskByType(type, description, parts);

            if (task != null && isDone) {
                task.markAsDone();
            }
            return task;
        } catch (Exception e) {
            return null;
        }
    }

    private Task parseTaskByType(String type, String description, String[] parts) {
        switch (type) {
        case "T":
            return new Todo(description);
        case "D":
            return parseDeadlineTask(description, parts);
        case "E":
            return parseEventTask(description, parts);
        default:
            return null;
        }
    }

    private Task parseDeadlineTask(String description, String[] parts) {
        if (parts.length >= 4) {
            LocalDateTime by = LocalDateTime.parse(parts[3], STORAGE_FORMAT);
            return new Deadline(description, by);
        }
        return null;
    }

    private Task parseEventTask(String description, String[] parts) {
        if (parts.length >= 5) {
            LocalDateTime from = LocalDateTime.parse(parts[3], STORAGE_FORMAT);
            LocalDateTime to = LocalDateTime.parse(parts[4], STORAGE_FORMAT);
            return new Event(description, from, to);
        }
        return null;
    }

    /**
     * Saves the given TaskList to the file.
     *
     * @param taskList The TaskList to save.
     * @throws IOException If an I/O error occurs.
     */
    public void save(TaskList taskList) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                StringBuilder sb = new StringBuilder();
                
                if (task instanceof Todo) {
                    sb.append("T | ");
                    sb.append(task.getStatusIcon().equals("X") ? "1" : "0").append(" | ");
                    sb.append(task.getDescription());
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    sb.append("D | ");
                    sb.append(task.getStatusIcon().equals("X") ? "1" : "0").append(" | ");
                    sb.append(task.getDescription()).append(" | ");
                    sb.append(deadline.getBy().format(STORAGE_FORMAT));
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    sb.append("E | ");
                    sb.append(task.getStatusIcon().equals("X") ? "1" : "0").append(" | ");
                    sb.append(task.getDescription()).append(" | ");
                    sb.append(event.getFrom().format(STORAGE_FORMAT)).append(" | ");
                    sb.append(event.getTo().format(STORAGE_FORMAT));
                }
                bw.write(sb.toString());
                bw.newLine();
            }
        }
    }
}
