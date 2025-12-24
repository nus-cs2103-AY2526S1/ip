package finixx.parser;

import finixx.exception.FinixxException;
import finixx.storage.Storage;
import finixx.task.DeadlineTask;
import finixx.task.EventTask;
import finixx.task.Task;
import finixx.task.TaskCollection;
import finixx.task.TodoTask;
import finixx.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The Parser class is responsible for interpreting user input commands and executing the corresponding actions.
 * It supports various commands such as adding tasks, marking tasks as done, deleting tasks, and more.
 * The class interacts with the TaskCollection to manage tasks and uses the Ui class to generate user-friendly
 * responses.
 */
public class Parser {

    /**
     * Represents the various commands that the parser can recognize and handle.
     */
    public enum Command {
        BYE,
        LIST,
        HELP,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        NOTE,
        UNKNOWN
    }

    private static Command parseCommand(String input) {
        return switch (input.toLowerCase()) {
            case "bye" -> Command.BYE;
            case "list" -> Command.LIST;
            case "help" -> Command.HELP;
            case "mark" -> Command.MARK;
            case "unmark" -> Command.UNMARK;
            case "delete" -> Command.DELETE;
            case "todo" -> Command.TODO;
            case "deadline" -> Command.DEADLINE;
            case "event" -> Command.EVENT;
            case "find" -> Command.FIND;
            case "note" -> Command.NOTE;
            default -> Command.UNKNOWN;
        };
    }

    /**
     * Parses input and returns a response string for GUI display.
     *
     * @param input The full user input string
     * @param ui The UI instance for displaying messages
     * @param tasks The collection of tasks to operate on
     * @param storage The storage handler for saving tasks
     * @return The response string to display in the GUI
     */
    public static String parse(String input, Ui ui, TaskCollection tasks, Storage storage) {
        input = input.trim().replaceAll("\\s+", " ");

        String[] parts = input.split(" ", 2);
        assert parts.length >= 1 : "There should be at least one command in the input!";

        String command = parts[0];
        Command commandEnum = parseCommand(command);
        String arg = (parts.length > 1) ? parts[1] : "";

        try {
            switch (commandEnum) {
            case BYE:
                return ui.printGoodbye();

            case LIST:
                return ui.printList(tasks);

            case HELP:
                return Ui.printHelp();

            case MARK:
                validateTaskIndex(arg, tasks);

                assert !arg.isEmpty() : "task number to be marked should not be empty!";
                int taskIndexMark = Integer.parseInt(arg);
                tasks.getTask(taskIndexMark - 1).markAsDone();
                storage.saveTasks(tasks);
                return ui.printMarked(tasks, taskIndexMark);

            case UNMARK:
                validateTaskIndex(arg, tasks);

                assert !arg.isEmpty() : "task number to be marked should not be empty!";
                int taskIndexUnmark = Integer.parseInt(arg);
                tasks.getTask(taskIndexUnmark - 1).markAsNotDone();
                storage.saveTasks(tasks);
                return ui.printUnmarked(tasks, taskIndexUnmark);

            case DELETE:
                validateTaskIndex(arg, tasks);

                assert !arg.isEmpty() : "task number to be marked should not be empty!";
                int taskIndexDelete = Integer.parseInt(arg);
                String response = ui.printDeleted(tasks, taskIndexDelete);
                tasks.removeTask(taskIndexDelete - 1);
                storage.saveTasks(tasks);
                return response;

            case TODO:
                TodoTask t = createTodoTask(arg);
                tasks.addTask(t);
                storage.saveTasks(tasks);
                return ui.printAddedTodo(tasks, t);

            case DEADLINE:
                DeadlineTask d = createDeadlineTask(arg);
                tasks.addTask(d);
                storage.saveTasks(tasks);
                return ui.printAddedDeadline(tasks, d);

            case EVENT:
                EventTask e = createEventTask(arg);
                tasks.addTask(e);
                storage.saveTasks(tasks);
                return ui.printAddedEvent(tasks, e);

            case FIND:
                if (arg.isEmpty()) {
                    throw new FinixxException("Hmm… without a keyword, your hidden tasks stay lost in the ashes!");
                }
                List<Task> matchingTasks = tasks.findTasks(arg);
                return ui.printFindPossible(matchingTasks);

            case NOTE:
                String[] details = arg.split(" ", 2);
                String taskIndex = details[0];
                validateTaskIndex(taskIndex, tasks);

                assert !arg.isEmpty() : "task number to be marked should not be empty!";
                int taskIndexNote = Integer.parseInt(taskIndex);
                String notes = (details.length > 1) ? details[1] : "";
                tasks.getTask(taskIndexNote - 1).addNote(notes);
                storage.saveTasks(tasks);
                return ui.printAddedNote(tasks, taskIndexNote);

            case UNKNOWN:
            default:
                throw new FinixxException("""
                        Oops! That’s not in my to-do spellbook!
                        Here are the commands I understand:
                        - list
                        - help
                        - mark
                        - unmark
                        - delete
                        - todo
                        - deadline
                        - event
                        - find
                        - note
                        - bye""");
            }

        } catch (FinixxException e) {
            return e.printMessage();
        }
    }

