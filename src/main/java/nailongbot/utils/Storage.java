package nailongbot.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import nailongbot.task.Deadline;
import nailongbot.task.Event;
import nailongbot.task.Task;
import nailongbot.task.Todo;


/**
 * Manages the storage and retrieval of tasks from file system.
 * Handles both loading tasks on startup and saving tasks on exit.
 */
public class Storage {
    private static final String FILE_PATH = "data/saved_tasks.txt";
    private static final String DIRECTORY_PATH = "data";

    /**
     * Loads the task from saved location on start up.
     *
     * @return the ArrayList of Tasks that are stored.
     * @throws IOException when file is not created.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> memory = new ArrayList<>();
        try {
            Path path = Paths.get(FILE_PATH);
            if (!Files.exists(path)) {
                return memory; // No file to load
            }
            List<String> lines = Files.readAllLines((path));
            int corruptedLines = 0;

            for (String line : lines) {
                Task task = parseTask(line);
                if (!line.isEmpty() && task != null) {
                    memory.add(task);
                } else {
                    corruptedLines++;
                }
            }
            if (corruptedLines > 0) {
                System.out.println("Loaded, system skip " + corruptedLines + "lines due to corruption.");
            }
            System.out.print(Ui.LINE);
            System.out.println("Loaded :)");

        } catch (IOException e) {
            System.out.println("File does not exist");
        }
        return memory;
    }

    /**
     * Saves the task to saved location on closing of app.
     *
     * @param memory the ArrayList of Tasks that has been added.
     * @throws IOException when there is an error saving the file.
     */
    public void saveTasks(ArrayList<Task> memory) {
        try {
            File directory = new File(DIRECTORY_PATH);

            // handles if data file does not exist at the start
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // writes every line
            try (var writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardCharsets.UTF_8)) {
                for (Task task : memory) {
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
            System.out.println("Saved! :)");
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    /**
     * Parses a single line from the data file into a Task object.
     * Expected format: "T | 0 | description" for Todo,
     * "D | 1 | description | byDate" for Deadline,
     * "E | 0 | description | fromTime | toTime" for Event.
     *
     * @param input the file line to parse, formatted with " | " separators
     * @return the parsed Task object, or null if input format is invalid
     * @throws ArrayIndexOutOfBoundsException if input doesn't have enough parts
     * @throws IllegalArgumentException       if task type is unrecognized
     */
    Task parseTask(String input) {
        try {
            String parts[] = input.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String desc = parts[2];

            Task task;
            switch (type) {
            case "T":
                task = new Todo(desc);
                break;
            case "D":
                task = new Deadline(desc, parts[3]);
                break;
            case "E":
                task = new Event(desc, parts[3], parts[4]);
                break;
            default:
                return null;
            }

            if (isDone) {
                task.doTask();
            }
            return task;

        } catch (Exception e) {
            System.out.println("Error parsing line: " + input);
            return null;
        }
    }
}
