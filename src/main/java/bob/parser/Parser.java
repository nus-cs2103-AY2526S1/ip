package bob.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bob.command.AddCommand;
import bob.command.ByeCommand;
import bob.command.Command;
import bob.command.DeleteCommand;
import bob.command.FindCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.UnmarkCommand;
import bob.exception.InvalidEventUsageException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.TaskManager;
import bob.task.Todo;

/**
 * Parser is responsible to make sense of user's input
 */
public class Parser {
    private static final int INVALID_INDEX = -1;
    private TaskManager manager;

    /**
     * Constructor for parser
     * 
     * @param manager TaskManager object for managing tasks
     */
    public Parser(TaskManager manager) {
        this.manager = manager;
    }

    private static int delete(String input) {
        try {
            String[] parts = input.split(" ", 2);
            int idx = Integer.parseInt(parts[1]) - 1;
            return idx;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
            return INVALID_INDEX;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Attempted to delete task that does not exists!");
            return INVALID_INDEX;
        }
    }

    private Task addTodo(String input) {
        try {
            String[] parts = input.split(" ", 2);
            Todo todo = new Todo(parts[1], false, manager.assignId());
            assert todo != null : "Valid todo should not be null";
            return todo;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Usage todo <Task name>!");
        }
    }

    private Task addDeadline(String input) {
        Task ret;
        try {
            String pattern = "^deadline\\s+(.+)\\s+/by\\s+(.+)$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(input);
            if (!m.matches()) {
                throw new InvalidEventUsageException("");
            }
            String description = m.group(1).trim();
            String byTime = m.group(2).trim();
            LocalDate date;
            try {
                date = LocalDate.parse(byTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Date must be in yyyy-MM-dd format!");
                return null;
            }
            ret = new Deadline(description, date, false, manager.assignId());
            assert ret != null : "Valid deadline should not be null";
            return ret;
        } catch (InvalidEventUsageException e) {
            System.out.println("Usage: deadline <task desc> /by <yyyy-MM-dd>");
            return null;
        }
    }

    private Task addEvent(String input) {
        try {
            String pattern = "^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$";
            java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
            java.util.regex.Matcher m = r.matcher(input);
            if (!m.matches()) {
                throw new InvalidEventUsageException("");
            }
            String description = m.group(1).trim();
            String fromTime = m.group(2).trim();
            String toTime = m.group(3).trim();
            LocalDate fromDate;
            LocalDate toDate;
            try {
                fromDate = LocalDate.parse(fromTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                toDate = LocalDate.parse(toTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (toDate.isBefore(fromDate)) {
                    throw new InvalidEventUsageException("");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Dates must be in yyyy-MM-dd format!");
                return null;
            } catch (InvalidEventUsageException e) {
                System.out.println("You cannot time travel bro");
                return null;
            }
            Event event = new Event(description, fromDate, toDate, false, manager.assignId());
            assert event != null : "Valid event should not be null";
            return event;
        } catch (InvalidEventUsageException e) {
            System.out.println("Usage: event <task desc> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
            return null;
        }
    }

    private static int unmark(String input) {
        try {
            String[] parts = input.split(" ", 2);
            int idx = Integer.parseInt(parts[1]) - 1;
            return idx;
        } catch (NumberFormatException e) {
            return INVALID_INDEX;
        } catch (IndexOutOfBoundsException e) {
            return INVALID_INDEX;
        }
    }

    private static int mark(String input) {
        try {
            String[] parts = input.split(" ", 2);
            int idx = Integer.parseInt(parts[1]) - 1;
            return idx;
        } catch (NumberFormatException e) {
            return INVALID_INDEX;
        } catch (IndexOutOfBoundsException e) {
            return INVALID_INDEX;
        }
    }

    private String parse(String input) {
        String[] splitString = input.split(" ", 2);
        return splitString[0];
    }

    private Task findTask(String input) {
        try {
            Pattern pattern = Pattern.compile("^find\\s+(.+)$");
            Matcher matcher = pattern.matcher(input);
            String query = "";
            if (matcher.matches()) {
                query = matcher.group(1).trim();
                if (query.isEmpty()) {
                    throw new InvalidEventUsageException("");
                }
            }
            return new Task(query, "T", false, 0);
        } catch (InvalidEventUsageException e) {
            return null;
        }
    }

    private Command runTodo(String input) {
        try {
            Task todo = addTodo(input);
            if (todo == null) {
                throw new InvalidEventUsageException("");
            }
            return new AddCommand(todo);
        } catch (InvalidEventUsageException e) {
            return null;
        }
    }

    private Command runDeadline(String input) {
        try {
            Task deadline = addDeadline(input);
            if (deadline == null) {
                throw new InvalidEventUsageException("");
            }
            return new AddCommand(deadline);
        } catch (InvalidEventUsageException e) {
            return null;
        }
    }

    private Command runEvent(String input) {
        try {
            Task event = addEvent(input);
            if (event == null) {
                throw new InvalidEventUsageException("");
            }
            return new AddCommand(event);
        } catch (InvalidEventUsageException e) {
            return null;
        }
    }

    private Command runMark(String input) {
        try {
            int idx = mark(input);
            Task marked = manager.getTask(idx);
            if (marked == null || idx < 0) {
                throw new InvalidEventUsageException("");
            }
            return new MarkCommand(marked, idx);
        } catch (InvalidEventUsageException e) {
            return null;
        }
    }

    private Command runDelete(String input) {
        try {
            int idx = delete(input);
            Task deleted = manager.getTask(idx);
            if (deleted == null || idx < 0) {
                throw new InvalidEventUsageException("");
            }
            return new DeleteCommand(deleted, idx);
        } catch (InvalidEventUsageException e) {
            return null;
        }
    }

    private Command runFind(String input) {
        try {
            Task t = findTask(input);
            if (t == null) {
                throw new InvalidEventUsageException("");
            }
            return new FindCommand(t, 0);
        } catch (InvalidEventUsageException e) {
            return null;
        }
    }

    private Command runUnmark(String input) {
        try {
            int idx = unmark(input);
            Task unmarked = manager.getTask(idx);
            if (unmarked == null || idx < 0) {
                throw new InvalidEventUsageException("");
            }
            return new UnmarkCommand(unmarked, idx);
        } catch (InvalidEventUsageException e) {
            return null;
        }
    }

    /**
     * Executes the user input
     * 
     * @param input User input in string
     * @return The command object to be executed
     */

    public Command run(String input) {
        String firstArg = parse(input);
        switch (firstArg) {
        case "bye":
            return new ByeCommand(null);
        case "list":
            return new ListCommand(null);
        case "todo":
            return runTodo(input);
        case "deadline": {
            return runDeadline(input);
        }
        case "event": {
            return runEvent(input);
        }
        case "mark": {
            return runMark(input);
        }
        case "delete": {
            return runDelete(input);
        }
        case "find": {
            return runFind(input);

        }
        case "unmark": {
            return runUnmark(input);
        }
        default:
            return null;
        }
    }

}
