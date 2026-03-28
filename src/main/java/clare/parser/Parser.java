package clare.parser;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import clare.exception.StringConvertExceptions;
import clare.storage.Storage;
import clare.task.Deadline;
import clare.task.Event;
import clare.task.Task;
import clare.task.TaskList;
import clare.task.Todo;
import clare.ui.UI;

/**
 * Represents the class to parse commands
 */
public class Parser {
    private final UI ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructor for the class Parser
     *
     * @param ui The ui object that display the message
     * @param storage The storage object that handle data storage
     * @param taskList The list of tasks
     */
    public Parser(UI ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Processes command from input
     *
     * @param msg the command string
     */
    public void processCommand(String msg) {
        String[] splits = msg.split(" ");
        try {
            Commands command = Commands.valueOf(splits[0].toUpperCase());
            switch (command) {
            case BYE:
                ui.farewell();
                return;
            case LIST:
                showList();
                break;
            case MARK:
                msg = msg.substring(4).trim();
                mark(msg);
                break;
            case UNMARK:
                msg = msg.substring(6).trim();
                unmark(msg);
                break;
            case TODO:
                createTodo(msg);
                break;
            case DEADLINE:
                createDeadline(msg);
                break;
            case EVENT:
                createEvent(msg);
                break;
            case DELETE:
                msg = msg.substring(6).trim();
                delete(msg);
                break;
            case DUE:
                findByDeadline(msg);
                break;
            case FIND:
                findByTitle(msg);
                break;
            case SORT:
                sort(msg);
                break;
            case HELP:
                ui.showHelp();
                break;
            default:
                ui.showMessage("I don't understand this command :(");
                assert false; //assumes that all case will be handled by exceptions
                break;
            }
        } catch (IllegalArgumentException e) {
            ui.showMessage("I don't understand this command :(");
        }
    }

    private void createTodo(String msg) {
        msg = msg.substring(4).trim(); // remove prefix
        if (msg.isEmpty()) {
            ui.showMessage("Please add a description.");
            return;
        }

        Task newTask = new Todo(msg, false);
        try {
            storage.addData(newTask);
        } catch (IOException e) {
            ui.showMessage("Something went wrong." + e);
            return;
        }

        taskList.add(newTask);
        ui.showMessage("System update: New task added!\n" + newTask + "\nYou now have "
                + taskList.size() + " tasks in the system.");
    }

    private void createDeadline(String msg) {
        msg = msg.substring(8).trim(); // remove prefix duke.command
        String[] s = Arrays.stream(msg.split("/")) // separate the msg and trim
                .map(String::trim)
                .filter(str -> !str.isBlank())
                .toArray(String[]::new);
        if (s.length < 2) {
            ui.showMessage("Please add a deadline according to this format: deadline ... /by ....");
            return;
        }
        if (!s[1].startsWith("by ")) {
            ui.showMessage("Wrong format!!\nPlease input according to this format: deadline ... /by ...");
            return;
        }

        Task newTask;
        try {
            newTask = new Deadline(s[0], s[1].substring(3).trim(), false);
        } catch (StringConvertExceptions e) {
            ui.showMessage(e.toString());
            return;
        }

        try {
            storage.addData(newTask);
        } catch (IOException e) {
            ui.showMessage("Something went wrong." + e);
            return;
        }
        assert newTask instanceof Deadline;
        taskList.add(newTask);
        ui.showMessage("Deadline uploaded successfully!\n" + newTask + "\nYou now have "
                + taskList.size() + " tasks in the system.");
    }

    private void createEvent(String msg) {
        msg = msg.substring(5); // remove prefix duke.command
        String[] s = Arrays.stream(msg.split("/")) // separate the msg and trim
                .map(String::trim)
                .filter(str -> !str.isBlank())
                .toArray(String[]::new);
        if (s.length < 3) {
            ui.showMessage("Please add a deadline and start time "
                    + "according to this format: event ... /from ... /to ....");
            return;
        }
        if (!s[1].startsWith("from ") || !s[2].startsWith("to ")) {
            ui.showMessage("Wrong format!!\nPlease input according to this format: event ... /from ... /to ...");
            return;
        }

        Task newTask;
        try {
            newTask = new Event(s[0], s[1].substring(5).trim(), s[2].substring(3).trim(), false);
        } catch (StringConvertExceptions e) {
            ui.showMessage(e.toString());
            return;
        }

        try {
            storage.addData(newTask);
        } catch (IOException e) {
            ui.showMessage("Something went wrong." + e);
            return;
        }
        assert newTask instanceof Event;
        taskList.add(newTask);
        ui.showMessage("Event Initialized!\n" + newTask + "\nYou now have "
                + taskList.size() + " tasks in the system.");
    }

    private void showList() {
        ui.showMessage("Current task database:\n" + taskList.getAllTaskString());
    }

    private void mark(String num) {
        int i;
        try {
            i = Integer.parseInt(num) - 1;
        } catch (NumberFormatException e) {
            ui.showMessage("This is not a number!!");
            return;
        }

        Task t;
        try {
            t = taskList.markDone(i);
            storage.rewriteData(taskList);
        } catch (IOException e) {
            ui.showMessage("Something went wrong. " + e);
            return;
        } catch (StringConvertExceptions e) {
            ui.showMessage("Invalid format: " + e);
            return;
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("There is no such task!");
            return;
        }
        ui.showMessage("Task status updated: Completed!\n" + (i + 1) + ". " + t + "\nWell Done!!");
    }

    private void unmark(String num) {
        int i;
        try {
            i = Integer.parseInt(num) - 1;
        } catch (NumberFormatException e) {
            ui.showMessage("This is not a number!!");
            return;
        }

        Task t;
        try {
            t = taskList.markUndone(i);
            storage.rewriteData(taskList);
        } catch (IOException e) {
            ui.showMessage("Something went wrong. " + e);
            return;
        } catch (StringConvertExceptions e) {
            ui.showMessage("Invalid format: " + e);
            return;
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("No such task!");
            return;
        }
        ui.showMessage("Task reactivated!\n" + (i + 1) + ". " + t);
    }

    private void delete(String num) {
        int i;
        try {
            i = Integer.parseInt(num) - 1;
        } catch (NumberFormatException e) {
            ui.showMessage("This is not a number!!");
            return;
        }

        Task t;
        try {
            t = taskList.delete(i);
            storage.rewriteData(taskList);
        } catch (IOException e) {
            ui.showMessage("Something went wrong. " + e);
            return;
        } catch (StringConvertExceptions e) {
            ui.showMessage("Invalid format: " + e);
            return;
        }
        ui.showMessage("Task removed from memory banks:\n" + t + "\nYou now have "
                + taskList.size() + " tasks in the system.");
    }

    private void findByDeadline(String msg) {
        String[] s = msg.substring(3).trim().split(" ");
        LocalDate date;
        try {
            date = LocalDate.parse(s[0]);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            ui.showMessage("Invalid format: please follow this format, YYYY-MM-DD");
            return;
        }
        ui.showMessage(taskList.findTaskByDeadline(date));
    }

    private void findByTitle(String msg) {
        try {
            msg = msg.substring(5);
            String[] s = Arrays.stream(msg.split(" "))
                    .filter(str -> !str.isBlank())
                    .map(String::trim)
                    .toArray(String[]::new);
            ui.showMessage(taskList.findTaskByTitle(s));
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("Please provide a description");
        }
    }

    private void sort(String msg) {
        boolean isAsc = true;
        msg = msg.substring(4).trim();
        if (msg.startsWith("-")) {
            if (msg.startsWith("-d")) {
                isAsc = false;
            } else if (!msg.startsWith("-a")) {
                ui.showMessage("Unknown flag, please follow format -- sort [-a/-d] [title/deadline/start] ");
                return;
            }
            msg = msg.substring(2).trim();
        }
        if (msg.startsWith("title")) {
            ui.showMessage(taskList.sortTaskByTitle(isAsc));
        } else if (msg.startsWith("deadline")) {
            ui.showMessage(taskList.sortTaskByDeadline(isAsc));
        } else if (msg.startsWith("start")) {
            ui.showMessage(taskList.sortTaskByStartDate(isAsc));
        } else {
            ui.showMessage("Command does not exist, please follow format -- sort [-a/-d] [title/deadline/start] ");
            return;
        }
        try {
            storage.rewriteData(taskList);
        } catch (IOException e) {
            ui.showMessage("Storage error.");
        }
    }
}
