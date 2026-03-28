package tweety.storage;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

import tweety.exceptions.TweetyException;

import tweety.tasks.TaskList;

import tweety.tasks.Deadline;
import tweety.tasks.Event;
import tweety.tasks.Task;
import tweety.tasks.ToDo;


/**
 * Handles storage operations for tasks such as saving to and loading from file.
 */
public class Storage {

    private final Path filePath;
    private final Path directoryPath;

    public Storage() {
        this(Paths.get("data/Tweety.txt"), Paths.get("data/"));
    }

    public Storage(Path filePath, Path directoryPath) {
        assert filePath != null : "filePath should not be null";
        assert directoryPath != null : "directoryPath should not be null";

        this.filePath = filePath;
        this.directoryPath = directoryPath;
    }

    private void ensureDirectoryExists() throws IOException {
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
    }

    /**
     * Saves the input list of tasks in the storage file.
     * Creates the directory structure if it doesn't exist.
     *
     * @param tasks the list of tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        try {
            // Check if directory exists first
            ensureDirectoryExists();

            List<String> lines = new ArrayList<>();

            // Convert each task to string format
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                lines.add(convertTaskToFileString(tasks.getTask(i)));
            }

            // Write to file
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the Tweety.txt file.
     *
     * @return a list of tasks loaded from the file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!Files.exists(filePath)) {
                return tasks;
            }

            // Read the tasks from the string file
            List<String> lines = Files.readAllLines(filePath);
            for (int i = 0; i < lines.size(); i++) {
                // Convert the string to task object
                Task currTask =  parseTaskFromString(lines.get(i));

                // Add the task to the list of tasks to return
                if (currTask != null) {
                    tasks.add(currTask);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Converts tasks into strings to be saved in the storage file.
     *
     * @param task task to convert to string to be saved in the storage file.
     * @return String of the task.
     */
    private String convertTaskToFileString(Task task) {
        if (task instanceof ToDo) {
            return "T | " + task.getStatusIcon() + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + task.getStatusIcon() + " | " + task.getDescription() + " | "
                    + ((Deadline) task).getDeadline();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + task.getStatusIcon() + " | " + task.getDescription()
                    + " | " + event.getEventStart() + " | " + event.getEventEnd();
        }
        return "";
    }

    /**
     * Parses tasks from string to tasks when "list" command is used.
     *
     * @return Task object by reading the strings in the file.
     */
    private Task parseTaskFromString(String line) throws TweetyException {
        // Split the string input into its type, isDone and description
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("[X]");
        String description = parts[2];

        // Return the string as respective task objects
        switch (type) {
            case "T":
                ToDo todo = new ToDo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                if (parts.length < 4) return null;
                try {
                    Deadline deadline = new Deadline(description, parts[3]);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    return deadline;
                } catch (TweetyException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            case "E":
                if (parts.length < 5) return null;
                Event event = new Event(description, parts[3], parts[4]);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
            default:
                return null;
        }
    }
}
