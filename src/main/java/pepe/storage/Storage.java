package pepe.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import pepe.exception.PepeExceptions;
import pepe.task.Deadlines;
import pepe.task.Events;
import pepe.task.Task;
import pepe.task.ToDos;
import pepe.task.tasklist.TaskList;

/**
 * Handles storage and retrieval of tasks from a file.
 * <p>
 * This class manages loading tasks from a text file into memory
 * and saving tasks from memory back into the file in a specific
 * file format.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a Storage instance for a given file path.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file at the given file path.
     * <p>
     * The method will attempt to parse each line into a Task object
     * (ToDos, Deadlines, Events) and mark them as done if specified.
     * If the file does not exist, it will be created.
     *
     * @return an ArrayList of Tasks loaded from the file
     * @throws PepeExceptions if there is an error creating the file
     */
    public ArrayList<Task> load() throws PepeExceptions {
        ArrayList<Task> outputArray = new ArrayList<>(100);
        File file = new File(this.filePath);
        try {
            if (!file.exists()) {
                createFile(file);
            } else {
                readTaskFromFile(file, outputArray);
            }
        } catch (PepeExceptions e) {
            clearFileContent(file);
            System.out.println("Warning: corrupted file detected. File has been cleared.");
        }
        return outputArray;
    }
    /**
     * Clears all content in the given file without deleting the file itself.
     *
     * @param file the file to clear
     * @throws PepeExceptions if an I/O error occurs
     */
    private void clearFileContent(File file) throws PepeExceptions {
        try (FileWriter fw = new FileWriter(file, false)) { // false = overwrite mode
            // writing nothing clears the file
        } catch (IOException ioException) {
            throw new PepeExceptions("Unable to clear file: " + file.getPath());
        }
    }
    /**
     *  Creates a new file at the specified path, including any missing parent directories.
     * @param file the file to be created
     * @throws PepeExceptions if the file or directories cannot be created */
    private void createFile(File file) throws PepeExceptions {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new PepeExceptions("Error: " + e.getMessage());
        }
    }

    /**
     * Saves the given list of tasks to the file.
     * <p>
     * Each task is written in its file format on a new line.
     *
     * @param tasks the TaskList containing tasks to save
     * @throws IOException if there is an error writing to the file
     */
    public void save(TaskList tasks) throws IOException {
        assert tasks != null : "TaskList object should never be null";
        FileWriter fileWriter = new FileWriter(this.filePath);
        try {
            for (Task task : tasks.getTaskList()) {
                fileWriter.write(task.toFileFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        } finally {
            fileWriter.close();
        }
    }
    /**
     * Reads tasks from the specified file and appends them to the given list.
     * <p>
     * Each line is parsed into a {@link Task} using {@link #parseTaskLine(String)}
     *
     * @param file the file containing stored tasks
     * @param outputArray the list to which parsed tasks will be added
     * @throws PepeExceptions if the file cannot be found or read */
    private void readTaskFromFile(File file, ArrayList<Task> outputArray) throws PepeExceptions {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parseTaskLine(line);
                if (task != null) {
                    outputArray.add(task);
                } else {
                    throw new PepeExceptions("File contains corrupt content! Resetting...");
                }
            }
        } catch (FileNotFoundException e) {
            throw new PepeExceptions("File not found: " + filePath);
        }
    }
    /**
     * Parses a single line from the task file into a {@link Task} object.
     * <p>
     * The line is expected to follow the format:
     * <pre>
     * T | 1 | task name
     * D | 0 | task name | Dec 31 2025
     * E | 1 | task name | Dec 31 2025-Dec 31 2026
     * </pre>
     * If the line is empty, blank, or improperly formatted, a {@link PepeExceptions} is thrown.
     * @param line the line representing a task
     * @return the corresponding {@link Task} object
     * @throws PepeExceptions if the task type is unrecognized */
    private Task parseTaskLine(String line) throws PepeExceptions {
        if (line == null || line.isBlank()) {
            return null; // skip empty lines
        }
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new PepeExceptions("Corrupted task line (too few fields): " + line);
        }
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String taskName = parts[2].trim();

        Task task;
        try {
            switch (type) {
            case "T":
                task = new ToDos(taskName);
                break;
            case "D":
                String by = parts[3].trim();
                String dateline = rawDateToString(by);
                task = new Deadlines(taskName, dateline);
                break;
            case "E":
                String time = parts[3].trim();
                String[] fromAndTo = time.split("-");
                String from = rawDateToString(fromAndTo[0].trim());
                String to = rawDateToString(fromAndTo[1].trim());
                task = new Events(taskName, from, to);
                break;
            default:
                throw new PepeExceptions("Unknown task type: " + line);
            }
        } catch (PepeExceptions e) {
            System.out.println(e.toString());
            return null;
        }
        if (isDone) {
            task.markTask();
        }
        return task;
    }
    /**
     * Converts a raw date string (e.g., "Dec 31 2025") to a standard
     * string format (yyyy-MM-dd) used internally.
     *
     * @param rawDate the raw date string from the file
     * @return the date string in yyyy-MM-dd format
     */
    public String rawDateToString(String rawDate) throws PepeExceptions {
        try {
            assert rawDate != null && !rawDate.isBlank() : "Raw date string should not be null or empty";
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate date = LocalDate.parse(rawDate, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new PepeExceptions("File has been tempered with...");
        }
    }
}
