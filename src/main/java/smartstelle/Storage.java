package smartstelle;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Handles reading from and writing to the storage file for the SmartStelle
 * application.
 * <p>
 * Provides methods to load tasks from a file into memory and save tasks from
 * memory to a file.
 */
public class Storage {
    private String path;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param path The file path for storing task data.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads tasks from the storage file into an ArrayList of Task objects.
     * <p>
     * If the file does not exist, it creates the file and returns an empty list.
     * Supports loading ToDo, Deadline, and Event tasks based on the file format.
     *
     * @return An ArrayList containing all tasks loaded from the file.
     * @throws IOException     If an error occurs while reading the file.
     * @throws StelleException If there is an error creating Task objects from file
     *                         data.
     */
    public ArrayList<Task> load() throws IOException, StelleException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) { // no file yet
            file.getParentFile().mkdir();
            file.createNewFile();
            return tasks;
        }

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.split(" \\| ");

            String type = split[0];
            String completion = split[1];
            String taskName = split[2];
            boolean isCompleted = completion.equals("1");

            if (type.equals("T")) {
                tasks.add(new ToDo(taskName, isCompleted));
            } else if (type.equals("D")) {
                tasks.add(new Deadline(taskName, split[3], isCompleted));
            } else {
                tasks.add(new Event(split[3], split[4], taskName, isCompleted));
            }
        }

        sc.close();
        return tasks;
    }

    /**
     * Saves a list of tasks to the storage file.
     * <p>
     * Overwrites any existing content in the file. Each task is written using
     * its own save format defined by the toSaveFormat() method.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(this.path);
        for (Task task : tasks) {
            writer.write(task.toSaveFormat() + System.lineSeparator());
        }

        writer.close();
    }
}
