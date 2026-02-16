package jibjab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles persistence of tasks to and from a file on disk.
 * The file format matches the output of TaskList#toString() and is parsed back into Task objects.
 */
public class Storage {
    private static final String UNKNOWN_TASK_TYPE_MSG = "Unknown task type: ";
    private final String filePath;

    /**
     * Creates a Storage bound to the given file path.
     *
     * @param filePath absolute or relative path to the tasklist data file
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isBlank() : "Storage filePath must not be null/blank";
        this.filePath = filePath;
    }

    /**
     * Saves all tasks to the configured file. If the list is empty, nothing is written.
     *
     * @param tasks the TaskList to persist
     * @throws JibJabException if writing fails, typically due to missing folders or IO errors
     */
    public void saveTasks(TaskList tasks) throws JibJabException {
        assert tasks != null : "TaskList to save must not be null";
        if (tasks.isEmpty()) {
            return;
        }
        File file = new File(this.filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs() && !parent.exists()) {
                throw new JibJabException("Failed to create parent directories for data file: " + parent);
            }
        }
        try (PrintWriter pw = new PrintWriter(this.filePath)) {
            pw.print(tasks);
        } catch (FileNotFoundException e) {
            throw new JibJabException("Data file not found: " + file.getPath());
        }
    }

    /**
     * Loads tasks from the configured file if it exists.
     * Parses each line to reconstruct Task instances of the appropriate type.
     *
     * @return a list of tasks loaded from storage; empty if no file exists
     * @throws JibJabException if the file exists but cannot be read
     */
    public ArrayList<Task> loadTasks() throws JibJabException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);
        if (!file.exists()) {
            return tasks;
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line == null || line.length() < 7) {
                    continue; // skip malformed line
                }
                try {
                    String taskType = Character.toString(line.charAt(1));
                    String taskStatus = Character.toString(line.charAt(4));
                    String taskDesc = line.substring(7);

                    Task parsed = parseTask(taskType, taskStatus, taskDesc);
                    if (parsed != null) {
                        tasks.add(parsed);
                    }
                } catch (Exception ex) {
                    // skip malformed line
                }
            }
        } catch (IOException e) {
            throw new JibJabException("Failed to load from file: " + e.getMessage());
        }
        return tasks;
    }

    private Task parseTask(String taskType, String taskStatus, String taskDesc) {
        Task task = null;
        switch (taskType) {
        case "T":
            task = new ToDo(taskDesc);
            break;
        case "D":
            String[] splitD = taskDesc.split(" \\(by: ");
            assert splitD.length == 2 : "Stored deadline must contain ' (by: '";
            String taskBy = splitD[1].substring(0, splitD[1].indexOf(")"));
            task = new Deadline(splitD[0], taskBy);
            break;
        case "E":
            String[] splitE = taskDesc.split(" \\(from: ");
            assert splitE.length == 2 : "Stored event must contain ' (from: '";
            String[] fromTo = splitE[1].split(" to: ");
            assert fromTo.length == 2 : "Stored event must contain ' to: '";
            String from = fromTo[0];
            String to = fromTo[1].substring(0, fromTo[1].indexOf(")"));
            task = new Event(splitE[0], from, to);
            break;
        default:
            System.err.println(UNKNOWN_TASK_TYPE_MSG + taskType);
            break;
        }
        if (task != null && "X".equals(taskStatus)) {
            task.setDone();
        }
        return task;
    }
}
