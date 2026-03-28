package dume.parser;

import dume.DumeException;
import dume.storage.Storage;
import dume.task.Deadline;
import dume.task.Event;
import dume.task.Task;
import dume.task.TaskList;
import dume.task.Todo;
import dume.ui.Ui;

import java.util.List;
import java.util.Objects;

/**
 * Parses user input and executes the corresponding commands.
 * 
 * AI Assistance: Claude Code helped improve error handling for sort commands by:
 * - Adding validation for empty sort parameters
 * - Enhancing error messages to include the invalid sort type
 * - Ensuring consistent error handling between CLI and GUI modes
 */
public class Parser {

    /**
     * Processes a raw user command.
     *
     * @param raw user input string
     * @param tasks task list to operate on
     * @param ui user interface for output
     * @param storage storage for saving tasks
     * @return true if the user wants to exit, false otherwise
     */
    public static boolean process(String raw, TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        
        String cmd = Objects.requireNonNullElse(raw, "").trim();
        String lc = cmd.toLowerCase();

        if (lc.equals("bye")) {
            storage.save(tasks.asList());
            ui.say("Bye. Hope to see you again soon!");
            return true;
        }

        if (lc.equals("list")) {
            ui.showList(tasks.asList());
            return false;
        }

        if (lc.equals("mark") || lc.startsWith("mark ")) {
            return handleMarkCommand(lc, tasks, ui, storage);
        }

        if (lc.equals("unmark") || lc.startsWith("unmark ")) {
            return handleUnmarkCommand(lc, tasks, ui, storage);
        }

        if (lc.equals("delete") || lc.startsWith("delete ")) {
            return handleDeleteCommand(lc, tasks, ui, storage);
        }

        if (lc.startsWith("todo")) {
            String details = (cmd.length() > 4) ? cmd.substring(4).trim() : "";
            checkNonEmpty(details, "a todo");
            Task task = new Todo(details);
            tasks.add(task);
            storage.save(tasks.asList());
            ui.say("Got it. I've added this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.");
            return false;
        }

        if (lc.startsWith("deadline")) {
            String rest = (cmd.length() > 8) ? cmd.substring(8).trim() : "";
            checkNonEmpty(rest, "a deadline");
            String[] p = split(rest, "/by", "A deadline needs a /by time!");
            String details = p[0].trim();
            String by = p[1].trim();
            checkNonEmpty(details, "a deadline");
            checkNonEmpty(by, "the /by time");
            Task task = new Deadline(details, by);
            tasks.add(task);
            storage.save(tasks.asList());
            ui.say("Got it. I've added this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.");
            return false;
        }

        if (lc.startsWith("event")) {
            String rest = (cmd.length() > 5) ? cmd.substring(5).trim() : "";
            checkNonEmpty(rest, "an event");
            String[] p1 = split(rest, "/from", "An event needs /from and /to!");
            String details = p1[0].trim();
            String[] p2 = split(p1[1], "/to", "Missing /to for event!");
            String from = p2[0].trim();
            String to = p2[1].trim();
            checkNonEmpty(details, "an event");
            checkNonEmpty(from, "the /from time");
            checkNonEmpty(to, "the /to time");
            Task task = new Event(details, from, to);
            tasks.add(task);
            storage.save(tasks.asList());
            ui.say("Got it. I've added this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.");
            return false;
        }

        if (lc.equals("find")) {
            throw new DumeException("Please give a keyword after 'find'!");
        }
        if (lc.startsWith("find ")) {
            String keyword = cmd.substring(5).trim();
            if (keyword.isEmpty()) throw new DumeException("Please give a keyword after 'find'!");
            List<Task> matches = tasks.find(keyword);
            ui.showFound(matches);
            return false;
        }

        if (lc.equals("sort")) {
            throw new DumeException("Please specify how to sort: 'sort chrono', 'sort alpha', or 'sort status'!");
        }
        if (lc.startsWith("sort ")) {
            String sortType = cmd.substring(5).trim().toLowerCase();
            if (sortType.isEmpty()) {
                throw new DumeException("Please specify how to sort: 'sort chrono', 'sort alpha', or 'sort status'!");
            }
            switch (sortType) {
                case "chrono":
                case "chronological":
                    tasks.sortChronologically();
                    storage.save(tasks.asList());
                    ui.say("Tasks sorted chronologically (deadlines and events by date).");
                    break;
                case "alpha":
                case "alphabetical":
                    tasks.sortAlphabetically();
                    storage.save(tasks.asList());
                    ui.say("Tasks sorted alphabetically.");
                    break;
                case "status":
                    tasks.sortByStatus();
                    storage.save(tasks.asList());
                    ui.say("Tasks sorted by status (incomplete first, then completed).");
                    break;
                default:
                    throw new DumeException("Unknown sort type '" + sortType + "'! Use 'chrono', 'alpha', or 'status'.");
            }
            return false;
        }

        throw new DumeException("Sorry! I don't understand!");
    }

