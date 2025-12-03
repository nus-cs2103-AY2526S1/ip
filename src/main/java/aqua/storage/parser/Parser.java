package aqua.storage.parser;

import aqua.exception.IllegalDataException;
import aqua.task.Task;
import aqua.task.Task.Priority;

/**
 * Parses task data from strings.
 */
public class Parser {
    /**
     * Parse task given a input string
     *
     * @param input The user's input
     * @return task specified by the input string
     * @throws IllegalDataException If the data is illegal or cannot be parsed
     */
    public static Task parse(String input) throws IllegalDataException {
        String[] parts = input.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        int priority = Integer.parseInt(parts[2].trim());
        String rest = parts[3].trim();
        Task task;

        switch (type) {
        case "T" -> {
            task = new TodoParser().parse(rest);
        }
        case "D" -> {
            task = new DeadlineParser().parse(rest);
        }
        case "E" -> {
            task = new EventParser().parse(rest);
        }
        default -> {
            throw new IllegalDataException("Failed to read data");
        }
        }

        if (isDone) {
            task.markDone();
        }

        if (priority != -1) {
            task.setPriority(Priority.values()[priority]);
        }

        return task;
    }
}