    private static boolean isInteger(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void validateTaskIndex(String arg, TaskCollection tasks) throws FinixxException {
        if (arg.isEmpty()) {
            throw new FinixxException("Ah! I need a task number to ignite the flames of action!");
        }
        if (!isInteger(arg)) {
            throw new FinixxException("Ah! " + arg + " is not an integer — I need a proper task number!");
        } else if (Integer.parseInt(arg) < 1 || Integer.parseInt(arg) > tasks.getSize()) {
            throw new FinixxException(
                    "Ah! There’s no task with number '" + arg + "'. Try a number that actually exists!");
        }
    }

    private static TodoTask createTodoTask(String arg) throws FinixxException {
        String[] argParts = arg.split("\\s*\\|\\s*", 2);
        String description = argParts[0].trim();
        String notes = (argParts.length > 1) ? argParts[1] : "";

        if (description.isEmpty()) {
            throw new FinixxException(
                    "Ah! You need to give your task a proper description so it can rise from the ashes!");
        }

        return new TodoTask(description, false, notes);
    }

    private static DeadlineTask createDeadlineTask(String arg)
            throws FinixxException {

        String[] argParts = arg.split("\\s*\\|\\s*", 2);
        String details = argParts[0];
        String notes = (argParts.length > 1) ? argParts[1] : "";

        if (details.isEmpty()) {
            throw new FinixxException(
                    "Ah! You need to give your task a proper description so it can rise from the ashes!");
        }

        if (!details.contains("/by")) {
            throw new FinixxException(
                    "Whoops! your deadline spell didn't work. Use: deadline <description> /by <yyyy-MM-dd>");
        }

        String[] detailParts = details.split("\\s*/by\\s*", 2);
        String description = detailParts[0].trim();
        String deadline = (detailParts.length > 1) ? detailParts[1] : "";

        if (deadline.isEmpty()) {
            throw new FinixxException("Oh no! I can’t ignite a task without a deadline");
        }

        if (description.isEmpty()) {
            throw new FinixxException(
                    "Ah! You need to give your task a proper description so it can rise from the ashes!");
        }

        // Validate deadline format
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDeadline = LocalDate.parse(deadline, formatter);

            if (parsedDeadline.isBefore(LocalDate.now())) {
                throw new FinixxException("You can’t set a deadline before today. Let’s aim for the future!");
            }

            deadline = parsedDeadline.toString(); // normalized format
        } catch (DateTimeParseException e) {
            throw new FinixxException("Whoops! The deadline spell didn’t take flight — use yyyy-MM-dd format!");
        }

        return new DeadlineTask(description, false, notes, deadline);
    }

    private static EventTask createEventTask(String arg) throws FinixxException {
        String[] argParts = arg.split("\\s*\\|\\s*", 2);
        String details = argParts[0].trim();
        String notes = (argParts.length > 1) ? argParts[1].trim() : "";

        if (details.isEmpty()) {
            throw new FinixxException(
                    "Ah! You need to give your task a proper description so it can rise from the ashes!");
        }

        if (!details.contains("/from") || !details.contains("/to")) {
            throw new FinixxException(
                    "Whoops! your deadline spell didn't work. Use: event <description> /from <start time> /to <end "
                            + "time>");
        }

        try {
            // Split into description and the rest
            String[] parts = details.split("/from", 2);
            String description = parts[0].trim();

            String[] timeParts = parts[1].split("/to", 2);
            String startTime = timeParts[0].trim();
            String endTime = timeParts[1].trim();

            // Validate
            if (description.isEmpty()) {
                throw new FinixxException(
                        "Ah! You need to give your task a proper description so it can rise from the ashes!");
            }
            if (startTime.isEmpty()) {
                throw new FinixxException("Oops! Your event needs a start time to take flight");
            }
            if (endTime.isEmpty()) {
                throw new FinixxException("Oops! Your event needs an end time to complete its fiery journey");
            }

            return new EventTask(description, false, notes, startTime, endTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FinixxException(
                    "Whoops! your deadline spell didn't work. Use: event <description> /from <start time> /to <end "
                            + "time>");
        }
    }
}