    public static String processGui(String raw, TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        
        String cmd = Objects.requireNonNullElse(raw, "").trim();
        String lc = cmd.toLowerCase();

        if (lc.equals("bye")) {
            storage.save(tasks.asList());
            return "Bye. Hope to see you again soon!";
        }

        if (lc.equals("list")) {
            List<Task> taskList = tasks.asList();
            if (taskList.isEmpty()) {
                return "No tasks yet...";
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < taskList.size(); i++) {
                    sb.append((i + 1)).append(". ").append(taskList.get(i));
                    if (i < taskList.size() - 1) sb.append("\n");
                }
                return sb.toString();
            }
        }

        if (lc.equals("mark") || lc.startsWith("mark ")) {
            return handleMarkCommandGui(lc, tasks, storage);
        }

        if (lc.equals("unmark") || lc.startsWith("unmark ")) {
            return handleUnmarkCommandGui(lc, tasks, storage);
        }

        if (lc.equals("delete") || lc.startsWith("delete ")) {
            return handleDeleteCommandGui(lc, tasks, storage);
        }

        if (lc.startsWith("todo")) {
            String details = (cmd.length() > 4) ? cmd.substring(4).trim() : "";
            checkNonEmpty(details, "a todo");
            Task task = new Todo(details);
            tasks.add(task);
            storage.save(tasks.asList());
            return "Got it. I've added this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        }

        if (lc.startsWith("deadline")) {
            String rest = (cmd.length() > 8) ? cmd.substring(8).trim() : "";
            checkNonEmpty(rest, "a deadline");
            String[] p = split(rest, "/by", "A deadline needs a /by time!");
            String details = p[0].trim();
            String by = p[1].trim();
            checkNonEmpty(details, "a deadline");
            checkNonEmpty(by, "the /by time");
            Task task = new Deadline(details, by);
            tasks.add(task);
            storage.save(tasks.asList());
            return "Got it. I've added this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        }

        if (lc.startsWith("event")) {
            String rest = (cmd.length() > 5) ? cmd.substring(5).trim() : "";
            checkNonEmpty(rest, "an event");
            String[] p1 = split(rest, "/from", "An event needs /from and /to!");
            String details = p1[0].trim();
            String[] p2 = split(p1[1], "/to", "Missing /to for event!");
            String from = p2[0].trim();
            String to = p2[1].trim();
            checkNonEmpty(details, "an event");
            checkNonEmpty(from, "the /from time");
            checkNonEmpty(to, "the /to time");
            Task task = new Event(details, from, to);
            tasks.add(task);
            storage.save(tasks.asList());
            return "Got it. I've added this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        }

        if (lc.equals("find")) {
            throw new DumeException("Please give a keyword after 'find'!");
        }
        if (lc.startsWith("find ")) {
            String keyword = cmd.substring(5).trim();
            if (keyword.isEmpty()) throw new DumeException("Please give a keyword after 'find'!");
            List<Task> matches = tasks.find(keyword);
            if (matches.isEmpty()) {
                return "No matching tasks found.";
            } else {
                StringBuilder sb = new StringBuilder("Here are the matching tasks found:\n");
                for (int i = 0; i < matches.size(); i++) {
                    sb.append((i + 1)).append(". ").append(matches.get(i));
                    if (i < matches.size() - 1) sb.append("\n");
                }
                return sb.toString();
            }
        }

        if (lc.equals("sort")) {
            throw new DumeException("Please specify how to sort: 'sort chrono', 'sort alpha', or 'sort status'!");
        }
        if (lc.startsWith("sort ")) {
            String sortType = cmd.substring(5).trim().toLowerCase();
            if (sortType.isEmpty()) {
                throw new DumeException("Please specify how to sort: 'sort chrono', 'sort alpha', or 'sort status'!");
            }
            switch (sortType) {
                case "chrono":
                case "chronological":
                    tasks.sortChronologically();
                    storage.save(tasks.asList());
                    return "Tasks sorted chronologically (deadlines and events by date).";
                case "alpha":
                case "alphabetical":
                    tasks.sortAlphabetically();
                    storage.save(tasks.asList());
                    return "Tasks sorted alphabetically.";
                case "status":
                    tasks.sortByStatus();
                    storage.save(tasks.asList());
                    return "Tasks sorted by status (incomplete first, then completed).";
                default:
                    throw new DumeException("Unknown sort type '" + sortType + "'! Use 'chrono', 'alpha', or 'status'.");
            }
        }

        throw new DumeException("Sorry! I don't understand!");
    }

    // Command handlers for better abstraction
    private static boolean handleMarkCommand(String cmd, TaskList tasks, Ui ui, Storage storage) {
        if (cmd.equals("mark")) {
            throw new DumeException("Please give a task number after 'mark'!");
        }
        if (cmd.startsWith("mark ")) {
            int id = parseIndexOrThrow(cmd.substring(5), tasks.size());
            Task task = tasks.get(id);
            task.mark();
            storage.save(tasks.asList());
            ui.say("Nice! I've marked this task as done:\n  " + task);
        }
        return false;
    }

    private static String handleMarkCommandGui(String cmd, TaskList tasks, Storage storage) {
        if (cmd.equals("mark")) {
            throw new DumeException("Please give a task number after 'mark'!");
        }
        if (cmd.startsWith("mark ")) {
            int id = parseIndexOrThrow(cmd.substring(5), tasks.size());
            Task task = tasks.get(id);
            task.mark();
            storage.save(tasks.asList());
            return "Nice! I've marked this task as done:\n  " + task;
        }
        return null;
    }

    private static boolean handleUnmarkCommand(String cmd, TaskList tasks, Ui ui, Storage storage) {
        if (cmd.equals("unmark")) {
            throw new DumeException("Please give a task number after 'unmark'!");
        }
        if (cmd.startsWith("unmark ")) {
            int id = parseIndexOrThrow(cmd.substring(7), tasks.size());
            Task task = tasks.get(id);
            task.unmark();
            storage.save(tasks.asList());
            ui.say("OK, I've marked this task as not done yet:\n  " + task);
        }
        return false;
    }

    private static String handleUnmarkCommandGui(String cmd, TaskList tasks, Storage storage) {
        if (cmd.equals("unmark")) {
            throw new DumeException("Please give a task number after 'unmark'!");
        }
        if (cmd.startsWith("unmark ")) {
            int id = parseIndexOrThrow(cmd.substring(7), tasks.size());
            Task task = tasks.get(id);
            task.unmark();
            storage.save(tasks.asList());
            return "OK, I've marked this task as not done yet:\n  " + task;
        }
        return null;
    }

    private static boolean handleDeleteCommand(String cmd, TaskList tasks, Ui ui, Storage storage) {
        if (cmd.equals("delete")) {
            throw new DumeException("Please give a task number after 'delete'!");
        }
        if (cmd.startsWith("delete ")) {
            int id = parseIndexOrThrow(cmd.substring(7), tasks.size());
            Task removed = tasks.remove(id);
            storage.save(tasks.asList());
            int n = tasks.size();
            ui.say("Noted. I've removed this task:\n  " + removed
                    + "\nNow you have " + n + " task" + (n == 1 ? "" : "s") + " in the list.");
        }
        return false;
    }

    private static String handleDeleteCommandGui(String cmd, TaskList tasks, Storage storage) {
        if (cmd.equals("delete")) {
            throw new DumeException("Please give a task number after 'delete'!");
        }
        if (cmd.startsWith("delete ")) {
            int id = parseIndexOrThrow(cmd.substring(7), tasks.size());
            Task removed = tasks.remove(id);
            storage.save(tasks.asList());
            int n = tasks.size();
            return "Noted. I've removed this task:\n  " + removed
                    + "\nNow you have " + n + " task" + (n == 1 ? "" : "s") + " in the list.";
        }
        return null;
    }

    // helpers (same logic you had)
    private static String[] split(String text, String delim, String err) {
        assert text != null : "Text cannot be null";
        assert delim != null : "Delimiter cannot be null";
        assert err != null : "Error message cannot be null";
        
        String[] parts = text.split(delim, 2);
        if (parts.length < 2) throw new DumeException(err);
        return parts;
    }

    private static void checkNonEmpty(String s, String what) {
        if (s == null || s.isBlank()) {
            throw new DumeException("The description of " + what + " cannot be empty!");
        }
    }

    private static int parseIndexOrThrow(String arg, int size) {
        assert arg != null : "Argument cannot be null";
        assert size >= 0 : "Size must be non-negative";
        
        final int zeroBased;
        try {
            zeroBased = Integer.parseInt(arg.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DumeException("Please give a task number!");
        }
        if (zeroBased < 0 || zeroBased >= size) throw new DumeException("That task does not exist!");
        return zeroBased;
    }
}
