package winnie.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

import winnie.exception.WinnieException;
import winnie.task.Deadline;
import winnie.task.Event;
import winnie.task.Task;
import winnie.task.Todo;
import winnie.tasklist.TaskList;
import winnie.util.DateTimeUtil;

/**
 * Handles the loading and saving of tasks to and from a file.
 */
public class Storage {

    private static final String FIELD_SEPARATOR = " | ";
    private static final String TIME_RANGE_SEPARATOR = " to ";
    
    private String filePath;

    /**
     * Creates a storage object.
     *
     * @param filePath The file path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the storage file.
     * AI-assisted enhancement: Improved resource management with try-with-resources
     * to ensure proper file closure and better error handling.
     *
     * @param tasks The task list to save.
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasksToFile(TaskList tasks) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            boolean dirsCreated = parentDir.mkdirs();
            if (!dirsCreated && !parentDir.exists()) {
                throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                String taskData = taskToPersistentString(tasks.getTask(i));
                writer.write(taskData + System.lineSeparator());
            }
        }
    }

    /**
     * Loads the tasks from the storage file.
     * AI-assisted enhancement: Improved resource management with try-with-resources
     * and enhanced error reporting for better debugging.
     *
     * @return The task list loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public TaskList loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return new TaskList(tasks);
        }

        try (Scanner scanner = new Scanner(file)) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    try {
                        Task task = persistentStringToTask(line);
                        if (task != null) {
                            tasks.add(task);
                        }
                    } catch (Exception e) {
                        System.err.println(
                                String.format("Warning: Skipping corrupted line %d in data file: %s (Error: %s)",
                                        lineNumber, line, e.getMessage()));
                    }
                }
            }
        }
        return new TaskList(tasks);
    }

    private String taskToPersistentString(Task task) {
        String statusStr = task.isDone() ? "1" : "0";
        String typeStr = task.getTaskType()
                .toString().substring(0, 1);
        String snoozeStr = task.getSnoozeUntil() != null
                ? DateTimeUtil.formatForStorage(task.getSnoozeUntil())
                : "UNSNOOZED";

        switch (task.getTaskType()) {
        case TODO:
            return typeStr
                    + FIELD_SEPARATOR + statusStr
                    + FIELD_SEPARATOR + task.getDescription()
                    + FIELD_SEPARATOR + snoozeStr;
        case DEADLINE:
            Deadline deadline = (Deadline) task;
            return typeStr
                    + FIELD_SEPARATOR + statusStr
                    + FIELD_SEPARATOR + task.getDescription()
                    + FIELD_SEPARATOR + DateTimeUtil.formatForStorage(deadline.getBy())
                    + FIELD_SEPARATOR + snoozeStr;
        case EVENT:
            Event event = (Event) task;
            return typeStr
                    + FIELD_SEPARATOR + statusStr
                    + FIELD_SEPARATOR + task.getDescription()
                    + FIELD_SEPARATOR + DateTimeUtil.formatForStorage(event.getFrom())
                    + TIME_RANGE_SEPARATOR + DateTimeUtil.formatForStorage(event.getTo())
                    + FIELD_SEPARATOR + snoozeStr;
        default:
            return "";
        }
    }

    private Task persistentStringToTask(String data) throws WinnieException {
        assert data != null && !data.trim().isEmpty() : "Data string must not be null or empty";
        String[] parts = data.split(" \\| ");
        if (parts.length < 3) {
            throw new WinnieException("Invalid data format");
        }

        String type = parts[0].trim();
        String status = parts[1].trim();
        String description = parts[2].trim();
        boolean isDone = "1".equals(status);

        Task task = null;

        switch (type.toUpperCase()) {
        case "T":
            task = new Todo(description);
            if (parts.length > 3 && !parts[3].trim().equals("UNSNOOZED")) {
                LocalDateTime snoozeTime = DateTimeUtil.parseFromStorage(parts[3].trim());
                task.snooze(snoozeTime);
            }
            break;
        case "D":
            if (parts.length < 4) {
                throw new WinnieException("Missing deadline time");
            }
            String by = parts[3].trim();
            LocalDateTime deadlineTime = DateTimeUtil.parseFromStorage(by);
            task = new Deadline(description, deadlineTime);
            if (parts.length > 4 && !parts[4].trim().equals("UNSNOOZED")) {
                LocalDateTime snoozeTime = DateTimeUtil.parseFromStorage(parts[4].trim());
                task.snooze(snoozeTime);
            }
            break;
        case "E":
            if (parts.length < 4) {
                throw new WinnieException("Missing event time");
            }
            String[] timeParts = parts[3].trim().split(TIME_RANGE_SEPARATOR);
            if (timeParts.length != 2) {
                throw new WinnieException("Invalid event time format");
            }
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            LocalDateTime fromTime = DateTimeUtil.parseFromStorage(from);
            LocalDateTime toTime = DateTimeUtil.parseFromStorage(to);
            task = new Event(description, fromTime, toTime);
            if (parts.length > 4 && !parts[4].trim().equals("UNSNOOZED")) {
                LocalDateTime snoozeTime = DateTimeUtil.parseFromStorage(parts[4].trim());
                task.snooze(snoozeTime);
            }
            break;
        default:
            throw new WinnieException("Unknown task type: " + type);
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

}
