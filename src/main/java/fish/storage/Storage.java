package fish.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fish.task.Deadline;
import fish.task.Event;
import fish.task.Task;
import fish.task.Todo;

/**
 * Handles reading from and writing to the task storage file.
 */
public class Storage {
    private final File dataFile;

    /**
     * Constructs a storage handler within the given directory using the specified file name.
     * The directory is created if it does not already exist.
     *
     * @param dir      directory containing the storage file
     * @param filename storage file name
     */
    public Storage(String dir, String filename) {
        this(new File(dir, filename));
    }

    public Storage(String path) {
        this(new File(path));
    }

    private Storage(File file) {
        this.dataFile = prepareFile(file);
    }
    private static File prepareFile(File file) {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            boolean created = parent.mkdirs();
            if (!created && !parent.exists()) {
                throw new RuntimeException("Cannot create storage directory: " + parent);
            }
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Cannot create storage file: " + e.getMessage(), e);
            }
        }
        return file;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!dataFile.exists()) {
            return tasks;
        }

        try (Scanner sc = new Scanner(dataFile, "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.isEmpty()) {
                    tasks.add(parseLine(line));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot load tasks: " + e.getMessage(), e);
        }
        return tasks;
    }


    public void save(List<Task> tasks) {
        try (PrintWriter out = new PrintWriter(new FileWriter(dataFile, false))) {
            for (Task t : tasks) {
                out.println(t.toFileString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot save tasks: " + e.getMessage(), e);
        }
    }


    private Task parseLine(String s) {
        String[] p = s.split("\\s*\\|\\s*");
        String type = p[0];
        boolean done = "1".equals(p[1]);
        Task t;
        switch (type) {
        case "T": t = new Todo(p[2]);
        break;
        case "D": t = new Deadline(p[2], p[3]);
        break;
        case "E": t = new Event(p[2], p[3], p[4]);
        break;
        default: throw new IllegalArgumentException("Unknown type: " + type);
        }
        if (done) {
            t.markAsDone();
        }
        return t;
    }
}
