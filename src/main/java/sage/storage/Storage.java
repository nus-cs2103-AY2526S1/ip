package sage.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import sage.exception.SageException;
import sage.task.Deadline;
import sage.task.Event;
import sage.task.Task;
import sage.task.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final File file;

    /**
     * Constructs a Storage object with the specified file path.
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the storage file.
     * If the file or its parent directories do not exist, they are created.
     * @return An ArrayList of Task objects loaded from the file.
     * @throws SageException If an error occurs during file loading or parsing.
     */
    public ArrayList<Task> load() throws SageException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create parent directories if they don't exist
                file.createNewFile(); // Create the file if it doesn't exist
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                tasks.add(parseLineToTask(line));
            }
        } catch (IOException e) {
            throw new SageException("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     * @param tasks The ArrayList of Task objects to be saved.
     * @throws SageException If an error occurs during file saving.
     */
    public void save(ArrayList<Task> tasks) throws SageException {
        try (FileWriter fw = new FileWriter(file)) {
            String content = tasks.stream()
                    .map(Task::toFileString)
                    .collect(Collectors.joining("\n"));
            fw.write(content);
        } catch (IOException e) {
            throw new SageException("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Parses a single line from the storage file into a Task object.
     * This is a private helper method.
     * @param line The line string to parse.
     * @return A Task object corresponding to the parsed line.
     * @throws SageException If the line format is corrupted or invalid.
     */
    private Task parseLineToTask(String line) throws SageException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            LocalDateTime by = LocalDateTime.parse(parts[3]);
            task = new Deadline(description, by);
            break;
        case "E":
            LocalDateTime from = LocalDateTime.parse(parts[3]);
            LocalDateTime to = LocalDateTime.parse(parts[4]);
            task = new Event(description, from, to);
            break;
        default:
            throw new SageException("Corrupted data file.");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}