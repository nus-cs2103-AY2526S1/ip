package hachi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the reading and writing of task and note data from/to files.
 * Responsible for unpacking stored task data into task objects,
 * saving modified or newly created task data, and mapping notes to tasks.
 */
public class Storage {
    private final File taskData;
    private final File noteData;

    /**
     * Constructs a new Storage object for managing tasks and notes.
     *
     * @param taskData the file where task data is stored
     * @param noteData the file where note data is stored
     */
    public Storage(File taskData, File noteData) {
        this.taskData = taskData;
        this.noteData = noteData;
    }

    // ------------------ UNPACK ------------------

    /**
     * Unpacks task data from the task file into a list of Task objects.
     * Also reads and attaches notes to their associated tasks.
     *
     * @return a list of tasks read from the task file
     * @throws IOException if an error occurs while reading task or note files
     */
    public ArrayList<Task> unpack() throws IOException {
        if (isFileEmpty(taskData)) {
            return new ArrayList<>();
        }
        ArrayList<Task> tasks = readTasksFromFile();
        readNoteData(tasks);
        return tasks;
    }

    /**
     * Reads all task data lines from the task file and parses them into Task objects.
     *
     * @return a list of parsed tasks
     * @throws IOException if an error occurs while reading the file
     */
    private ArrayList<Task> readTasksFromFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(taskData)) {
            while (scanner.hasNextLine()) {
                tasks.add(parseTask(scanner.nextLine()));
            }
        }
        return tasks;
    }

    /**
     * Parses a single line of task data into a Task object.
     *
     * @param line the raw string from the task file
     * @return a Task object corresponding to the parsed line
     * @throws IllegalArgumentException if the task code is invalid
     */
    private Task parseTask(String line) {
        String[] parts = line.split("\\|");
        String taskCode = parts[0];
        return switch (taskCode) {
            case "T" -> parseToDo(parts);
            case "D" -> parseDeadline(parts);
            case "E" -> parseEvent(parts);
            default -> throw new IllegalArgumentException("Invalid task code: " + taskCode);
        };
    }

    /**
     * Parses task data into a ToDo task.
     *
     * @param parts the split data fields from the task line
     * @return a ToDo task
     */
    private ToDo parseToDo(String[] parts) {
        return new ToDo(parts[1], Boolean.parseBoolean(parts[2]));
    }

    /**
     * Parses task data into a Deadline task.
     *
     * @param parts the split data fields from the task line
     * @return a Deadline task
     */
    private Deadline parseDeadline(String[] parts) {
        return new Deadline(parts[1], Boolean.parseBoolean(parts[2]), LocalDateTime.parse(parts[3]));
    }

    /**
     * Parses task data into an Event task.
     *
     * @param parts the split data fields from the task line
     * @return an Event task
     */
    private Event parseEvent(String[] parts) {
        return new Event(parts[1], Boolean.parseBoolean(parts[2]),
                LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]));
    }

    // ------------------ WRITE ------------------

    /**
     * Writes both tasks and associated notes to their respective files.
     *
     * @param data the list of tasks to save
     * @throws IOException if an error occurs while writing
     */
    public void write(ArrayList<Task> data) throws IOException {
        writeTasks(data);
        writeNotes(data);
    }

    /**
     * Writes task data to the task file in a serialized format.
     *
     * @param data the list of tasks to save
     * @throws IOException if an error occurs while writing
     */
    private void writeTasks(ArrayList<Task> data) throws IOException {
        try (FileWriter fw = new FileWriter(taskData)) {
            for (Task task : data) {
                fw.write(formatTask(task) + "\n");
            }
        }
    }

    /**
     * Converts a Task object into its string representation for storage.
     *
     * @param task the task to serialize
     * @return the formatted string representation of the task
     * @throws IllegalArgumentException if the task type is unsupported
     */
    private String formatTask(Task task) {
        if (task instanceof ToDo todo) {
            return String.format("T|%s|%s", todo.description, todo.isCompleted);
        } else if (task instanceof Deadline deadline) {
            return String.format("D|%s|%s|%s", deadline.description.trim(),
                    deadline.isCompleted, deadline.by);
        } else if (task instanceof Event event) {
            return String.format("E|%s|%s|%s|%s", event.description.trim(),
                    event.isCompleted, event.from, event.to);
        }
        throw new IllegalArgumentException("Unknown task type: " + task.getClass().getName());
    }

    /**
     * Writes note data associated with tasks to the note file.
     * Each line corresponds to the note of a task.
     *
     * @param data the list of tasks containing notes
     * @throws IOException if an error occurs while writing
     */
    private void writeNotes(ArrayList<Task> data) throws IOException {
        try (FileWriter fw = new FileWriter(noteData)) {
            for (Task task : data) {
                String note = task.printNote();
                fw.write(note == null ? "\n" : note + "\n");
            }
        }
    }

    // ------------------ READ NOTES ------------------

    /**
     * Reads notes from the note file and maps them to their corresponding tasks.
     *
     * @param tasks the list of tasks to attach notes to
     * @throws IOException if an error occurs while reading
     */
    public void readNoteData(ArrayList<Task> tasks) throws IOException {
        if (isFileEmpty(noteData)) {
            return;
        }
        try (Scanner scanner = new Scanner(noteData)) {
            int index = 0;
            while (scanner.hasNextLine() && index < tasks.size()) {
                index = readNoteForTask(tasks, scanner, index);
            }
        }
    }

    /**
     * Reads a note (and its timestamp) for a single task from the note file.
     *
     * @param tasks   the list of tasks
     * @param scanner the scanner reading the file
     * @param index   the current task index
     * @return the next task index
     */
    private int readNoteForTask(ArrayList<Task> tasks, Scanner scanner, int index) {
        String information = scanner.nextLine();
        if (information.isEmpty()) {
            return index + 1;
        }

        String unformattedTime = scanner.nextLine();
        LocalDateTime parsedTime = new DateFormatter()
                .parseTime(unformattedTime.substring(unformattedTime.indexOf(" ")).trim());

        tasks.get(index).addNote(information, parsedTime);
        return index + 1;
    }

    // ------------------ HELPERS ------------------

    /**
     * Checks if a file does not exist or is empty.
     *
     * @param file the file to check
     * @return true if the file is missing or empty, false otherwise
     * @throws IOException if an error occurs while checking file size
     */
    private boolean isFileEmpty(File file) throws IOException {
        return !Files.isRegularFile(file.toPath()) || Files.size(file.toPath()) == 0L;
    }
}

