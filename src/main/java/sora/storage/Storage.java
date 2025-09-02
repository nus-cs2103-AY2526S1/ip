package sora.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import sora.list.TaskList;
import sora.task.Deadline;
import sora.task.Event;
import sora.task.Task;
import sora.task.Todo;

/**
 * Handles the loading and saving of tasks to a file.
 * <p>
 * This class is responsible for reading tasks from a file into a {@link TaskList}
 * and saving tasks from a {@link TaskList} back to the file.
 */
public class Storage {
    private String filePath;
    private final DateTimeFormatter format =
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH);

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * <p>
     * If the file or its parent directories do not exist, they will be created,
     * and an empty {@link TaskList} is returned.
     *
     * @return a {@link TaskList} containing all tasks loaded from the file.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            switch (type) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                tasks.addTask(todo);
                break;
            case "D":
                LocalDateTime by = LocalDateTime.parse(parts[3], format);
                Deadline deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.markAsDone();
                }
                tasks.addTask(deadline);
                break;
            case "E":
                LocalDateTime from = LocalDateTime.parse(parts[3], format);
                LocalDateTime to = LocalDateTime.parse(parts[4], format);
                Event event = new Event(description, from, to);
                if (isDone) {
                    event.markAsDone();
                }
                tasks.addTask(event);
                break;
            default:
                System.out.println("Invalid line: " + line);
            }
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks the {@link TaskList} containing tasks to save.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter file = new FileWriter(filePath);
        for (Task task : tasks.getFullTasks()) {
            file.write(task.toFormat() + System.lineSeparator());
        }
        file.close();
    }
}
