package sora;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import sora.list.TaskList;
import sora.storage.Storage;
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
    public static void parse(String command, TaskList tasks, Ui ui,
                             Storage storage) throws SoraException {
        String[] parts = command.split(" ", 2);
        String startWord = parts[0];
        String description = parts.length == 1 ? "" : parts[1];
        if (command.equals("bye")) {
            ui.showGoodbye();
        } else {
            switch (startWord) {
            case "find":
                if (description.isEmpty()) {
                    throw new SoraException("Lack of keyword");
                }
                ArrayList<Task> foundTask = tasks.findTasks(description);
                ui.showFoundTask(foundTask);
                break;
            case "list":
                if (description.isEmpty()) {
                    ui.showTaskList(tasks);
                }
                break;
            case "mark":
                int x = Integer.parseInt(description);
                tasks.getTask(x - 1).markAsDone();
                ui.showMarkedTask(tasks.getTask(x - 1));
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    System.out.println("Can not save! " + e.getMessage());
                }
                break;
            case "unmark":
                int y = Integer.parseInt(description);
                tasks.getTask(y - 1).markAsNotDone();
                ui.showUnmarkedTask(tasks.getTask(y - 1));
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    System.out.println("Can not save! " + e.getMessage());
                }
                break;
            case "delete":
                int z = Integer.parseInt(description);
                Task deleted = tasks.deleteTask(z - 1);
                ui.showDeletedTask(deleted, tasks.size());
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    System.out.println("Can not save! " + e.getMessage());
                }
                break;
            case "todo":
                if (description.isEmpty()) {
                    throw new SoraException("Cannot todo nothing");
                } else {
                    Task todo = new Todo(description);
                    tasks.addTask(todo);
                    ui.showAddedTask(todo, tasks.size());
                }
                break;
            case "deadline":
                if (description.isEmpty()) {
                    throw new SoraException("Invalid deadline");
                } else {
                    if (!description.contains("/by")) {
                        throw new SoraException("Lack of deadline time!");
                    }
                    String[] time = description.split(" /by ");
                    LocalDateTime by = LocalDateTime.parse(time[1], format);
                    Task deadline = new Deadline(time[0], by);
                    tasks.addTask(deadline);
                    ui.showAddedTask(deadline, tasks.size());
                }
                break;
            case "event":
                if (description.isEmpty()) {
                    throw new SoraException("Invalid event");
                } else {
                    if (!description.contains("/from")) {
                        throw new SoraException("Lack of start time!");
                    }
                    String[] time = description.split(" /from ");
                    if (!time[1].contains("/to")) {
                        throw new SoraException("Lack of end time!");
                    }
                    String[] range = time[1].split(" /to ");
                    LocalDateTime from = LocalDateTime.parse(range[0], format);
                    LocalDateTime to = LocalDateTime.parse(range[1], format);
                    Task event = new Event(time[0], from, to);
                    tasks.addTask(event);
                    ui.showAddedTask(event, tasks.size());
                }
                break;
            default:
                throw new SoraException("Not a valid input");
            }
        }
    }
}
