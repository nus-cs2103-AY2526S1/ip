package whchenyicn.ui;

import whchenyicn.task.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path file;
    private static final String DATA_DIR    = "data";
    private static final String DEFAULT_FILE = "whchenyicn.txt";

    /**
     * extracts data from whchenyicn.txt
     */
    public Storage() {
        this(Paths.get(DATA_DIR, DEFAULT_FILE));
    }

    public Storage(Path file) {
        this.file = file;
    }

    /**
     * Loads data from whchenyicn.txt and adds to whchenyicn.task.TaskList
     * @return returns a list of tasks
     */
    public TaskList load() {
        TaskList taskList = new TaskList();
        try {
            if (file.getParent() != null && !Files.exists(file.getParent())) {
                Files.createDirectories(file.getParent());
            }

            if (!Files.exists(file)) {
                return taskList;
            }
            for (String line : Files.readAllLines(file)) {
                Task t = parse(line);
                if (t != null) taskList.add(t);
            }
        } catch (IOException e) {
            System.out.println("Failed to read saved files: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * saves the tasks after each change
     * @param taskList takes in a list of tasks and adds it to whchenyicn.whchenyicn.txt
     * @throws IOException throws error if file doesn't exist
     */
    public void save(TaskList taskList) throws IOException{
        if (file.getParent() != null && !Files.exists(file.getParent())) {
            Files.createDirectories(file.getParent());
        }

        List<String> lines = new ArrayList<>();

        for (Task t : taskList.asList()) {
            lines.add(t.toSave());
        }

        Files.write(file, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private Task parse(String line) {
        Task t;
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0].trim();
        boolean isDone = "1".equals(parts[1].trim());
        String desc = parts[2].trim();
        switch (type) {
        case "T":
            t = new ToDo(desc);
             break;
        case "D":
            if (parts.length <4) {
                return null;
            }
            try {
                t = new Deadline(desc, parts[3].trim());
                break;
            } catch (IllegalArgumentException e) {
                return null;
            }
        case "E":
            if (parts.length < 5) {
                return null;
            }
            t = new Event(desc, parts[3].trim(), parts[4].trim());
            break;
        default:
            return null;
        }

        if (isDone) {
            t.markDone();
        } else {
            t.unmark();
        }

        return t;
    }
}
