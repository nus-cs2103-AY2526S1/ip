import jdk.jfr.Event;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/// This class handles the storage and updating of tasks in gokschat.txt
///
/// @author Ravichandran Gokul
public class Storage {
    // New fields declared
    private String filePath;

    /**
     * Constructs a new {@code Storage} object with the file path.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> intialiseTaskList() throws BadFileException {
        List<Task> listOfTasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            // Ensure parent directories exist
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            // Ensure file exists
            if (!file.exists()) {
                file.createNewFile();
                return listOfTasks;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseLineToTask(line);
                if (task != null) {
                    listOfTasks.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new BadFileException("      Oops! I couldn't read your tasks. Imma start with a fresh list yea...");
        }

        return listOfTasks;
    }

    private Task parseLineToTask(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String taskName = parts[2];
        Task task = null;

        if (taskType.equals("T")) {
            task = new Task(taskName);
        } else if (taskType.equals("D")) {
            String deadline = parts[3].substring(3);
            task = new DeadlineTask(taskName, deadline);
        } else if (taskType.equals("E")) {
            String from = parts[3].substring(5);
            String to = parts[4].substring(3);
            task = new EventTask(taskName, from, to);
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }
}
