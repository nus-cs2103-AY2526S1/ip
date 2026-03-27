package joe.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import joe.task.Deadline;
import joe.task.Event;
import joe.task.Task;
import joe.task.TaskList;
import joe.task.ToDo;

import java.util.ArrayList;

/**
 * The {@code Storage} class handles reading and writing of tasks from and to
 * persistent storage.
 */
public class Storage {
    private final ArrayList<Task> todoList = new ArrayList<>();
    private final String filepath;

    /**
     * Creates a storage object that stores and loads history of tasks.
     *
     * @param filepath Filepath to the txt file storing the history.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Stores the current tasks in the TaskList into a txt file.
     *
     * @param todoList TaskList of tasks.
     */
    public void logTodoList(TaskList todoList) {
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            try (FileWriter fw = new FileWriter("data/joe.txt", false)) {
                for (Task task : todoList.getTodoList()) {
                    fw.write(task.toString() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts the tasks in the memory txt file into an ArrayList.
     *
     * @return ArrayList of tasks in the memory txt file.
     */
    public ArrayList<Task> loadTodoList() {
        File file = new File(filepath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                if (taskLine.isEmpty()) {
                    continue;
                }
                parseTaskLine(taskLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return this.todoList;
    }

    // ---------------- Parsing Helpers ---------------- //

    /**
     * Parses a single line from the saved task file and adds the corresponding Task
     * (ToDo, Deadline, or Event) to the todoList.
     *
     * @param taskLine the raw line representing a saved task
     */
    private void parseTaskLine(String taskLine) {
        char taskType = taskLine.charAt(1);
        boolean isDone = taskLine.charAt(4) == '1';

        switch (taskType) {
        case 'T' -> todoList.add(parseToDo(taskLine, isDone));
        case 'D' -> todoList.add(parseDeadline(taskLine, isDone));
        case 'E' -> todoList.add(parseEvent(taskLine, isDone));
        default -> {
        }
        }
    }

    /**
     * Parses a saved ToDo task from its string representation.
     *
     * @param taskLine the raw line representing the ToDo
     * @param isDone whether the task is marked as completed
     * @return a ToDo object reconstructed from the line
     */
    private ToDo parseToDo(String taskLine, boolean isDone) {
        String description = taskLine.split(" ", 2)[1];
        return new ToDo(description, isDone);
    }

    /**
     * Parses a saved Deadline task from its string representation.
     *
     * @param taskLine the raw line representing the Deadline
     * @param isDone whether the task is marked as completed
     * @return a Deadline object reconstructed from the line
     */
    private Deadline parseDeadline(String taskLine, boolean isDone) {
        // Extract description (everything before "(by:")
        String descriptionPart = taskLine.split(" ", 2)[1];
        String description = descriptionPart.split("\\(")[0].strip();

        // Extract raw deadline inside "(by: ...)"
        String deadlineRawPart = taskLine.split("by:")[1];
        String deadlineRaw = deadlineRawPart.substring(0, deadlineRawPart.length() - 1).strip();

        // Convert formatted string back to machine-readable "yyyy-MM-dd HHmm"
        // If you saved it like "Dec 02 2019, 6:00 PM", parse it to LocalDateTime
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        DateTimeFormatter storageFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String deadline = LocalDateTime.parse(deadlineRaw, displayFormat).format(storageFormat);

        return new Deadline(description, deadline, isDone);
    }

    /**
     * Parses a saved Event task from its string representation.
     *
     * @param taskLine the raw line representing the Event
     * @param isDone whether the task is marked as completed
     * @return an Event object reconstructed from the line
     */
    private Event parseEvent(String taskLine, boolean isDone) {
        String descriptionPart = taskLine.split(" ", 2)[1];
        String description = descriptionPart.split("\\(")[0].strip();

        String fromRaw = taskLine.split("from:")[1].split("to:")[0].strip();
        String toRaw = taskLine.split("to: ")[1];
        String to = toRaw.substring(0, toRaw.length() - 1);

        return new Event(description, formatDatesFromMemory(fromRaw), formatDatesFromMemory(to), isDone);
    }

    /**
     * Formats a date string read from storage into the yyyy-MM-dd format.
     *
     * @param date date string in MMM dd yyyy format (e.g., "Jan 02 2025")
     * @return date string in yyyy-MM-dd format (e.g., "2025-01-02")
     */
    public String formatDatesFromMemory(String date) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, inputFormat).format(outputFormat);
    }
}
