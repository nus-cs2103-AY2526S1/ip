package application;

import java.util.ArrayList;

import exception.RomidasException;
import tasks.DeadlineTask;
import tasks.Event;
import tasks.Task;
import tasks.TodoTask;

/**
 * Converts between text representations and Task objects for file storage.
 * Handles the parsing and formatting of task data for persistence.
 */
public class TextTaskConverter  {
    /**
     * Converts a list of text lines back to Task objects.
     * Parses each line according to the storage format and creates appropriate Task instances.
     * Skips empty lines and handles different task types (T, D, E).
     *
     * @param lines ArrayList of text lines representing tasks in storage format.
     * @return ArrayList of reconstructed Task objects.
     * @throws RomidasException If the text format is invalid or unrecognized task type.
     */
    public static ArrayList<Task> convertToTask(ArrayList<String> lines) throws RomidasException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines){
            if (line.trim().isEmpty()) {
                continue; // Skip empty lines
            }
            String[] parts = line.split("\\|");
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].strip();
            }
            Task task = switch (parts[0]) {
                case "T" -> TodoTask.toTask(parts);
                case "D" -> DeadlineTask.toTask(parts);
                case "E" -> Event.toTask(parts);
                default -> throw new RomidasException("I'm sorry, I don't recognise that command. "
                        + "Try one of event, todo, deadline");
            };
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Converts a list of Task objects to text lines for storage.
     * Formats each task using its toText() method for file persistence.
     *
     * @param tasks ArrayList of Task objects to convert.
     * @return ArrayList of text lines representing the tasks.
     * @throws RomidasException If any task fails to convert to text format.
     */
    public static ArrayList<String> convertToText(ArrayList<Task> tasks) throws RomidasException {
        ArrayList<String> lines = new ArrayList<>();
        for(Task task : tasks){
            String line = task.toText();
            lines.add(line);
        }
        return lines;
    }
}
