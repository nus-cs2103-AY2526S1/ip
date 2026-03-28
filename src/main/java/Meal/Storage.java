package Meal;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path filePath;
    private static final String TYPE_TODO = "T";
    private static final String TYPE_DEADLINE = "D";
    private static final String TYPE_EVENT = "E";
    private static final String MARKED = "1";
    private static final String UNMARKED = "0";

    /**
     * Initialises a new storage for the given filepath
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        try {
            Files.createDirectories(this.filePath.getParent());
            if (!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file
     * @return a list of tasks to be loaded
     */
    public List<Task> load() {
        List<Task> items = new ArrayList<>();
        try {
            if (!Files.exists(filePath)) {
                return items;
            }
            for (String line : Files.readAllLines(filePath)) {
                if (line.isBlank()) {
                    continue;
                }
                Task task = parseLine(line);
                items.add(task);
            }
        } catch (IOException e) {
            System.out.println("Load failed: " + e.getMessage());
        }
        return items;
    }

    /**
     * Parses a single line from the storage file into a task
     * @param line raw line of text
     * @return the corresponding task object
     */
    private Task parseLine(String line) {
        String[] parts = line.split("\\s*\\|\\s*");
        String type = parts[0];
        boolean done = MARKED.equals(parts[1]);
        String name = parts[2];

        Task task = switch (type) {
            case TYPE_TODO -> new ToDo(name);
            case TYPE_DEADLINE -> new Deadline(name, parts[3]);
            case TYPE_EVENT -> new Event(name, parts[3], parts[4]);
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };

        if (done) {
            task.silentMark();
        }
        return task;
    }

    /**
     * Saves all tasks into the storage file
     * @param tasks list of tasks to save
     */
    public void save(List<Task> tasks) {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task t : tasks) {
                sb.append(toDataString(t)).append(System.lineSeparator());
            }
            Files.writeString(filePath, sb.toString(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    /**
     * Converts a task into its data string format for saving to the file
     * @param t task to be converted
     * @return its corresponding data string
     */
    private String toDataString(Task t) {
        String done = t.isMarked() ? "1" : "0";
        if (t instanceof ToDo) {
            return String.format("T | %s | %s", done, t.getTaskName());
        } else if (t instanceof Deadline d) {
            return String.format("D | %s | %s | %s", done, d.getTaskName(), d.getDeadline());
        } else if (t instanceof Event e) {
            return String.format("E | %s | %s | %s | %s",
                    done, e.getTaskName(), e.getStartTime(), e.getEndTime());
        }
        return String.format("? | %s | %s", done, t.getTaskName());
    }
}
