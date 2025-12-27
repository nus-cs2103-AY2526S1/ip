package smartstelle;

import javafx.application.Platform;
import java.io.IOException;

/**
 * The Parser class is responsible for interpreting user input commands
 * and executing corresponding actions on the TaskList, Storage, and UI.
 * 
 * It handles commands such as adding tasks, marking/unmarking tasks,
 * deleting tasks, listing tasks, and exiting the program.
 */
public class Parser {

    /**
     * Parses and executes a user command.
     *
     * @param input   The user input command as a string.
     * @param list    The TaskList containing all current tasks.
     * @param storage The Storage object for saving tasks to file.
     * @param ui      The Ui object for displaying messages and lines.
     * @return -1 if the input is "bye" (to signal program exit), otherwise 0.
     * @throws IOException     If an error occurs when saving to storage.
     * @throws StelleException If the user input is invalid or incomplete (e.g.,
     *                         missing deadline or event duration).
     */
    public static String parse(String input, TaskList list, Storage storage, Ui ui) throws IOException, StelleException {
        assert input != null && !input.trim().isEmpty() : "User input must not be null or empty";

        // Use of ChatGPT to change long if-else to switch
        String[] tokens = input.trim().split("\\s+", 2);
        String command = tokens[0];
        String args = tokens.length > 1 ? tokens[1] : "";

        switch (command) {
            case "bye" -> {
                return handleBye();
            }
            case "list" -> {
                return list.display();
            }
            case "mark" -> {
                return handleMark(args, list, storage);
            }
            case "unmark" -> {
                return handleUnmark(args, list, storage);
            }
            case "todo" -> {
                return handleTodo(args, list, storage);
            }
            case "deadline" -> {
                return handleDeadline(args, list, storage);
            }
            case "event" -> {
                return handleEvent(args, list, storage);
            }
            case "delete" -> {
                return handleDelete(args, list, storage);
            }
            case "find" -> {
                return list.getTasksWithKeyword(args);
            }
            default -> {
                return "What are you saying..............\n";
            }
        }
    }

    private static String handleBye () {
        Platform.exit();
        return "bye";
    }

    private static String handleMark (String arg, TaskList list, Storage storage) throws IOException, StelleException {
        int num = parseIntChecked(arg);
        String msg = list.mark(num);
        storage.save(list.getAll());
        return msg;
    }

    private static String handleUnmark (String arg, TaskList list, Storage storage) throws IOException, StelleException {
        int num = parseIntChecked(arg);
        String msg = list.unmark(num);
        storage.save(list.getAll());
        return msg;
    }

    private static String handleTodo (String arg, TaskList list, Storage storage) throws IOException, StelleException {
        if (arg.isBlank()) throw new StelleException("Todo description cannot be empty.");
        String msg = list.add(new ToDo(arg, false));
        storage.save(list.getAll());
        return msg;
    }

    private static String handleDeadline (String arg, TaskList list, Storage storage) throws IOException, StelleException {
        String[] parts = arg.split("\\s*/by\\s+", 2);
        if (parts.length < 2) throw new StelleException("Why no deadline.");
        String msg = list.add(new Deadline(parts[0], parts[1], false));
        storage.save(list.getAll());
        return msg;
    }

    private static String handleEvent (String arg, TaskList list, Storage storage) throws IOException, StelleException {
        String[] parts = arg.split(" /");
        if (parts.length < 3) throw new StelleException("Why you dk how long.");
        String msg = list.add(new Event(parts[1].substring(5), parts[2].substring(3), parts[0], false));
        storage.save(list.getAll());
        return msg;
    }

    private static String handleDelete (String arg, TaskList list, Storage storage) throws IOException, StelleException {
        int num = parseIntChecked(arg);
        String msg = list.delete(num);
        storage.save(list.getAll());
        return msg;
    }

    // Helper to validate numbers safely
    private static int parseIntChecked(String arg) throws StelleException {
        try {
                return Integer.parseInt(arg.trim());
        } catch (NumberFormatException e) {
                throw new StelleException("Please enter a valid task number.");
        }
    }
}
