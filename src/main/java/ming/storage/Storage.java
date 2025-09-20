package ming.storage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ming.exception.MingException;
import ming.model.Deadline;
import ming.model.Event;
import ming.model.Task;
import ming.model.Todo;

/**
 * Manages the loading and saving of tasks to a file.
 */
public class Storage {
    private final Path path;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    /**
     * Loads tasks from the file. Returns an empty list if the file does not exist.
     */
    public List<Task> load() throws MingException {
        List<Task> tasks = new ArrayList<>();

        try {
            Path directory = path.getParent();
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner dataScanner;
        try {
            dataScanner = new Scanner(path.toFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (dataScanner.hasNextLine()) {
            String[] parts = dataScanner.nextLine().split(" \\| ");
            boolean isDone = parts[1].equals("1");
            switch (parts[0]) {
            case "T":
                Task todo = new Todo(parts[2], new ArrayList<>());
                if (isDone) {
                    todo.markAsDone();
                }
                tasks.add(todo);
                break;
            case "D":
                Task deadline = new Deadline(parts[2],
                        LocalDateTime.parse(parts[3], FORMATTER),
                        new ArrayList<>());
                if (isDone) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case "E":
                Task event = new Event(parts[2],
                        LocalDateTime.parse(parts[3], FORMATTER),
                        LocalDateTime.parse(parts[4], FORMATTER),
                        new ArrayList<>());
                if (isDone) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            default:
                System.out.println("Unknown task type: " + parts[0]);
            }
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks List of tasks to save.
     */
    public void save(List<Task> tasks) throws MingException {
        try {
            FileWriter writer = new FileWriter(path.toFile());
            StringBuilder sb = new StringBuilder();
            tasks.stream().forEach(task -> sb.append(task.toDataString()).append("\n"));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new MingException("Error saving tasks: " + e.getMessage());
        }
    }
}
