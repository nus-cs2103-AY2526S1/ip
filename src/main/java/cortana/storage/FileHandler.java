package cortana.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.zip.DataFormatException;

import cortana.exception.CortanaException;
import cortana.task.Deadline;
import cortana.task.Event;
import cortana.task.TaskList;
import cortana.task.ToDo;

/**
 * Handles file operations related to task persistence.
 * Responsible for checking, loading, saving, and updating the task data file.
 */
public class FileHandler {

    private final Path filePath;

    /**
     * Constructs a cortana.storage.FileHandler for the given file path.
     *
     * @param filePath the path to the data file
     */
    public FileHandler(Path filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath.toString();
    }

    /**
     * Checks if the data directory and file exist.
     * Creates them if they do not.
     *
     * @throws IOException if directory or file creation fails
     */
    public void ensureFileExists() throws IOException {
        Path directory = filePath.getParent(); // return directory path or null if none
        if (directory != null && !directory.toFile().exists()) {
            boolean createdDir = directory.toFile().mkdirs(); // create directory and parents if needed
            if (!createdDir) {
                throw new IOException("Failed to create directory: " + directory.toString());
            }
        }

        File file = filePath.toFile();
        if (!file.exists()) {
            boolean createdFile = file.createNewFile(); // create empty file if it doesn't exist
            if (!createdFile) {
                throw new IOException("Failed to create file: " + file.getAbsolutePath());
            }
        }
    }

    /**
     * Checks that the data file exists and validates its content format.
     * Resets the file to a blank file if formatting errors are detected.
     *
     * @throws IOException if an I/O error occurs when accessing the file
     */
    public void checkAndPrepareFile() throws IOException, CortanaException {
        ensureFileExists();

        try {
            // Attempt to validate the file format
            validateFileFormat();
        } catch (DataFormatException e) {
            // Malformed file detected - replace with an empty file
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
            throw new CortanaException("DataFormatException detected - Resetting file to blank: " + filePath);
        }
    }

