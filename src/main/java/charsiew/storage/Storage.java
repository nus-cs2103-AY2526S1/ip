package charsiew.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import charsiew.task.Deadline;
import charsiew.task.Event;
import charsiew.task.Task;
import charsiew.task.TaskList;
import charsiew.task.TaskType;
import charsiew.task.Todo;

/**
 * Handles reading from and writing to the storage file.
 * Provides methods to load tasks from file into a TaskList and save tasks back to the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath Path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Loads tasks from the storage file.
     * If the file/folder doesn't exist, returns an empty TaskList.
     *
     * @return TaskList containing all loaded tasks.
     * @throws IOException If there is an error reading the file.
     */
    public TaskList load() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            return new TaskList();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Task task = parseTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        sc.close();
        return new TaskList(tasks);
    }

    /**
     * Saves tasks from the TaskList into the storage file.
     *
     * @param taskList TaskList to save.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : taskList.getAllTasks()) {
            fw.write(formatTask(t) + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Parses a line of text from the file into a Task object.
     * Example line format: "T | 1 | read book"
     *
     * @param line Line of text from file.
     * @return Task object corresponding to the line, or null if the line is corrupted.
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            TaskType taskType = TaskType.fromCode(parts[0]);
            boolean isDone = parts[1].equals("1");
            String name = parts[2];

            switch (taskType) {
            case TODO:
                Todo todo = new Todo(name);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;

            case DEADLINE:
                LocalDate by = LocalDate.parse(parts[3]);
                Deadline deadline = new Deadline(name, by);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;

            case EVENT:
                LocalDate from = LocalDate.parse(parts[3]);
                LocalDate to = LocalDate.parse(parts[4]);
                Event event = new Event(name, from, to);
                if (isDone) {
                    event.markAsDone();
                }
                return event;

            default:
                return null; // shouldn't happen
            }

        } catch (Exception e) {
            return null; // corrupted line
        }
    }

    /**
     * Converts a Task object into a line of text for saving to file.
     *
     * @param task Task to format.
     * @return String representing the task in file format.
     */
    private String formatTask(Task task) {
        String status = task.isDone() ? "1" : "0";

        if (task instanceof Todo) {
            return TaskType.TODO.getCode() + " | " + status + " | " + task.getName();
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return TaskType.DEADLINE.getCode() + " | " + status + " | " + d.getName() + " | " + d.getBy();
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return TaskType.EVENT.getCode() + " | " + status + " | " + e.getName() + " | " + e.getFrom()
                    + " | " + e.getTo();
        }
        return "";
    }
}
