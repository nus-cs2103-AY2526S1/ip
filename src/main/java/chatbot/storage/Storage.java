package chatbot.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.Todo;
import chatbot.tasklist.TaskList;




/**
 * Handles saving and loading tasks to and from a text file.
 * Supports Todo, Deadline, and Event tasks.
 */
public class Storage {

    private final String filepath;
    private final String parentFolder;

    /**
     * Constructs a Storage object with the specified folder and file path.
     *
     * @param parentFolder the folder to store the file in
     * @param filepath     the file path for storing tasks
     */
    public Storage(String parentFolder, String filepath) {
        this.parentFolder = parentFolder;
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the file into an ArrayList of Task objects.
     *
     * @return an ArrayList of tasks read from the file
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>(List.of(
                new Todo("Finish homework"),
                new Deadline("Submit report", "2019-12-02 1800"),
                new Event("Chill session", "now", "later")
        ));
        return tasks;
    }

    /**
     * Parses a single line of text from the storage file into a Task object.
     *
     * @param line a line from the storage file
     * @return the corresponding Task object, or null if the line is invalid
     */
    private Task parseTask(String line) {
        line = line.trim();

        // Validate basic shape: [T|D|E][ |X]...
        assert line.matches("^\\[[TDE]\\]\\[( |X)\\].+")
                : "Invalid task format: " + line;

        boolean isDone = line.contains("[X]");
        // strip the done box so we can parse uniformly
        line = line.replace("[X]", "").replace("[ ]", "").trim();

        // Type is the char inside the first box, e.g. [T]
        char type = line.charAt(1);
        String payload = line.substring(3).trim();
        return withDone(createTask(type, payload), isDone);
    }

    /** Factory: create Todo/Deadline/Event from the payload (no done-state here). */
    private Task createTask(char type, String payload) {
        return switch (type) {
        case 'T' -> createTodo(payload);
        case 'D' -> createDeadline(payload);
        case 'E' -> createEvent(payload);
        default -> throw new IllegalArgumentException("Unknown task type: " + type);
        };
    }

    /** Marks as done if needed and returns the same task (fluent helper). */
    private Task withDone(Task t, boolean isDone) {
        if (isDone) { t.markDone(); }
        return t;
    }

    /** T: payload is just the description. */
    private Task createTodo(String payload) {
        return new Todo(payload);
    }

    //D: payload looks like "<desc> by: <iso-datetime>" (your save format).
    private Task createDeadline(String payload) {
        String[] parts = payload.split("by:", 2);
        String desc = parts[0].trim();
        String byStr = (parts.length > 1) ? parts[1].trim() : "";

        try {
            if (!byStr.isEmpty()) {
                LocalDateTime by = LocalDateTime.parse(byStr);
                return new Deadline(desc, by.toString());
            }
        } catch (DateTimeParseException ignore) {
            // fall through and keep raw string
        }
        return new Deadline(desc, byStr);
    }

    //E: payload looks like "<desc> from: <start> to: <end>" (free-form strings).
    private Task createEvent(String payload) {
        String[] fromSplit = payload.split("from:", 2);
        String desc = fromSplit[0].trim();

        String from = "";
        String to = "";

        if (fromSplit.length > 1) {
            String[] toSplit = fromSplit[1].split("to:", 2);
            from = toSplit[0].trim();
            if (toSplit.length > 1) {
                to = toSplit[1].trim();
            }
        }
        return new Event(desc, from, to);
    }

    /**
     * Saves the tasks in the TaskList to the file.
     * Creates parent folder and file if they do not exist.
     *
     * @param taskList the TaskList containing tasks to save
     */
    public void saveTasks(TaskList taskList) {
        try {
            File folder = new File(parentFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(filepath);
            for (Task task : taskList.getTasks()) {
                //int doneFlag = (task.isDone()) ? 1 : 0;
                writer.write(task + System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }





}
