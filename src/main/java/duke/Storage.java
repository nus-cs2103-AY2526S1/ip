package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading tasks from the file and saving tasks to the file.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a Storage instance with the specified file path.
     * @param filePath The path to the data file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file.
     * @return ArrayList of tasks loaded from file
     * @throws PenguinException if there's an error loading the file
     */
    public ArrayList<Task> load() throws PenguinException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try {
            readTasksFromFile(file, tasks);
        } catch (FileNotFoundException e) {
            throw new PenguinException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Reads tasks from the file and adds them to the task list.
     * @param file The file to read from
     * @param tasks The list to add tasks to
     * @throws FileNotFoundException if the file cannot be found
     */
    private void readTasksFromFile(File file, ArrayList<Task> tasks) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Task task = parseTaskFromLine(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        fileScanner.close();
    }

    /**
     * Parses a single line from the file and creates a Task object.
     * @param line The line to parse
     * @return The parsed Task object, or null if parsing fails
     */
    private Task parseTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = createTaskByType(taskType, description, parts);
        if (task != null) {
            task.setDone(isDone);
        }
        return task;
    }

    /**
     * Creates a task based on its type and the parsed parts.
     * @param taskType The type of task (T, D, E)
     * @param description The task description
     * @param parts The parsed line parts
     * @return The created Task object, or null if creation fails
     */
    private Task createTaskByType(String taskType, String description, String[] parts) {
        switch (taskType) {
        case "T":
            return createTodoTask(description);
        case "D":
            return createDeadlineTask(description, parts);
        case "E":
            return createEventTask(description, parts);
        default:
            return null;
        }
    }

    /**
     * Creates a Todo task.
     * @param description The task description
     * @return A new Todo task
     */
    private Task createTodoTask(String description) {
        return new Todo(description);
    }

    /**
     * Creates a Deadline task.
     * @param description The task description
     * @param parts The parsed line parts
     * @return A new Deadline task, or null if creation fails
     */
    private Task createDeadlineTask(String description, String[] parts) {
        if (parts.length < 4) {
            return null;
        }
        String deadline = parts[3];
        try {
            return new Deadline(description, deadline);
        } catch (Exception e) {
            return null; // Skip invalid date entries
        }
    }

    /**
     * Creates an Event task.
     * @param description The task description
     * @param parts The parsed line parts
     * @return A new Event task, or null if creation fails
     */
    private Task createEventTask(String description, String[] parts) {
        if (parts.length < 5) {
            return null;
        }
        String from = parts[3];
        String to = parts[4];
        return new Event(description, from, to);
    }

    /**
     * Saves the list of tasks to the data file.
     * @param tasks The list of tasks to save
     * @throws PenguinException if there's an error saving to the file
     */
    public void save(ArrayList<Task> tasks) throws PenguinException {
        try {
            ensureDirectoryExists();
            String content = buildFileContent(tasks);
            writeContentToFile(content);
        } catch (IOException e) {
            throw new PenguinException("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Ensures that the directory for the data file exists.
     */
    private void ensureDirectoryExists() {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    /**
     * Builds the content string for all tasks to be written to file.
     * @param tasks The list of tasks to convert to string format
     * @return The formatted content string
     */
    private String buildFileContent(ArrayList<Task> tasks) {
        StringBuilder content = new StringBuilder();
        for (Task task : tasks) {
            String taskLine = formatTaskForFile(task);
            content.append(taskLine).append("\n");
        }
        return content.toString();
    }

    /**
     * Formats a single task for file storage.
     * @param task The task to format
     * @return The formatted task string
     */
    private String formatTaskForFile(Task task) {
        String taskType = getTaskType(task);
        String doneStatus = task.getDone() ? "1" : "0";
        String additionalInfo = getAdditionalTaskInfo(task);

        return taskType + " | " + doneStatus + " | " + task.getDescription() + additionalInfo;
    }

    /**
     * Gets the task type identifier for file storage.
     * @param task The task to get the type for
     * @return The task type string (T, D, or E)
     */
    private String getTaskType(Task task) {
        if (task instanceof Todo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        } else if (task instanceof Event) {
            return "E";
        }
        return "";
    }

    /**
     * Gets additional information for specific task types.
     * @param task The task to get additional info for
     * @return The additional information string
     */
    private String getAdditionalTaskInfo(Task task) {
        if (task instanceof Deadline) {
            return " | " + ((Deadline) task).getBy().toString();
        } else if (task instanceof Event) {
            return " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
        }
        return "";
    }

    /**
     * Writes the content string to the data file.
     * @param content The content to write
     * @throws IOException if there's an error writing to the file
     */
    private void writeContentToFile(String content) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(content);
        fw.close();
    }
}
