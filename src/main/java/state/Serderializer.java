package state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import misc.PepeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Class responsible for serializing and deserializing the task list from and to the file containing the application's
 * state.
 */
public class Serderializer {
    /**
     * Maps a list of Strings into their corresponding tasks.
     * @param lines List of strings from the task list file.
     * @return A list of the corresponding tasks.
     * @throws PepeException if invalid/corrupted file input.
     */
    public static List<Task> deserializeTasks(List<String> lines) throws PepeException {
        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            parts = Arrays.stream(line.split("\\|"))
                        .map(String::strip)
                        .toArray(String[]::new);
            if (parts.length < 3) {
                throw new PepeException("Invalid task format. Expected at least 3 parts but got " + parts.length);
            }
            Task task;
            // Exceptions thrown here will bounce up
            task = switch (parts[0].strip()) {
            case "T" -> Todo.fromFileInput(parts);
            case "D" -> Deadline.fromFileInput(parts);
            case "E" -> Event.fromFileInput(parts);
            default -> throw new PepeException("Invalid task format. Expected one of 'T', 'D' or 'E' but got "
                    + parts[0]);
            };
            tasks.add(task);
        }
        return tasks;
    }
}
