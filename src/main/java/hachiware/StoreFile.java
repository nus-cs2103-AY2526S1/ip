package hachiware;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Handles the saving and loading of tasks from a persistent text file.
 * Tasks are stored in a text format, with each line representing 1 task.
 */
public class StoreFile {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** Persistent filepath to use */
    private final String filePath = "./data/Hachiware.Hachiware.txt";


    /**
     * Constructs a StoreFile handler.
     *
     * @param filePath Path of the file to store and load tasks.
     */
    public StoreFile(String filePath) {

    }

    /**
     * Converts a line of text from the text file into a {@link Task} object.
     *
     * @param line A line from the save file (e.g., "T | 1 | read book").
     * @return A corresponding {@link Task} object (ToDo, Deadline, or Event).
     * @throws HachiwareException If the line format is invalid or task type is unknown.
     */
    private Task convertTask(String line) throws HachiwareException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = Objects.equals(parts[1], "1");
        String descript = parts[2];

        switch (taskType) {
        case "T":
            assert parts.length >= 4 : "ToDo must have at least 4 parts";
            Task todo = new ToDo(descript);
            if (isDone) {
                todo.markAsDone();
            }
            todo.setPriority(Priority.valueOf(parts[3].toUpperCase()));
            return todo;

        case "D":
            assert parts.length >= 5 : "Deadline must have at least 5 parts";
            String by = parts[3];
            Task deadline = new Deadline(descript, by);
            if (isDone) {
                deadline.markAsDone();
            }
            deadline.setPriority(Priority.valueOf(parts[4].toUpperCase()));
            return deadline;

        case "E":
            assert parts.length >= 6 : "Event must have at least 6 parts";
            String from = parts[3];
            String to = parts[4];
            Task event = new Event(descript, from, to);
            if (isDone) {
                event.markAsDone();
            }
            event.setPriority(Priority.valueOf(parts[5].toUpperCase()));
            return event;

        default:
            throw new HachiwareException("Unknown task type: " + taskType);

        }

    }

    /**
     * Converts a {@link Task} object into its text file format.
     *
     * @param task The task to convert.
     * @return A string representation of the task (e.g "T | 1 | read book").
     * @throws HachiwareException If the task type is unsupported.
     */
    private String convertTaskToSave(Task task) throws HachiwareException {
        String done = task.isDone ? "1" : "0";

        assert task != null : "Task cannot be null when saving";

        if (task instanceof ToDo) {
            return "T | " + done + " | " + task.description + " | " + task.getPriority();
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + done + " | " + d.description + " | " + d.getBy()
                    + " | " + d.getPriority(); //Updated to use getBy()
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + done + " | " + e.description + " | " + e.from.format(FORMATTER)
                    + " | " + e.to.format(FORMATTER) + " | " + e.getPriority();
        } else {
            throw new HachiwareException("Unknown task type");
        }
    }

    /**
     * Loads all tasks from the text file into a task list.
     * Create if the file or parent directories do not exist.
     *
     * @return A list of tasks loaded from the save file.
     * @throws HachiwareException If the file cannot be read or parsed correctly.
     */
    public ArrayList<Task> load() throws HachiwareException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            //Check if folder exist and create if it doesnt
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            //Using BufferedReader as it can read line by line instead
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    assert convertTask(line) != null : "Task to convert should not be null";
                    tasks.add(convertTask(line));
                }
            }
        } catch (IOException e) {
            throw new HachiwareException("Failed to load tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves all current tasks into the text file.
     * Overwrites the file if it already exists.
     *
     * @param tasks The list of tasks to save.
     * @throws HachiwareException If the file cannot be written.
     */
    public void save(ArrayList<Task> tasks) throws HachiwareException {

        assert tasks != null : "Task list cannot be null";
        for (Task t : tasks) {
            assert t != null : "Task list contains a null task";
        }

        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Task task : tasks) {
                    String line = convertTaskToSave(task);
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new HachiwareException("Failed to save tasks: " + e.getMessage());
        }
    }
}
