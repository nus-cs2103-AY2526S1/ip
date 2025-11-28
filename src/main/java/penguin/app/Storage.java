package penguin.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import penguin.tasks.Deadline;
import penguin.tasks.Event;
import penguin.tasks.Task;
import penguin.tasks.Todo;

/**
 * Handles reading and writing tasks to a file.
 */
public class Storage {

    private static final String FOLDER_PATH = "./data";
    private static final String FILE_PATH = FOLDER_PATH + "/penguin.txt";

    /**
     * Ensures the data folder exists.
     */
    public void createFolder() {
        new File(FOLDER_PATH).mkdirs();
    }

    /**
     * Ensures the storage file exists.
     * @throws IOException if file creation fails
     */
    public void createFile() throws IOException {
        new File(FILE_PATH).createNewFile();
    }

    /**
     * Reads tasks from the storage file.
     *
     * @return list of tasks
     * @throws FileNotFoundException if file not found
     */
    public List<Task> load() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] split = scanner.nextLine().split(" \\| ");
                Task task = parseTaskFromFile(split);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }

        return tasks;
    }

    /**
     * Writes all tasks to the storage file.
     *
     * @param tasks list of tasks to save
     * @throws IOException if writing fails
     */
    public void writeAllTasks(List<Task> tasks) throws IOException {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                fw.write(convertTaskToLine(task) + System.lineSeparator());
            }
        }
    }

    // ------------------- Helper Methods -------------------

    /**
     * Converts a line from the file into a Task object.
     */
    private Task parseTaskFromFile(String[] split) {
        if (split.length < 3) return null; // invalid line

        Task task;
        switch (split[0]) {
        case "T":
            task = new Todo(split[2]);
            break;
        case "D":
            task = new Deadline(split[2], split.length > 3 ? split[3] : "");
            break;
        case "E":
            task = new Event(split[2], split.length > 3 ? split[3] : "", split.length > 4 ? split[4] : "");
            break;
        default:
            task = null;
        }

        if (task != null && "[X]".equals(split[1])) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Converts a Task object into a string to save in the file.
     */
    private String convertTaskToLine(Task task) {
        if (task instanceof Todo) {
            return "T | " + task.getStatusIcon() + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + task.getStatusIcon() + " | " + d.getDescription() + " | " + d.getDeadline();
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + task.getStatusIcon() + " | " + e.getDescription() + " | " + e.getStart() + " | " + e.getEnd();
        }
        return ""; // fallback for unknown task type
    }
}

