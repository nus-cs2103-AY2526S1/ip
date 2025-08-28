import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            createFileAndParent();
            return tasks;
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        createFileAndParent();
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(formatTask(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    private void createFileAndParent() {
        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file/folder: " + e.getMessage());
        }
    }

    private Task parseTask(String line) {
        // Example: T | 1 | read book
        // Implement parsing logic based on your Task/Deadline/Event/Todo classes
        String[] parts = line.split(" \| ");
        try {
            switch (parts[0]) {
                case "T":
                    return new Todo(parts[2], parts[1].equals("1"));
                case "D":
                    return new Deadline(parts[2], parts[1].equals("1"), parts[3]);
                case "E":
                    return new Event(parts[2], parts[1].equals("1"), parts[3]);
                default:
                    return null;
            }
        } catch (Exception e) {
            // Handle corrupted line
            return null;
        }
    }

    private String formatTask(Task task) {
        String status = task.isDone ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + status + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + status + " | " + d.description + " | " + d.by;
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + status + " | " + e.description + " | " + e.at;
        } else {
            return "";
        }
    }
}
