package lynx.command;

import lynx.exception.LynxException;
import lynx.exception.MissingArgumentException;
import lynx.formatter.LynxDateManager;
import lynx.storage.LynxFileManager;
import lynx.storage.LynxStorage;
import lynx.storage.LynxTaskList;
import lynx.task.DeadlineTask;
import lynx.task.EventTask;
import lynx.task.Task;
import lynx.task.TodoTask;
import lynx.ui.LynxUI;

import java.time.LocalDateTime;

// Parses command details and executes them
public abstract class LynxCommand {

    public static void reload() {
        LynxFileManager.createFile();
        LynxStorage.loadTasks(LynxFileManager.readFromFile());
        LynxUI.line();
    }

    public static void addTodo(String input) throws LynxException {
        if (input.length() <= 4) {
            throw new MissingArgumentException("todo");
        }
        String name = input.substring(5).trim();
        LynxTaskList.addTask(new TodoTask(name), true);
    }

    public static void addDeadline(String input) throws LynxException {
        if (input.length() <= 8) {
            throw new MissingArgumentException("deadline");
        }
        String[] parts = input.substring(9).split("/by", 2);
        if (parts.length < 2) {
            throw new LynxException("Please specify a deadline using '/by'.");
        }
        String name = parts[0].trim();
        LocalDateTime by = LynxDateManager.parseDateTime(parts[1].trim());
        LynxTaskList.addTask(new DeadlineTask(name, by), true);
    }

    public static void addEvent(String input) throws LynxException {
        if (input.length() <= 5) {
            throw new MissingArgumentException("event");
        }
        String[] nameSplit = input.substring(6).split("/from", 2);
        if (nameSplit.length < 2) {
            throw new LynxException("Please specify a start time using '/from'.");
        }
        String name = nameSplit[0].trim();
        String[] timeSplit = nameSplit[1].split("/to", 2);
        if (timeSplit.length < 2) {
            throw new LynxException("Please specify an end time using '/to'.");
        }
        LocalDateTime from = LynxDateManager.parseDateTime(timeSplit[0].trim());
        LocalDateTime to = LynxDateManager.parseDateTime(timeSplit[1].trim());
        if (from.isAfter(to)) {
            throw new LynxException("The start date/time cannot be after the end date/time.");
        }
        LynxTaskList.addTask(new EventTask(name, from, to), true);
    }

    public static void markTask(String input) throws LynxException {
        if (input.length() <= 4) {
            throw new MissingArgumentException("mark");
        }
        input = input.substring(5).trim();
        Task task = findTask(input);
        task.setCompleted();
        LynxUI.printBox("Excellent! Marked as done:\n     " + task.toString());
    }

    public static void unmarkTask(String input) throws LynxException {
        if (input.length() <= 6) {
            throw new MissingArgumentException("unmark");
        }
        input = input.substring(7).trim();
        Task task = findTask(input);
        task.resetCompleted();
        LynxUI.printBox("Alright, marked as not done:\n     " + task.toString());
    }

    private static Task findTask(String input) throws LynxException {
        if (input.startsWith("id:")) {
            // Mark by unique ID
            try {
                int id = Integer.parseInt(input.substring(3).trim());
                return LynxTaskList.findTaskById(id);
            } catch (NumberFormatException e) {
                throw new LynxException("Sorry, that isn't a valid ID.");
            }
        } else {
            // Mark by position in list
            try {
                int pos = Integer.parseInt(input);
                return LynxTaskList.findTaskByPosition(pos);
            } catch (NumberFormatException e) {
                throw new LynxException("Please provide a valid position number.");
            }
        }
    }

    public static void deleteTask(String input) throws LynxException {
        if (input.length() <= 6) {
            throw new MissingArgumentException("delete");
        }
        input = input.substring(7).trim();
        if (input.equals("/all")) {
            LynxTaskList.clearTasks(true);
            return;
        }
        Task task = findTask(input);
        LynxTaskList.removeTask(task, true);
    }

    public static void listTasks(String input) throws LynxException {
        if (input.equals("list")) {
            LynxTaskList.printTasks();
            return;
        }
        input = input.substring(5).trim();
        try {
            LocalDateTime dateTime = LynxDateManager.parseDateTime(input);
            LynxTaskList.printTasksOnDate(dateTime);
        } catch (LynxException e) {
            LynxUI.printBox(e.getMessage());
        }
    }
}
