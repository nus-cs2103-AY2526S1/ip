package geegar.storage;

import geegar.task.Deadline;
import geegar.task.Event;
import geegar.task.Task;
import geegar.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Reads from the file based on storage and extracts out information on the list of tasks
 */
public class TaskReader {
    private static final Path TASKS_FILE = Paths.get("data", "tasks.txt");

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = TASKS_FILE.toFile();

        if (!file.exists()) {
            return taskList;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Task task = stringToTask(line);
                    if (task != null) {
                        taskList.add(task);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error loading Tasks: " + e.getMessage());
        }

        return taskList;
    }

    /**
     * Returns a geegar.task.Task Object of the respective types (todo, deadline, event)
     * based on the txt file input written in the following format
     * [a][b] description (with possible deadline / event)
     * where a is equal to the task type 'T', 'D', 'E'
     * where b is the indication if the task has been marked done with a 'X'
     *
     * @param taskString - based on each line (each geegar.task.Task)
     * @return The actual geegar.task.Task type based on the type
     */

    private static Task stringToTask(String taskString) {
        try {
            // format of task is [T][X] of 6 length
            if (taskString.length() < 6) return null;

            char taskType = taskString.charAt(1);
            boolean isDone = taskString.charAt(4) == 'X';

            Task task = null;

            if (taskType == 'T') {
                // [T][X] description
                String description = taskString.substring(6);
                task = new Todo(description, isDone);

            } else if (taskType == 'D') {
                // [D][X] description (by: date);
                String content = taskString.substring(6).trim();
                int byIndex = content.lastIndexOf(" (by: ");
                if (byIndex != -1) {
                    String description = content.substring(0, byIndex).trim();
                    // account for closing bracket
                    String byString = content.substring(byIndex + 6, content.length() - 1).trim();

                    // Accept format "dd/MM/yyyy HHmm" like 27/08/2025 0600
                    LocalDateTime by = LocalDateTime.parse(byString, Task.SAVE_FORMATTER);

                    task = new Deadline(description, by, isDone);
                }
            } else if (taskType == 'E') {
                // [E][X] description (from: start to: end)
                String content = taskString.substring(6).trim();
                int fromIndex = content.lastIndexOf(" (from: ");
                if (fromIndex != -1) {
                    String description = content.substring(0, fromIndex).trim();
                    String timeInfo = content.substring(fromIndex + 8, content.length() - 1).trim();
                    int toIndex = timeInfo.lastIndexOf(" to: ");
                    if (toIndex != -1) {
                        String fromString = timeInfo.substring(0, toIndex).trim();
                        String toString = timeInfo.substring(toIndex + 5).trim();

                        // Accept format "dd/MM/yyyy HHmm" like 27/08/2025 0600
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime from = LocalDateTime.parse(fromString, Task.SAVE_FORMATTER);
                        LocalDateTime to = LocalDateTime.parse(toString, Task.SAVE_FORMATTER);

                        task = new Event(description, from, to, isDone);
                    }
                }
            }

            return task;

        } catch (Exception e) {
            System.err.println("Error parsing geegar.task.Task: " + taskString);
            return null;
        }
    }
}
