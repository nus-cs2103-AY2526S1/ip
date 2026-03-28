package peanutbutter.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import peanutbutter.tasks.Deadline;
import peanutbutter.tasks.Event;
import peanutbutter.tasks.Task;
import peanutbutter.tasks.TaskList;
import peanutbutter.tasks.Todo;

/**
 * Storage class that handles reading and writing
 * Allows the task list to be saved and maintained
 */
public class Storage {
    private String path;

    /**
     * Creates a Storage object with a file path
     *
     * @param path The path that the Storage is created with
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Writes the contents of the task list to the storage
     *
     * @param taskList The task list that is being saved in the storage
     */
    public void write(TaskList taskList) {
        checkFolder();
        String serialized = serialiseTasks(taskList);
        writeToFile(serialized);
    }

    /**
     * Reads the saved contents of the task list from the storage
     *
     * @return The task list that is saved in the storage
     */
    public List<Task> read() {
        File file = new File(path);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return parseTasksFromFile(file);
    }

    private void checkFolder() {
        File folder = new File("Data");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private String serialiseTasks(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList.getTasks()) {
            sb.append(task.makePretty()).append("\n");
        }
        return sb.toString();
    }

    private void writeToFile(String content) {
        try (FileWriter fw = new FileWriter(this.path)) {
            fw.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Task> parseTasksFromFile(File file) {
        List<Task> tasks = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    tasks.add(parseFile(line));
                }
            }
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
        return tasks;
    }

    /**
     * Helper method to translates the contents of the task list that is saved in storage
     *
     * @param line The line that is being translated back from a string to a task
     * @return The task that was just translated
     */
    private Task parseFile(String line) {
        String[] parts = line.split("\\s*\\|\\s*");
        String taskType = parts[0];
        boolean isDone = "1".equals(parts[1]);
        String description = parts[2];
        Task task = null;

        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;

        case "D":
            parts[3] = parts[3].trim().replace(":", "");
            task = new Deadline(description, parts[3]);
            break;

        case "E":
            parts[3] = parts[3].trim().replace(":", "");
            parts[4] = parts[4].trim().replace(":", "");
            task = new Event(description, parts[3], parts[4]);
            break;

        default:
            throw new IllegalArgumentException();
        }

        if (isDone) {
            task.markDone();
        }

        return task;
    }
}
