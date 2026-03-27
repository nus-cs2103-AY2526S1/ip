package amogus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tasks.Deadlines;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

/**
 * Represents a class that modifies the hard disk file
 * for displaying user tasks, allowing for saved memory.
 */
public class FileStorage {

    private final String path;

    public FileStorage(String path) {
        this.path = path;
    }

    /**
     * Takes in local memory Tasks.TaskList to write onto a txt file
     * as a hard disk memory for future use as the program restarts
     * on a fresh new call without any local memory.
     *
     * @param tasks list of tasks
     */
    public void saveTasks(TaskList tasks) {
        try {
            File file = new File(path);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter fw = new FileWriter(file)) {
                for (int i = 0; i < tasks.getSize(); i++) {
                    Task task = tasks.get(i);
                    fw.write(task.toString() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Reads from hard disk memory stored txt file onto local memory
     * to be displayed when list command is called.
     *
     * @return Tasks.TaskList with correctly loaded tasks from txt file
     * @throws AmogusException improper date/time format
     * @throws IOException ioexception
     */
    public TaskList loadTasks() throws AmogusException, IOException {
        TaskList tasks = new TaskList();
        File f = new File(path);

        if (!f.exists()) {
            f.createNewFile();
            return tasks;
        }

        try (Scanner s = new Scanner(f)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                Task task = parseTask(line);
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Parses the task from given user input into its
     * appropriate subclasses, and returns the correct task.
     *
     * @param line current line in the txt file
     * @return a Tasks.Task from txt file
     * @throws AmogusException improper date/time format
     */
    public Task parseTask(String line) throws AmogusException {
        assert line != null : "empty line cannot be parsed";

        String[] parts = line.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String tag = "";

        for (String part : parts) {
            if (part.startsWith("#")) {
                tag = part.substring(1).trim();
            }
        }

        Task task;
        switch (type) {
        case "T":
            task = new ToDo(parts[2]);
            break;
        case "D":
            if (parts.length < 4) {
                throw new AmogusException("Invalid deadline format");
            }
            task = new Deadlines(parts[2], parts[3]);
            break;
        case "E":
            if (parts.length < 5) {
                throw new AmogusException("Invalid event format");
            }
            task = new Event(parts[2], parts[3], parts[4]);
            break;

        default:
            throw new AmogusException("Unknown task type: " + type);
        }

        if (isDone) {
            task.mark();
        }

        if (!tag.isEmpty()) {
            task.tag(tag);
        }

        return task;
    }
}
