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

    /**
     * Attempts to create the log.txt data file or load its contents if it exists.
     */
    public static void reload() {
        LynxFileManager.createFile();
        LynxStorage.loadTasks(LynxFileManager.readFromFile());
        LynxUI.line();
    }

    /**
     * Creates a TodoTask and adds it to the task list.
     *
     * @param input User command in the form "todo [name]".
     * @return TodoTask that is created.
     * @throws LynxException If command or name is invalid.
     */
    public static TodoTask addTodo(String input) throws LynxException {
        if (input.length() <= 4) {
            throw new MissingArgumentException("todo");
        }
        String name = input.substring(4).trim();
        if (name.isEmpty()) {
            throw new LynxException("Please specify a task name.");
        }
        checkName(name);

        TodoTask task = new TodoTask(name);
        LynxTaskList.addTask(task, true);
        return task;
    }

    /**
     * Creates a DeadlineTask and adds it to the task list.
     *
     * @param input User command in the form "deadline [name] /by [date]".
     * @return DeadlineTask that is created.
     * @throws LynxException If command, name or date is invalid.
     */
    public static DeadlineTask addDeadline(String input) throws LynxException {
        if (input.length() <= 8) {
            throw new MissingArgumentException("deadline");
        }
        String[] parts = input.substring(8).split(" /by ", 2);
        if (parts.length < 2) {
            throw new LynxException("Please specify a deadline using ' /by '.");
        }
        String name = parts[0].trim();
        if (name.isEmpty()) {
            throw new LynxException("Please specify a task name.");
        }
        checkName(name);

        LocalDateTime by = LynxDateManager.parseDateTime(parts[1].trim());
        DeadlineTask task = new DeadlineTask(name, by);
        LynxTaskList.addTask(task, true);
        return task;
    }

    /**
     * Creates a EventTask and adds it to the task list.
     *
     * @param input User command in the form "event [name] /from [start] /to [end]".
     * @return EventTask that is created.
     * @throws LynxException If command, name or date is invalid.
     */
    public static EventTask addEvent(String input) throws LynxException {
        if (input.length() <= 5) {
            throw new MissingArgumentException("event");
        }
        String[] parts = input.substring(5).split(" /from ", 2);
        if (parts.length < 2) {
            throw new LynxException("Please specify a start time using ' /from '.");
        }
        String[] timeSplit = parts[1].split(" /to ", 2);
        if (timeSplit.length < 2) {
            throw new LynxException("Please specify an end time using ' /to '.");
        }
        String name = parts[0].trim();
        if (name.isEmpty()) {
            throw new LynxException("Please specify a task name.");
        }
        checkName(name);


        LocalDateTime from = LynxDateManager.parseDateTime(timeSplit[0].trim());
        LocalDateTime to = LynxDateManager.parseDateTime(timeSplit[1].trim());
        if (from.isAfter(to)) {
            throw new LynxException("The start date/time cannot be after the end date/time.");
        }
        EventTask task = new EventTask(name, from, to);
        LynxTaskList.addTask(task, true);
        return task;
    }

    /**
     * Marks a Task.
     *
     * @param input User command in the form "mark id:[id]" or "mark [position]".
     * @return Task that is marked.
     * @throws LynxException If command is invalid or task cannot be found.
     */
    public static Task markTask(String input) throws LynxException {
        if (input.length() <= 4) {
            throw new MissingArgumentException("mark");
        }
        input = input.substring(4).trim();
        Task task = findTask(input);
        task.setComplete();
        LynxUI.printBox("Excellent! Marked as done:\n     " + task.toString());
        return task;
    }

    /**
     * Unmarks a Task.
     *
     * @param input User command in the form "unmark id:[id]" or "unmark [position]".
     * @return Task that is unmarked.
     * @throws LynxException If command is invalid or task cannot be found.
     */
    public static Task unmarkTask(String input) throws LynxException {
        if (input.length() <= 6) {
            throw new MissingArgumentException("unmark");
        }
        input = input.substring(6).trim();
        Task task = findTask(input);
        task.setIncomplete();
        LynxUI.printBox("Alright, marked as not done:\n     " + task.toString());
        return task;
    }

    /**
     * Deletes a task using its id or position in the task list.
     * Deletes all tasks if the argument "/all" is provided instead.
     *
     * @param input User command in the form "delete id:[id]" or "delete [position]" or "delete /all".
     * @return Task that is deleted. Returns null if "delete /all" is provided instead.
     * @throws LynxException If command, id or position is invalid.
     */
    public static Task deleteTask(String input) throws LynxException {
        if (input.length() <= 6) {
            throw new MissingArgumentException("delete");
        }
        input = input.substring(6).trim();
        if (input.equals("/all")) {
            LynxTaskList.clearTasks(true);
            return null;
        }
        Task task = findTask(input);
        LynxTaskList.removeTask(task, true);
        return task;
    }

    /**
     * Lists all tasks in the task list.
     * If a keyword is provided, lists all tasks with names containing that keyword instead.
     * If a date is provided using "/on", lists all tasks occurring on that date instead.
     * If an id is provided using "/id", lists the task with that id instead.
     *
     * @param input User command in the form "list" or "list [keyword]" or "list /on [date]" or "list /id [id]".
     * @throws LynxException If command or date is invalid.
     */
    public static void listTasks(String input) throws LynxException {
        input = input.trim();

        if (input.equals("list")) {
            LynxTaskList.printAllTasks();
            return;
        }

        if (input.startsWith("list /on ")) {
            input = input.substring(9).trim();
            try {
                LocalDateTime dateTime = LynxDateManager.parseDateTime(input);
                LynxTaskList.printTasksOnDate(dateTime);
            } catch (LynxException e) {
                LynxUI.printBox("Invalid date format: " + e.getMessage());
            }
            return;
        }

        if (input.startsWith("list /id ")) {
            input = input.substring(9).trim();
            try {
                int id = Integer.parseInt(input);
                LynxTaskList.printTaskById(id);
            } catch (NumberFormatException e) {
                throw new LynxException("Sorry, that isn't a valid ID.");
            }
            return;
        }

        if (input.startsWith("list ")) {
            String substring = input.substring(5).trim();
            LynxTaskList.printTasksContaining(substring);
            return;
        }

        throw new LynxException("Unrecognized 'list' command format.");
    }

    /**
     * Searches for a task using its id or position in the task list.
     *
     * @param input Task id in the form "/id [id]" or position.
     * @return Matching task.
     * @throws LynxException If command, id or position is invalid.
     */
    private static Task findTask(String input) throws LynxException {
        if (input.startsWith("/id ")) {
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

    private static void checkName(String name) throws LynxException {
        if (name.contains("/")) {
            throw new LynxException("Task name cannot contain the \"/\" character.");
        } else if (name.length() > 150) {
            throw new LynxException("Task name cannot exceed 150 characters.");
        }
    }

}
