package siri.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import siri.task.DeadlineTask;
import siri.task.EventTask;
import siri.task.Task;
import siri.task.ToDoTask;


/**
 * Storage class
 */
public class Storage {
    public final Path filePath;

    public Storage(String relativePath) {
        this.filePath = Paths.get(relativePath);
    }

    /**
     * make sure the file and its parent directory exists
     * @throws IOException
     */
    public void ensureFile() throws IOException {
        Path parentPath = filePath.getParent();
        if (parentPath != null && !Files.exists(parentPath)) {
            Files.createDirectories(parentPath);
        }
        if (!Files.exists(this.filePath)) {
            Files.createFile(filePath);
        }
        assert parentPath != null : "ParentPath of the file is null";
    }

    /**
     * read all the tasks in the file and store them in a list
     * @return a list of tasks
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            ensureFile();
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Task t = parse(line);
                if (t != null) {
                    tasks.add(new Task(line));
                }
            }
        } catch (IOException e) {
            ConsoleLogger.printLine("Error saving data" + e.getMessage());
        }
        return tasks;
    }

    /**
     * save all the tasks in the file
     * @param tasks the list of tasks
     */
    public void save(List<Task> tasks) {
        try {
            ensureFile();
            try (BufferedWriter w = Files.newBufferedWriter(filePath)) {
                for (Task t : tasks) {
                    w.write(format(t));
                    w.newLine();
                }
            }
        } catch (IOException e) {
            ConsoleLogger.printLine("Error saving data: " + e.getMessage());
        }

    }

    /**
     * Format a task so it can be displayed in the expected behavior
     * @param t the task
     * @return the string of the formatted task
     */
    public String format(Task t) {
        String done = t.isDone() ? "1" : "0";
        assert done.equals("1") || done.equals("0") : "invalid done status";
        if (t instanceof ToDoTask) {
            return "T | " + done + " | " + t.getDescription();
        } else if (t instanceof DeadlineTask) {
            DeadlineTask d = (DeadlineTask) t;
            return "D | " + done + " | " + d.getDescription() + " | " + d.getDeadline();
        } else if (t instanceof EventTask) {
            EventTask e = (EventTask) t;
            return "E | " + done + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
        } else {
            return "Unknown task";
        }
    }

    /**
     * Parse a line in the file so it can be converted to a task
     * @param line a line of string i the file
     * @return the task
     */
    private Task parse(String line) {
        String[] p = line.split(" \\| ");
        if (p.length < 3) {
            return null;
        }

        String type = p[0].trim();
        assert type != null : "Invalid type";
        boolean done = p[1].trim().equals("1");
        String desc = p[2].trim();
        assert desc != null && !desc.isBlank() : "Invalid desc";
        switch (type) {
        case "T": {
            Task t = new ToDoTask(desc);
            t.setDone(done);
            return t;
        }
        case "D": {
            if (p.length < 4) {
                return null;
            }
            String by = p[3].trim();
            Task t = new DeadlineTask(desc, by);
            t.setDone(done);
            return t;
        }
        case "E": {
            if (p.length < 5) {
                return null;
            }
            String from = p[3].trim();
            String to = p[4].trim();
            Task t = new EventTask(desc, from, to);
            t.setDone(done);
            return t;
        }

        default:
            return null;
        }
    }
}

