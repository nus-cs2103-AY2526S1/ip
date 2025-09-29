package floydai.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import floydai.FloydException;
import floydai.task.Deadline;
import floydai.task.Event;
import floydai.task.Task;
import floydai.task.TaskType;
import floydai.task.Todo;


/**
 * Handles loading and saving of tasks to a file on disk.
 * <p>
 * Tasks are stored as text lines using the format:
 * {@code TYPE | DONE | DESCRIPTION [| BY/FROM | TO]} depending on the task type.
 */
public class Storage {
    /** Path to the file used for persisting tasks. */
    private final String filePath;

    /**
     * Constructs a {@code Storage} object for the given file path.
     *
     * @param filePath the path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the save file.
     *
     * @return a list of tasks reconstructed from the save file
     * @throws FloydException if an error occurs while reading the file
     */
    public ArrayList<Task> load() throws FloydException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // return empty if no save file
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                tasks.add(parseTaskLine(sc.nextLine()));
            }
        } catch (IOException e) {
            throw new FloydException("Error loading file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Parses a single line from the save file into a Task object.
     *
     * @param line a line representing a task, split by " | "
     * @return the parsed Task object
     * @throws FloydException if the task type cannot be parsed
     */
    private Task parseTaskLine(String line) throws FloydException {
        String[] parts = line.split(" \\| ");
        TaskType type = parseType(parts[0].trim());

        boolean isDone = parts[1].trim().equals("1");
        Task task = createTask(type, parts);

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Creates a Task object based on its type and line parts.
     *
     * @param type  the TaskType of the task
     * @param parts the parts of the line split by " | "
     * @return a Task instance of the correct subtype
     * @throws FloydException if the task type is not recognized
     */
    private Task createTask(TaskType type, String[] parts) throws FloydException {
        return switch (type) {
            case TODO -> new Todo(parts[2].trim());
            case DEADLINE -> new Deadline(parts[2].trim(), parts[3].trim());
            case EVENT -> new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
        };
    }

    /**
     * Saves the given tasks to the file.
     * <p>
     * Automatically creates parent directories if they do not exist.
     *
     * @param tasks the list of tasks to save
     * @throws FloydException if writing to the file fails
     */
    public void save(ArrayList<Task> tasks) throws FloydException {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (FileWriter fw = new FileWriter(file)) {
                for (Task t : tasks) {
                    fw.write(serializeTask(t) + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new FloydException("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Converts a task to a line string suitable for saving in the file.
     *
     * @param task the task to serialize
     * @return the serialized string representation
     */
    private String serializeTask(Task task) {
        StringBuilder sb = new StringBuilder();
        TaskType type = task.getType();
        sb.append(type.getIcon()).append(" | ")
                .append(task.isDone() ? "1" : "0").append(" | ")
                .append(task.getDescription());

        if (type == TaskType.DEADLINE) {
            sb.append(" | ").append(((Deadline) task).getBy());
        } else if (type == TaskType.EVENT) {
            sb.append(" | ").append(((Event) task).getFrom())
                    .append(" | ").append(((Event) task).getTo());
        }
        return sb.toString();
    }

    /**
     * Converts an icon string to its corresponding {@link TaskType}.
     *
     * @param icon the icon representing a task type
     * @return the {@link TaskType} corresponding to the icon
     * @throws FloydException if the icon does not match any known task type
     */
    private TaskType parseType(String icon) throws FloydException {
        for (TaskType t : TaskType.values()) {
            if (t.getIcon().equals(icon)) {
                return t;
            }
        }
        throw new FloydException("Unknown task type: " + icon);
    }
}
