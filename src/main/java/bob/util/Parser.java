package bob.util;

import bob.tasks.Deadline;
import bob.tasks.Event;
import bob.tasks.TaskList;
import bob.tasks.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String parseInput(String input, TaskList tasks) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        return switch (command) {
            case "mark" -> handleMark(parts, tasks);
            case "unmark" -> handleUnmark(parts, tasks);
            case "list" -> handleList(tasks);
            case "todo" -> handleTodo(parts, tasks);
            case "deadline" -> handleDeadline(input, parts, tasks);
            case "event" -> handleEvent(input, parts, tasks);
            case "delete" -> handleDelete(parts, tasks);
            case "find" -> handleFind(parts, tasks);
            case "update" -> handleUpdate(input, parts, tasks);
            case "help" -> handleHelp(parts);
            default -> input + "\nType `help` to see available commands.";
        };
    }

    // --- Handlers below ---

    private static String handleMark(String[] parts, TaskList tasks) {
        if (parts.length < 2) {
            return "Usage: mark <index>";
        }
        try {
            int index = Integer.parseInt(parts[1]);
            return tasks.markItem(index);
        } catch (NumberFormatException e) {
            return "Type in a number in the range";
        }
    }

    private static String handleUnmark(String[] parts, TaskList tasks) {
        if (parts.length < 2) {
            return "Usage: unmark <index>";
        }
        try {
            int index = Integer.parseInt(parts[1]);
            return tasks.unmarkItem(index);
        } catch (NumberFormatException e) {
            return "Type in a number in the range";
        }
    }

    private static String handleList(TaskList tasks) {
        return tasks.toString();
    }

    private static String handleTodo(String[] parts, TaskList tasks) {
        if (parts.length < 2) {
            return "Usage: todo <task>";
        }
        return tasks.addItem(new ToDo(parts[1]));
    }

    private static String handleDeadline(String input, String[] parts, TaskList tasks) {
        if (parts.length < 2) {
            return "Usage: deadline <task> /by <time>";
        }
        Pattern pattern = Pattern.compile("^deadline (.+) /by (.+)$");
        Matcher m = pattern.matcher(input);
        if (!m.matches()) {
            return "Usage: deadline <task> /by <time>";
        }

        String desc = m.group(1).trim();
        String byStr = m.group(2).trim();
        try {
            LocalDate dueDate = LocalDate.parse(byStr, DATE_FORMAT);
            return tasks.addItem(new Deadline(desc, dueDate));
        } catch (DateTimeParseException e) {
            return "Invalid date format, use yyyy-MM-dd";
        }
    }

    private static String handleEvent(String input, String[] parts, TaskList tasks) {
        if (parts.length < 2) {
            return "Usage: event <task> /from <startTime> /to <endTime>";
        }
        Pattern pattern = Pattern.compile("^event (.+) /from (.+) /to (.+)$");
        Matcher m = pattern.matcher(input);
        if (!m.matches()) {
            return "Usage: event <task> /from <startTime> /to <endTime>";
        }

        String desc = m.group(1).trim();
        String fromStr = m.group(2).trim();
        String toStr = m.group(3).trim();
        try {
            LocalDate from = LocalDate.parse(fromStr, DATE_FORMAT);
            LocalDate to = LocalDate.parse(toStr, DATE_FORMAT);
            return tasks.addItem(new Event(desc, from, to));
        } catch (DateTimeParseException e) {
            return "Invalid date format, use yyyy-MM-dd";
        }
    }

    private static String handleDelete(String[] parts, TaskList tasks) {
        if (parts.length < 2) {
            return "Usage: delete <index>";
        }
        try {
            int index = Integer.parseInt(parts[1]);
            return tasks.deleteItem(index);
        } catch (NumberFormatException e) {
            return "Type in a number in the range";
        }
    }

    private static String handleFind(String[] parts, TaskList tasks) {
        if (parts.length < 2) {
            return "Usage: find <query>";
        }
        return tasks.findTask(parts[1]);
    }

    private static String handleUpdate(String input, String[] parts, TaskList tasks) {
        if (parts.length < 2) {
            return "Usage: update <index> /*";
        }
        return Updater.handleUpdate(input, tasks);
    }

    private static String handleHelp(String[] parts) {
        if (parts.length == 1) {
            return Help.getHelp(null);
        } else {
            return Help.getHelp(parts[1]);
        }
    }
}
