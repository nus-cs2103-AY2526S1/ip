package alune;

import java.time.LocalDateTime;

import alune.tasks.DeadlineTask;
import alune.tasks.EventTask;
import alune.tasks.Task;
import alune.tasks.TaskList;
import alune.tasks.ToDoTask;
import alune.ui.UI;
import alune.utils.Parser;

/**
 * This standard class handles the chatbot's responses to the user's inputs.
 * 
 * @author nghnaomi
 */
public class Alune {
    private final Database database;
    private final UI ui;
    private TaskList tasks;

    /**
     * Constructor for Alune class object.
     * 
     * @param filePath Path to load database file from.
     */
    public Alune(String filePath) {
        ui = new UI();
        database = new Database(filePath);
        tasks = new TaskList(database.load());
    }

    /**
     * Saves data before program closes.
     */
    public void shutdown() {
        database.update(tasks);
    }

    /**
     * Returns the chatbot's response to the given command, including a default for
     * unknown commands.
     * 
     * @param input User's message to the chatbot.
     * @return String response to user's command.
     */
    public String getResponse(String input) {
        assert ui != null : "important: UI not found";
        assert database != null : "important: database not found";
        assert tasks != null : "important: tasklist not found";

        String firstWord = input.split(" ")[0];
        Commands command = Commands.fromString(firstWord);

        return switch (command) {
            case HI -> ui.greet();
            case LIST -> ui.listTasks(tasks);
            case BYE -> handleBye();
            case MARK -> handleMark(input);
            case UNMARK -> handleUnmark(input);
            case TODO -> handleToDo(input);
            case DEADLINE -> handleDeadline(input);
            case EVENT -> handleEvent(input);
            case DELETE -> handleDelete(input);
            case CLEAR -> handleClear();
            case FIND -> handleFind(input);
            case UNDO -> handleUndo();
            case UPDATE -> handleUpdate();
            case UNKNOWN -> ui.invalidCommand();
        };
    }

    private String handleBye() {
        database.update(tasks);
        return ui.farewell();
    }

    private String handleMark(String input) {
        try {
            int index = Parser.parseMarkCommand(input);
            database.update(tasks);
            tasks.mark(index);
            return ui.markedDone(tasks.getTask(index));
        } catch (NumberFormatException e) {
            return ui.invalidInput();
        } catch (IndexOutOfBoundsException e) {
            return ui.taskNotFound();
        }
    }

    private String handleUnmark(String input) {
        try {
            int index = Parser.parseUnmarkCommand(input);
            database.update(tasks);
            tasks.unmark(index);
            return ui.markedUndone(tasks.getTask(index));
        } catch (NumberFormatException e) {
            return ui.invalidInput();
        } catch (IndexOutOfBoundsException e) {
            return ui.taskNotFound();
        }
    }

    private String handleToDo(String input) {
        if (input.length() <= 5) {
            return ui.invalidInput();
        }

        database.update(tasks);

        String desc = input.substring(5).trim();
        Task task = new ToDoTask(desc);
        tasks.addTask(task);
        return ui.addedTask(task.getName(), tasks.size());
    }

    private String handleDeadline(String input) {
        int byIndex = input.indexOf(" /by");
        if (byIndex == -1 || byIndex <= 9 || input.length() <= byIndex + 4) {
            return ui.invalidInput();
        }

        database.update(tasks);

        String desc = Parser.getDeadlineDescription(input);
        String deadlineStr = Parser.getDeadlineString(input);
        LocalDateTime deadline = Parser.getDateTime(deadlineStr);

        if (deadline == null) {
            return ui.invalidDateTime();
        }

        Task task = new DeadlineTask(desc, deadline);
        tasks.addTask(task);
        return ui.addedTask(task.getName(), tasks.size());
    }

    private String handleEvent(String input) {
        int fromIndex = input.indexOf(" /from");
        int toIndex = input.indexOf(" /to", fromIndex + 1);
        if (fromIndex == -1 || toIndex == -1 || fromIndex <= 5 ||
                toIndex - (fromIndex + 6) <= 0 || input.length() <= toIndex + 4) {
            return ui.invalidInput();
        }

        database.update(tasks);

        String desc = Parser.getEventDescription(input);
        String startStr = Parser.getEventString(input, true);
        String endStr = Parser.getEventString(input, false);
        LocalDateTime start = Parser.getDateTime(startStr);
        LocalDateTime end = Parser.getDateTime(endStr);

        if (start == null || end == null) {
            return ui.invalidDateTime();
        }

        Task task = new EventTask(desc, start, end);
        tasks.addTask(task);
        return ui.addedTask(task.getName(), tasks.size());
    }

    private String handleDelete(String input) {
        try {
            int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                return ui.taskNotFound();
            }
            database.update(tasks);
            Task task = tasks.removeTask(taskNumber);
            return ui.deletedTask(task, tasks.size());
        } catch (NumberFormatException e) {
            return ui.invalidInput();
        }
    }

    private String handleClear() {
        int total = tasks.size();
        database.update(tasks);
        while (!tasks.isEmpty()) {
            tasks.removeTask(0);
        }
        return ui.clearedTasks(total);
    }

    private String handleFind(String input) {
        String toSearch = Parser.parseFindCommand(input);
        if (toSearch.isEmpty()) {
            return ui.invalidInput();
        }
        return ui.listFilteredTasks(tasks, toSearch);
    }

    private String handleUndo() {
        TaskList previousState = database.getPreviousState();
        if (previousState == null) {
            return ui.failedUndoCommand();
        }
        this.tasks = previousState;
        return ui.undidCommand();
    }

    private String handleUpdate() {
        database.update(tasks);
        int removed = tasks.removeDoneTasks();
        return ui.wipedDoneTasks(removed, tasks.size());
    }

}
