package HawkerUncle.storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.task.Task;
import HawkerUncle.task.ToDo;
import HawkerUncle.task.Deadline;
import HawkerUncle.task.Event;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a storage system that loads and saves tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Initializes the Storage object with the given file path.
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of tasks from the storage file.
     * If the file does not exist, it will be created.
     * The file format follows this structure for each task:
     *  - T | isDone | description
     *  - D | isDone | description | by
     *  - E | isDone | description | from | to
     * @return A list of tasks loaded from the file.
     * @throws IOException If there is an error reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);

        f.getParentFile().mkdirs();
        if (!f.exists()) {
            f.createNewFile();
        }

        Scanner s = new Scanner(f);

        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
            case "T":
                tasks.add(new ToDo(description, isDone));
                break;
            case "D":
                assert parts.length == 4 : "Deadline task line must have 4 parts" + line;
                LocalDateTime by = parseDateTime(parts[3]);
                tasks.add(new Deadline(description, by, isDone));
                break;
            case "E":
                assert parts.length == 5 : "Event task line must have 5 parts" + line;
                LocalDateTime from = parseDateTime(parts[3]);
                LocalDateTime to = parseDateTime(parts[4]);
                tasks.add(new Event(description, from, to, isDone));
                break;
            default:
                assert false : "Unknown task type in storage file: " + type;
            }
        }

        s.close();
        return tasks;
    }

    /**
     * Saves the current list of tasks to the storage file.
     * Each task is saved in the following format:
     * - T | isDone | description
     * - D | isDone | description | deadline
     * - E | isDone | description | from | to
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); ++i){
            fw.write(tasks.get(i).toSaveFormat() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     * The expected format is "yyyy-MM-dd Hhmm"
     * @param dateTimeStr The date-time string to be parsed.
     * @return The parsed LocalDateTime object
     */
    private LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

}
