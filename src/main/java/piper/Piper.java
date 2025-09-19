package piper;

import piper.parser.CommandType;
import piper.parser.Parser;
import piper.storage.Storage;
import piper.task.Deadline;
import piper.task.Event;
import piper.task.Task;
import piper.task.TaskList;
import piper.task.Todo;
import piper.ui.Ui;

/**
 * Entry point of the Piper application.
 * Initializes the UI, loads tasks from storage, and runs the REPL loop.
 * REPL loop accepts user commands until bye is entered.
 */
public class Piper {
    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = "piper.txt";
    private static final String CHATBOT_NAME = "Piper";

    private final Ui ui = new Ui(CHATBOT_NAME);
    private TaskList tasks;
    private Storage storage;
    private boolean isExit = false;

    /** Constructor for GUI */
    public Piper() {
        try {
            this.storage = new Storage(DATA_DIR, DATA_FILE);
            this.tasks = storage.load();
        } catch (PiperException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Processes a single line of user input and generates an appropriate response.
     * Acts as the central command handler for the Piper chatbot.
     * If the input is invalid or an error occurs, a descriptive error message is returned instead.
     *
     * @param userInput the raw user input string
     * @return a string containing Piper's reply, including task updates and error messages
     */
    public String run(String userInput) {
        String trimmed = userInput == null ? "" : userInput.trim();
        StringBuilder reply = new StringBuilder();
        try {
            Parser.ParsedString ps = Parser.parse(trimmed);
            CommandType cmd = ps.cmdType();
            assert cmd != null : "Parser must return a non-null command";
            String arg = ps.arg();

            int initSize = tasks.getSize();

            if (arg == null) {
                reply.append(handleNoArgCommand(cmd, trimmed));
            } else {
                switch (cmd) {
                    case MARK:
                    case UNMARK:
                        reply.append(handleMarkUnmark(cmd, arg));
                        break;
                    case DELETE:
                        reply.append(handleDelete(arg, initSize));
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        reply.append(handleAddTask(cmd, arg, initSize));
                        break;
                    case FIND:
                        reply.append(handleFind(arg));
                        break;
                    case SNOOZE:
                        reply.append(handleSnooze(arg));
                        break;
                default:
                    throw new PiperException(
                            "CHEEP CHEEP! I can't quite sing along with '"
                                    + trimmed + "'. Wanna try another command?");
                }
            }
        } catch (PiperException e) {
            reply.append(ui.showError(e.getMessage()));
        }
        return reply.toString();
    }

    public String getGreeting() {
        return ui.greetUser();
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Saves tasks to storage if storage has been set up.
     */
     private void saveToStorage() throws PiperException {
         if (storage != null) {
             storage.saveAll(tasks);
         }
     }

    /**
     * Persists the current tasks to storage twice, with an assertion that the
     * task list size has changed by the expected delta from the initial size.
     * This preserves the behavior of addition or deletion operations that
     * expect the size to increase or decrease by exactly one.
     *
     * @param expectedDelta the expected change in task list size (e.g. +1 or -1)
     * @param initSize the task list size before the operation.
     * @throws PiperException if saving to storage fails.
     */
    private void persistAndAssertCount(int expectedDelta, int initSize) throws PiperException {
        saveToStorage();
        assert tasks.getSize() == initSize + expectedDelta
                : (expectedDelta > 0
                ? "New task addition should increase number of tasks by " + expectedDelta
                : "Task deletion should decrease number of tasks by " + (-expectedDelta));
        saveToStorage();
    }

    /**
     * Parses a 1-based task index string and retrieves the corresponding task.
     *
     * @param arg the raw user input containing the task index.
     * @return the {@code Task} object at the given index.
     * @throws PiperException if the index is not a valid number.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    private Task getTaskBy1Based(String arg) throws PiperException, IndexOutOfBoundsException {
        int taskNumber = Parser.parseIndex(arg);
        int index = taskNumber - 1;
        return tasks.getTask(index);
    }

    /**
     * Handles commands that require no argument, such as `bye` and `list`.
     *
     * @param cmd the parsed command type.
     * @param trimmed the normalized user input string.
     * @return Piper's reply string for the command.
     * @throws PiperException if the command is unrecognized in this context.
     */
    private String handleNoArgCommand(CommandType cmd, String trimmed) throws PiperException {
        switch (cmd) {
            case BYE:
                isExit = true;
                return ui.farewellUser();
            case LIST:
                return ui.displayTasks(tasks);
            default:
                throw new PiperException(
                        "CHEEP CHEEP! I can't quite sing along with '"
                                + trimmed + "'. Wanna try another command?");
        }
    }

    /**
     * Handles `mark` and `unmark` commands, updating a task's status.
     *
     * @param cmd either `mark` or `unmark`.
     * @param arg the argument containing the task index.
     * @return a reply string reflecting the updated task status.
     * @throws PiperException if the index is invalid or out of range.
     */
    private String handleMarkUnmark(CommandType cmd, String arg) throws PiperException {
        try {
            Task task = getTaskBy1Based(arg);
            if (cmd == CommandType.MARK) {
                task.markDone();
                assert "X".equals(task.getStatusIcon()) : "Task should be marked done";
            } else {
                task.markUndone();
                assert " ".equals(task.getStatusIcon()) : "Task should be marked undone";
            }
            saveToStorage();
            return ui.showTaskStatus(task);
        } catch (IndexOutOfBoundsException ex) {
            throw new PiperException(
                    "PEEP! That task flew out of the nest. "
                            + "Please check using 'list' to see which tasks are home!");
        }
    }

    /**
     * Handles task deletion.
     *
     * @param arg the argument containing the task index.
     * @param initSize the task list size before deletion.
     * @return a reply string confirming deletion and showing the new list size.
     * @throws PiperException if the index is invalid or out of range.
     */
    private String handleDelete(String arg, int initSize) throws PiperException {
        try {
            int taskNumber = Parser.parseIndex(arg);
            int index = taskNumber - 1;
            Task task = tasks.getTask(index);
            tasks.deleteTask(index);
            persistAndAssertCount(-1, initSize);
            return ui.showDeletedTask(task) + System.lineSeparator() + ui.showTasksSize(tasks);
        } catch (IndexOutOfBoundsException ex) {
            throw new PiperException("PEEP! Bad egg. Please check using 'list' to see which tasks are home!");
        }
    }

    /**
     * Builds a new Task instance from the given command type and arguments.
     *
     * @param cmd the command type.
     * @param arg the raw arguments string.
     * @return a new task corresponding to the command.
     * @throws PiperException if parsing fails or an unsupported type is given.
     */
    private Task buildTask(CommandType cmd, String arg) throws PiperException {
        switch (cmd) {
            case TODO:
                return new Todo(arg);
            case DEADLINE: {
                Parser.DeadlineArgs da = Parser.parseDeadlineArgs(arg);
                return new Deadline(da.description(), da.by());
            }
            case EVENT: {
                Parser.EventArgs ea = Parser.parseEventArgs(arg);
                return new Event(ea.description(), ea.from(), ea.to());
            }
            default:
                throw new PiperException("Unexpected command for buildTask: " + cmd);
        }
    }

    /**
     * Handles task addition.
     *
     * @param cmd the command type.
     * @param arg the argument containing the task index.
     * @param initSize the task list size before deletion.
     * @return a reply string confirming addition and showing the new list size.
     * @throws PiperException if the index is invalid or out of range.
     */
    private String handleAddTask(CommandType cmd, String arg, int initSize) throws PiperException {
        Task task = buildTask(cmd, arg);
        tasks.addTask(task);
        persistAndAssertCount(+1, initSize);
        return ui.showAddedTask(task) + System.lineSeparator() + ui.showTasksSize(tasks);
    }

    /**
     * Handles the `find` command by searching for matching tasks.
     *
     * @param arg the search keyword.
     * @return a reply string listing tasks that match the keyword.
     */
    private String handleFind(String arg) throws PiperException {
        TaskList matches = tasks.find(arg);
        assert matches != null : "Function find(String kw) should not return null";
        return ui.displayMatchingTasks(matches);
    }

    /**
     * Handles the `snooze` command, shifting deadlines or event dates by the given number of days.
     *
     * @param arg the argument string containing the task index and number of days.
     * @return a reply string reflecting the updated task.
     * @throws PiperException if the task cannot be snoozed, dates are invalid, or index is out of range.
     */
    private String handleSnooze(String arg) throws PiperException {
        Parser.SnoozeArgs sa = Parser.parseSnoozeArgs(arg);
        int index = sa.index() - 1;
        try {
            Task task = tasks.getTask(index);
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                java.time.LocalDate date = java.time.LocalDate.parse(
                        d.toSerializedLine().split(" \\| ", 4)[3]); // current /by
                java.time.LocalDate updatedDate = date.plusDays(sa.days());
                d.updateByDate(updatedDate.toString());
            } else if (task instanceof Event) {
                Event e = (Event) task;
                String[] substrings = e.toSerializedLine().split(" \\| ", 6);
                java.time.LocalDate from = java.time.LocalDate.parse(substrings[3]);
                java.time.LocalDate to = java.time.LocalDate.parse(substrings[4]);
                e.updateRange(from.plusDays(sa.days()).toString(), to.plusDays(sa.days()).toString());
            } else {
                throw new PiperException("PEEP! Only deadlines or events can be snoozed.");
            }
            saveToStorage();
            return ui.showSnoozedTask(task);
        } catch (java.time.format.DateTimeParseException e) {
            throw new PiperException("I only understand ISO dates...");
        } catch (IndexOutOfBoundsException e) {
            throw new PiperException(
                    "PEEP! That task flew out of the nest. "
                            + "Please check using 'list' to see which tasks are home!");
        }
    }
     
}
