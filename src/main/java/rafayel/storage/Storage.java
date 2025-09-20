
package rafayel.storage;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import rafayel.RafayelException;
import rafayel.task.Deadline;
import rafayel.task.Event;
import rafayel.task.Task;
import rafayel.task.Todo;

/**
 * Handles persistent storage of tasks for the Rafayel chatbot.
 * <p>
 * This class is responsible for:
 * <ul>
 *   <li>Saving tasks to a file in a defined format.</li>
 *   <li>Loading tasks from a file and reconstructing them.</li>
 *   <li>Ensuring the storage file and directories exist.</li>
 *   <li>Parsing dates from different supported formats.</li>
 * </ul>
 * </p>
 */
public class Storage {

    /** String to store the path to the data/files */
    protected String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the storage file.
     * Each task is converted to a string representation and written to the file.
     *
     * @param tasks list of tasks to be saved.
     * @throws RafayelException if an error occurs during file writing.
     */
    public void save(ArrayList<Task> tasks) throws RafayelException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.saveTaskName() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RafayelException("I couldn't save the file :c");
        }
    }

    /**
     * Loads tasks from the storage file.
     * Parses each line of the file to reconstruct Task objects.
     *
     * @return an ArrayList of Task objects loaded from the file.
     * @throws RafayelException if there's an error ensuring file existence.
     * @throws IOException if there's an error reading the file.
     */
    public ArrayList<Task> load() throws RafayelException, IOException {

        // check if directory/folder and file exists
        ensureFileExists();

        ArrayList<Task> tasks = new ArrayList<Task>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }
        }
        return tasks;
    }

    /**
     * Ensures that the storage file and its parent directories exist.
     * Creates directories and file if they don't exist.
     *
     * @throws RafayelException if directory or file creation fails.
     */
    private void ensureFileExists() throws RafayelException, IOException {
        File file = new File(filePath);
        File directory = file.getParentFile();

        if (directory != null && !directory.exists()) {
            boolean dirCreated = directory.mkdirs();
            if (!dirCreated) {
                throw new RafayelException("Failed to create folder: " + directory.getAbsolutePath());
            }
        }

        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                throw new RafayelException("Failed to create new file: " + file.getAbsolutePath());
            }
        }
    }

    /**
    * Parses a single line from the storage file into a Task.
    *
    * @param input raw line from file.
    * @return Task object parsed from the line.
    * @throws RafayelException if the task type is unknown or the line is invalid.
    */
    private Task parseTask(String input) throws RafayelException {
        String[] parts = input.split(" \\| ");

        if (parts.length < 3) {
            throw new RafayelException("Task line does not have enough parts :0");
        }

        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        try {
            return switch (taskType) {
            case "T" -> parseTodo(description, isDone);
            case "D" -> parseDeadline(parts, description, isDone);
            case "E" -> parseEvent(parts, description, isDone);
            default -> throw new RafayelException("Unknown task type imported ;<!");
            };
        } catch (Exception e) {
            throw new RafayelException("Error parsing task: " + input + " - " + e.getMessage());
        }
    }

    /**
     * Parses a Todo task.
     *
     * @param description description of the todo task.
     * @param isDone whether todo task is completed.
     * @return a Todo object.
     */
    private Task parseTodo(String description, Boolean isDone) {
        Task task = new Todo(description);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Parses a Deadline task.
     *
     * @param parts split parts of the input line.
     * @param description description of the deadline task.
     * @param isDone whether deadline task is completed.
     * @return a Deadline object.
     * @throws RafayelException if input format is invalid.
     */
    private Task parseDeadline(String[] parts, String description, Boolean isDone) throws RafayelException {
        final int DEADLINE_NUM_PARTS = 4;
        if (parts.length < DEADLINE_NUM_PARTS) {
            throw new RafayelException("Deadline task does not have enough parts, I need " + DEADLINE_NUM_PARTS
                    + " but you gave me " + parts.length);
        }
        LocalDateTime by = handleReadDate(parts[3].trim());

        Task task = new Deadline(description, by);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Parses an Event task.
     *
     * @param parts split parts of the input line.
     * @param description description of the event task.
     * @param isDone whether event task is completed.
     * @return an Event object.
     * @throws RafayelException if input format is invalid.
     */
    private Task parseEvent(String[] parts, String description, Boolean isDone) throws RafayelException {
        final int EVENT_NUM_PARTS = 4;
        if (parts.length < EVENT_NUM_PARTS) {
            throw new RafayelException("Event task does not have enough parts, I need " + EVENT_NUM_PARTS
                    + " but you gave me " + parts.length);
        }

        String[] fromTo = parts[3].trim().split("-");
        LocalDateTime from = handleReadDate(fromTo[0].trim());
        LocalDateTime to = handleReadDate(fromTo[1].trim());

        Task task = new Event(description, from, to);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Parses a date string into a LocalDateTime object using multiple supported formats.
     *
     * @param input the date string to parse.
     * @return the parsed LocalDateTime object, or null if no format matches.
     */
    public static LocalDateTime handleReadDate(String input) {
        // check if valid format
        DateTimeFormatter[] differentTimeFormatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm") };

        for (DateTimeFormatter formatter : differentTimeFormatters) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (Exception ignore) {
                // ignore
            }
        }

        return null; // Error
    }

}
