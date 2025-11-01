package yuan.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.application.Platform;
import yuan.exception.YuanException;
import yuan.storage.Storage;
import yuan.task.Deadline;
import yuan.task.Event;
import yuan.task.Task;
import yuan.task.Todo;
import yuan.tasklist.TaskList;
import yuan.ui.UI;

/**
 * Deals with parsing user input and executing commands.
 */
public class Parser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    /**
     * Parses user input and executes the corresponding command.
     *
     * @param input    The full command string entered by the user.
     * @param taskList The current list of tasks.
     * @param storage  The storage to save tasks into.
     * @param ui       The UI to render responses.
     * @return A response string to show to the user (GUI or CLI).
     */
    public static String parseAndExecute(String input, TaskList taskList, Storage storage, UI ui) {
        try {
            String[] parts = input.trim().split(" ", 2);
            String command = parts[0];
            String instruction = parts.length > 1 ? parts[1] : "";

            if (command.equals("bye")) {
                Platform.exit();
                return "Bye. I don't wanna see you again";
            }

            if (command.equals("list")) {
                return ui.renderTasks(taskList);
            }

            if (command.equals("help")) {
                return ui.renderHelp();
            }

            if (command.equals("find")) {
                TaskList found = taskList.findTaskWithKeyword(instruction);
                return ui.renderTasks(found);
            }

            if (command.equals("mark")) {
                int idx = Integer.parseInt(instruction) - 1;
                if (idx < 0 || idx >= taskList.getSize()) {
                    throw new YuanException("No task with that number!");
                }
                taskList.markTask(idx);
                storage.save(taskList);
                return ui.renderMark(taskList.get(idx));
            }

            if (command.equals("unmark")) {
                int idx = Integer.parseInt(instruction) - 1;
                if (idx < 0 || idx >= taskList.getSize()) {
                    throw new YuanException("No task with that number!");
                }
                taskList.unmarkTask(idx);
                storage.save(taskList);
                return ui.renderUnmark(taskList.get(idx));
            }

            switch (command) {
            case "todo":
                if (instruction.isEmpty()) {
                    throw new YuanException("Todo cannot be empty.");
                }
                Task todo = new Todo(instruction, false);
                taskList.addTask(todo);
                storage.save(taskList);
                return ui.renderAdded(todo, taskList.getSize());

            case "deadline":
                String[] rest = instruction.split(" /by ", 2);
                String desc = rest[0];
                LocalDate by = LocalDate.parse(rest[1], formatter);
                Task deadline = new Deadline(desc, by, false);
                taskList.addTask(deadline);
                storage.save(taskList);
                return ui.renderAdded(deadline, taskList.getSize());

            case "event":
                String[] fromParts = instruction.split(" /from ", 2);
                String descE = fromParts[0];
                String[] toParts = fromParts[1].split(" /to ", 2);
                LocalDate from = LocalDate.parse(toParts[0], formatter);
                LocalDate to = LocalDate.parse(toParts[1], formatter);
                Task event = new Event(descE, from, to, false);
                taskList.addTask(event);
                storage.save(taskList);
                return ui.renderAdded(event, taskList.getSize());

            case "delete":
                int idxDel = Integer.parseInt(instruction) - 1;
                if (idxDel < 0 || idxDel >= taskList.getSize()) {
                    throw new YuanException("No task with that number!");
                }
                Task removed = taskList.removeTask(idxDel);
                storage.save(taskList);
                return ui.renderRemoved(removed, taskList.getSize());

            default:
                throw new YuanException("What are you saying??? Try again lil bro or try typing help for guidance");
            }
        } catch (DateTimeParseException e) {
            return ui.renderError("Don't make me say again, pls enter the date in dd/MM/yyyy format");
        } catch (Exception e) {
            return ui.renderError(e.getMessage());
        }
    }
}

