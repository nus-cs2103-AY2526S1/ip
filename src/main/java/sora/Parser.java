package sora;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import sora.list.TaskList;
import sora.storage.Storage;
import sora.task.After;
import sora.task.Deadline;
import sora.task.Event;
import sora.task.Task;
import sora.task.Todo;

/**
 * The {@code Parser} class handles the parsing and execution of user commands.
 * It interprets user input and modifies the {@code TaskList} accordingly.
 */
public class Parser {
    private static final DateTimeFormatter format =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.ENGLISH);

    /**
     * Parses and executes the given user command.
     *
     * @param command the raw user input string.
     * @param tasks the current {@code TaskList} to modify.
     * @param ui the {@code Ui} instance to handle interactions.
     * @param storage the {@code Storage} used for saving/loading tasks.
     * @throws SoraException if the command is invalid or malformed.
     */
    public static String parse(String command, TaskList tasks, Ui ui,
                             Storage storage) throws SoraException {
        assert command != null : "Command must not be null";
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";

        String[] parts = command.split(" ", 2);
        String startWord = parts[0].trim();
        String description = parts.length == 1 ? "" : parts[1].trim();
        if (command.equals("bye")) {
            return ui.showGoodbye();
        } else {
            switch (startWord) {
            case "find":
                if (description.isEmpty()) {
                    throw new SoraException("Lack of keyword");
                }
                ArrayList<Task> foundTask = tasks.findTasks(description);
                return ui.showFoundTask(foundTask);
            case "list":
                if (description.isEmpty()) {
                    return ui.showTaskList(tasks);
                } else {
                    throw new SoraException("Not a valid input");
                }
            case "mark":
                int x = Integer.parseInt(description);
                tasks.mark(x - 1);
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    System.out.println("Can not save! " + e.getMessage());
                }
                return ui.showMarkedTask(tasks.getTask(x - 1));
            case "unmark":
                int y = Integer.parseInt(description);
                tasks.unmark(y - 1);
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    System.out.println("Can not save! " + e.getMessage());
                }
                return ui.showUnmarkedTask(tasks.getTask(y - 1));
            case "delete":
                int z = Integer.parseInt(description);
                Task deleted = tasks.deleteTask(z - 1);
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    System.out.println("Can not save! " + e.getMessage());
                }
                return ui.showDeletedTask(deleted, tasks.size());
            case "todo":
                if (description.isEmpty()) {
                    throw new SoraException("Cannot todo nothing");
                } else {
                    Task todo = new Todo(description);
                    tasks.addTask(todo);
                    return ui.showAddedTask(todo, tasks.size());
                }
            case "deadline":
                if (description.isEmpty()) {
                    throw new SoraException("Invalid deadline");
                } else {
                    if (!description.contains("/by")) {
                        throw new SoraException("Lack of deadline time!");
                    }
                    String[] time = description.split("/by");
                    LocalDateTime by = LocalDateTime.parse(time[1].trim(), format);
                    Task deadline = new Deadline(time[0].trim(), by);
                    tasks.addTask(deadline);
                    return ui.showAddedTask(deadline, tasks.size());
                }
            case "after":
                if (description.isEmpty()) {
                    throw new SoraException("Invalid after");
                } else {
                    if (!description.contains("/required")) {
                        throw new SoraException("Lack of required time!");
                    }
                    String[] time = description.split("/required");
                    LocalDateTime required = LocalDateTime.parse(time[1].trim(), format);
                    Task after = new After(time[0].trim(), required);
                    tasks.addTask(after);
                    return ui.showAddedTask(after, tasks.size());
                }
            case "event":
                if (description.isEmpty()) {
                    throw new SoraException("Invalid event");
                } else {
                    if (!description.contains("/from")) {
                        throw new SoraException("Lack of start time!");
                    }
                    String[] time = description.split("/from");
                    if (!time[1].trim().contains("/to")) {
                        throw new SoraException("Lack of end time!");
                    }
                    String[] range = time[1].trim().split("/to");
                    LocalDateTime from = LocalDateTime.parse(range[0].trim(), format);
                    LocalDateTime to = LocalDateTime.parse(range[1].trim(), format);
                    Task event = new Event(time[0].trim(), from, to);
                    tasks.addTask(event);
                    return ui.showAddedTask(event, tasks.size());
                }
            default:
                throw new SoraException("Not a valid input");
            }
        }
    }
}
