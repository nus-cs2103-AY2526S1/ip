package david.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import david.exception.InvalidDateTimeException;
import david.task.Deadline;
import david.task.Event;
import david.task.Task;
import david.task.TaskList;
import david.task.Todo;

/**
 * Represents a location to store data from the user.
 */
public class Storage {
    private final Path filePath;

    /**
     * Initialises a Storage object.
     *
     * @param filePath File path of data file.
     */
    public Storage(String filePath) {
        assert filePath!= null : "Filepath should never be null";
        this.filePath = Paths.get(filePath);

        checkFileAndFolderExists();
    }

    /**
     * Checks if the data.txt file and parent directory exists. If not, creates them.
     */
    private void checkFileAndFolderExists() {
        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.err.println("Eh you got an error creating the data file: " + e.getMessage());
        }
    }

    /**
     * Loads the users data from the previous session from data.txt.
     *
     * @return The list of tasks from the user's previous sesssion.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task t = parseTask(line);
                if (t != null) {
                    tasks.add(t);
                }
            }
        } catch (IOException e) {
            System.err.println("Eh you got an error loading the tasks:" + e.getMessage());
        } catch (InvalidDateTimeException e) {
            System.err.println("Eh one of the tasks have the wrong datetime format:" + e.getMessage());
        }
        assert tasks != null : "Loaded tasks should never be null";
        return tasks;
    }

    /**
     * Process task string and create a new task based on the task type.
     *
     * @param line Task string.
     * @return Task in the specific task type.
     */
    private Task parseTask(String line) throws InvalidDateTimeException {
        assert line != null : "Line should never be null";
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];

        Task task = null;
        switch (taskType) {
        case "T":
            task = new Todo(desc);
            break;
        case "D":
            task = new Deadline(desc, parts[3]);
            break;
        case "E":
            task = new Event(desc, parts[3], parts[4]);
            break;
        }
        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Saves user's TaskList into data.txt.
     *
     * @param tl User's current TaskList.
     */
    public void save(TaskList tl) {
        assert tl != null : "TaskList should never be null";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (Task t : tl.getTasks()) {
                bw.write(formatTask(t));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Formats the Task into the string format for saving into data.txt.
     *
     * @param t Task.
     * @return String of task in save data format.
     */
    private String formatTask(Task t) {
        assert t != null : "Task should never be null";
        String status = t.isDone() ? "1" : "0";

        DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        if (t instanceof Todo) {
            return "T | " + status + " | " + t.getText();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D | " + status + " | " + d.getText() + " | " + d.getBy().format(saveFormat);
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return "E | " + status + " | " + e.getText() + " | " + e.getFrom().format(saveFormat) + " | " + e.getTo().format(saveFormat);
        }
        assert false : "Code should never reach here";
        return "";
    }
}