    /**
     * Validates the format of each line in the data file.
     *
     * @throws IOException         if an I/O error occurs reading the file
     * @throws DataFormatException if any line is incorrectly formatted
     */
    private void validateFileFormat() throws IOException, DataFormatException {
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            validateLineFormat(line);
        }
    }

    /**
     * Validates the format of a single line according to expected task format.
     * Examples:
     * <pre>
     * T | 1 | Read Book
     * D | 0 | Return Book | June 6th
     * E | 1 | Project Meeting | Aug 6th 2pm | 4pm
     * </pre>
     *
     * @param line the line string to validate
     * @throws DataFormatException if the line format is invalid
     */
    private void validateLineFormat(String line) throws DataFormatException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new DataFormatException("Malformed line (too few parts): " + line);
        }
        if (!parts[0].matches("[TDE]")) {
            throw new DataFormatException("Invalid task type in line: " + line);
        }
        if (!parts[1].matches("[01]")) {
            throw new DataFormatException("Invalid done status in line: " + line);
        }
        if (parts[0].equals("D") && parts.length != 4) {
            throw new DataFormatException("Invalid number of parameters for deadline in line: " + line);
        } else if (parts[0].equals("E") && parts.length != 5) {
            throw new DataFormatException("Invalid number of parameters for event in line: " + line);
        }
    }

    /**
     * Loads the tasks stored in the data file into a cortana.task.TaskList object.
     * Each line in the file is parsed into a corresponding task with their done statuses.
     *
     * @return the cortana.task.TaskList loaded from the file
     * @throws CortanaException if there is an error related to task creation
     * @throws IOException      if an I/O error occurs reading the file
     */
    public TaskList loadTasks() throws CortanaException, IOException {
        TaskList tasks = new TaskList();

        // Each line format examples:
        // T | 1 | Read Book
        // D | 0 | Return Book | June 6th
        // E | 1 | Project Meeting | Aug 6th 2pm | 4pm

        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            String taskCommand = parts[0];
            String markValue = parts[1];
            String taskName = parts[2];

            // for cortana.task.ToDo tasks
            if (taskCommand.equals("T")) {
                tasks.add(new ToDo(taskName));
                // for cortana.task.Deadline tasks
            } else if (taskCommand.equals("D")) {
                String taskBy = parts[3];
                LocalDateTime byDateTime = LocalDateTime.parse(taskBy, DateTimeFormatter.ofPattern("dd MMM yy HHmm"));
                tasks.add(new Deadline(taskName, byDateTime));
                // for events tasks
            } else {
                String taskFrom = parts[3];
                String taskTo = parts[4];

                LocalDateTime fromDateTime = LocalDateTime.parse(
                            taskFrom, DateTimeFormatter.ofPattern("dd MMM yy HHmm"));
                LocalDateTime toDateTime = LocalDateTime.parse(
                            taskTo, DateTimeFormatter.ofPattern("dd MMM yy HHmm"));
                tasks.add(new Event(taskName, fromDateTime, toDateTime));
            }

            if (markValue.equals("1")) {
                // The task just added is at the end of the list
                tasks.mark(tasks.size());
            }
        }
        return tasks;
    }

    /**
     * Saves a new task string to the data file.
     * The task string is expected in the format: "[T][ ] Read Book".
     * This is converted and appended as "T | 0 | Read Book".
     * @param taskString the string representation of the cortana.task.ToDo task to save
     * @throws CortanaException if an I/O error occurs during file writing
     */
    public void saveToDo(String taskString) throws CortanaException {
        String taskType = taskString.substring(1, 2);
        String description = taskString.substring(7).trim();
        String newString = taskType + " | 0 | " + description + "\n";

        try {
            Files.write(filePath, newString.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new CortanaException(e.getMessage());
        }
    }

    /**
     * Saves a new task string to the data file.
     * The task string is expected in the format: "[T][ ] Read Book (by: Sunday)".
     * This is converted and appended as "T | 0 | Read Book | Sunday".
     * @param taskString the string representation of the cortana.task.Deadline task to save
     * @throws CortanaException if an I/O error occurs during file writing
     */
    public void saveDeadline(String taskString) throws CortanaException {
        String taskType = taskString.substring(1, 2);
        // Find the substring start index of "(by: "
        int startBy = taskString.indexOf("(by: ");
        String description = taskString.substring(7, startBy).trim();
        startBy += "(by: ".length();
        int endBy = taskString.indexOf(")");

        String byDate = taskString.substring(startBy, endBy).trim();
        String newString = taskType + " | 0 | " + description + " | " + byDate + "\n";

        try {
            Files.write(filePath, newString.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new CortanaException(e.getMessage());
        }
    }

    /**
     * Saves a new task string to the data file.
     * The task string is expected in the format: "[T][ ] Read Book (from: Sunday 4pm to: 6pm)".
     * This is converted and appended as "T | 0 | Read Book | Sunday 4pm | 6pm".
     * @param taskString the string representation of the cortana.task.Event task to save
     * @throws CortanaException if an I/O error occurs during file writing
     */
    public void saveEvent(String taskString) throws CortanaException {
        String taskType = taskString.substring(1, 2);
        // Find the substring start index of "(by: "
        int startFrom = taskString.indexOf("(from: ");
        int startTo = taskString.indexOf(" to: ");

        String description = taskString.substring(7, startFrom).trim();
        startFrom += "(from: ".length();
        String from = taskString.substring(startFrom, startTo).trim();

        int endTo = taskString.indexOf(")", startTo);
        String to = taskString.substring(startTo + "to: ".length(), endTo).trim();

        String newString = taskType + " | 0 | " + description + " | " + from + " | " + to + "\n";

        try {
            Files.write(filePath, newString.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new CortanaException(e.getMessage());
        }
    }

    /**
     * Updates the done status mark value (0 or 1) on a specific line in the data file.
     * @param lineNumber the 1-based line number to update
     * @param markValue  the mark value to set ("0" or "1")
     * @throws CortanaException if an I/O error occurs during update
     */
    public void saveMarkValue(int lineNumber, String markValue) throws CortanaException {
        try {
            List<String> lines = Files.readAllLines(filePath);
            String line = lines.get(lineNumber - 1);
            String[] parts = line.split(" \\| ");
            parts[1] = markValue;

            StringBuilder updatedLine = new StringBuilder();
            for (int i = 0; i < parts.length; i++) {
                if (i > 0) {
                    updatedLine.append(" | ");
                }
                updatedLine.append(parts[i]);
            }

            lines.set(lineNumber - 1, updatedLine.toString());
            Files.write(filePath, lines);

        } catch (IOException e) {
            throw new CortanaException(e.getMessage());
        }
    }

    /**
     * Deletes a task line from the data file by line number.
     * @param lineNumber the 1-based line number of the task to delete
     * @throws CortanaException if an I/O error occurs during delete
     */
    public void saveDelete(int lineNumber) throws CortanaException {
        try {
            List<String> lines = Files.readAllLines(filePath);
            lines.remove(lineNumber - 1);
            Files.write(filePath, lines);
        } catch (IOException e) {
            throw new CortanaException(e.getMessage());
        }
    }
}
