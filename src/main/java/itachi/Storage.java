package itachi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import itachi.task.Deadline;
import itachi.task.Event;
import itachi.task.Task;
import itachi.task.Todo;

/**
 * Handles reading and writing of tasks to a file on disk
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    //This header comment is AI generated.
    /**
     * Saves the list of tasks to the file specified by filePath.
     * @param tasks the list of tasks to save
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks) {
            fw.write(t.toSaveFormat() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * If the file or its parent directories do not exist, they are created.
     * Returns an empty list if the file was newly created.
     * @return a list of tasks loaded from the file
     * @throws IOException if there is an error reading from file
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
            return tasks;
        }

        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String desc = parts[2];

            Task t;
            switch (type) {
            case "T":
                t = new Todo(desc);
                break;
            case "D":
                t = new Deadline(desc, parts[3]);
                break;
            case "E":
                t = new Event(desc, parts[3], parts[4]);
                break;
            default:
                continue;
            }
            if (isDone) {
                t.markAsDone();
            }
            tasks.add(t);
        }
        sc.close();
        return tasks;
    }
}
