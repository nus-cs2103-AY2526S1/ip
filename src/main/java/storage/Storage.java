package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.GenieweenieException;
import task.Deadline;
import task.Events;
import task.Task;
import task.Todo;

/**
 * Handles loading and saving of tasks to and from a file.
 */
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from file.
     *
     * @return list of tasks
     * @throws IOException if file cannot be read
     */
    public List<Task> load() throws IOException, GenieweenieException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // create "data" folder if missing
        if (file.createNewFile()) {
            System.out.println("New file created: " + file.getAbsolutePath());
            return new ArrayList<>(); // empty list on first run
        }

        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split(" \\| ");
            assert parts.length >= 3 : "Corrupted line in save file: not enough parts";

            Task t;
            switch (parts[0]) {
            case "T":
                t = new Todo(parts[2]);
                break;
            case "D":
                assert parts.length >= 4 : "Deadline entry missing fields";
                t = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                assert parts.length >= 5 : "Event entry missing fields";
                t = new Events(parts[2], parts[3], parts[4]);
                break;
            default:
                continue;
            }

            if (parts[1].equals("1")) {
                t.markAsDone();
            }
            tasks.add(t);
        }
        sc.close();
        return tasks;
    }

    /**
     * Saves tasks to file.
     *
     * @param tasks list of tasks
     * @throws IOException if file cannot be written
     */
    public void save(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks) {
            fw.write(t.toSaveFormat() + System.lineSeparator());
        }
        fw.close();
    }
}
