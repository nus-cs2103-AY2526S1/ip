package evansbot.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles reading and writing tasks to and from persistent storage.
 * Tasks are saved in a text file with a specific format for each task type (ToDo, Deadline, Event).
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance that reads from and writes to the specified file path.
     *
     * @param filePath Path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file into an ArrayList of Task objects.
     * If the file or parent directories do not exist, they are created.
     *
     * @return ArrayList of tasks loaded from storage.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        //Make folder if it does not exist
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //Make file if it does not exist
        if (!file.exists()) {
            file.createNewFile();
            return tasks;
        }

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (type) {
            case "T": // Case of a ToDo
                task = new ToDo(description);
                break;
            case "D": // Case of a Deadline
                task = new Deadline(description, parts[3]);
                break;
            case "E": // Case of a Event
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                continue;
            }

            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        sc.close();
        return tasks;
    }

    /**
     * Saves a list of tasks to the storage file.
     * Each task is formatted according to its type.
     *
     * @param tasks ArrayList of tasks to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(formatTask(task) + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Formats a task into a string suitable for saving in the storage file.
     *
     * @param task Task to format.
     * @return Formatted string representing the task.
     */
    private String formatTask(Task task) {
        String status = task.isDone ? "1" : "0";
        if (task instanceof ToDo) {
            return "T | " + status + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            String byStr;
            if (d.getByDate() != null) {
                byStr = String.valueOf(d.byDate);
            } else {
                byStr = d.byRaw;
            }
            return "D | " + status + " | " + d.description + " | " + byStr;
        } else if (task instanceof Event) {
            Event e = (Event) task;
            String fromStr;
            String toStr;
            if (e.getFromDate() != null && e.getToDate() != null) {
                fromStr = String.valueOf(e.fromDate);
                toStr = String.valueOf(e.toDate);
            } else {
                fromStr = e.fromRaw;
                toStr = e.toRaw;
            }
            return "E | " + status + " | " + e.description + " | " + fromStr + " | " + toStr;
        }
        return "";
    }

}
