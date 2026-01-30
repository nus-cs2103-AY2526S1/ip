package eve;

import java.util.List;

import eve.ui.Ui;
import eve.parser.Parser;
import eve.parser.EveException;
import eve.parser.Parser.Command;
import eve.parser.Parser.DeadlineParts;
import eve.parser.Parser.EventParts;
import eve.parser.Parser.PeriodParts;
import eve.storage.Storage;
import eve.tasks.Task;
import eve.tasks.Todo;
import eve.tasks.Deadline;
import eve.tasks.DoWithinPeriod;
import eve.tasks.Event;

/**
 * Entry point for the Eve chatbot application.
 * <p>
 * The Eve class wires together the UI, parser, storage, and task list
 * components to provide an interactive chatbot that
 * manages tasks such as Todos, Deadlines, and Events.
 * </p>
 */
public class Eve {

    /** Handles all user input and output. */
    private final Ui ui = new Ui();

    /** Responsible for loading and saving tasks to disk. */
    private final Storage storage = new Storage("data/eve.txt");

    /** Encapsulates the in-memory list of tasks. */
    private TaskList tasks;

    /**
     * Constructs a new Eve chatbot instance.
     * <p>
     * Loads previously saved tasks from storage (if available) and
     * initializes the in-memory {@link TaskList}.
     */
    public Eve() {
        List<Task> loaded = storage.load();
        tasks = new TaskList(loaded);
    }

    /**
     * Runs the chatbot in interactive CLI mode.
     * Displays the welcome message, enters the command loop,
     * and exits when the user types {@code bye} or EOF is reached.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String full = ui.readCommand();
            if (full == null) {
                ui.showError("Goodbye (EOF).");
                break;
            }
            full = full.trim();
            if (full.isEmpty()) {
                continue;
            }

            Command cmd = Parser.parseCommand(full);
            if (cmd == null) {
                ui.showUnknown();
                continue;
            }

            try {
                isExit = executeCommand(cmd, Parser.args(full));
            } catch (EveException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    /**
     * Executes a command in CLI mode, performing the required task manipulation
     * and printing feedback through the UI.
     *
     * @param cmd  the parsed command
     * @param args the arguments string associated with the command
     * @return true if the command signals exit (BYE), false otherwise
     * @throws EveException if parsing or execution fails
     */
    private boolean executeCommand(Command cmd, String args) throws EveException {
        switch (cmd) {
            case HELP:
                ui.showHelp();
                break;
            case LIST:
                ui.showList(tasks.asList());
                break;
            case TODO:
                executeTodo(args);
                break;
            case DEADLINE:
                executeDeadline(args);
                break;
            case EVENT:
                executeEvent(args);
                break;
            case PERIOD:
                executePeriod(args);
                break;
            case FIND:
                executeFind(args);
                break;
            case MARK:
                executeMark(args, true);
                break;
            case UNMARK:
                executeMark(args, false);
                break;
            case DELETE:
                executeDelete(args);
                break;
            case BYE:
                return true;
            default:
                ui.showUnknown();
        }
        return false;
    }

    // === Command Helpers (CLI mode) ===

    /** Adds a new Todo task. */
    private void executeTodo(String args) throws EveException {
        String desc = Parser.parseTodoDesc(args);
        Task t = tasks.add(new Todo(desc));
        storage.save(tasks.asList());
        ui.showAdded(t, tasks.size());
    }

    /** Adds a new Deadline task. */
    private void executeDeadline(String args) throws EveException {
        DeadlineParts p = Parser.parseDeadline(args);
        Task t = tasks.add(new Deadline(p.desc, p.when));
        storage.save(tasks.asList());
        ui.showAdded(t, tasks.size());
    }

    /** Adds a new Event task. */
    private void executeEvent(String args) throws EveException {
        EventParts p = Parser.parseEvent(args);
        Task t = tasks.add(new Event(p.desc, p.from, p.to));
        storage.save(tasks.asList());
        ui.showAdded(t, tasks.size());
    }

    /** Adds a new DoWithinPeriod task. */
    private void executePeriod(String args) throws EveException {
        PeriodParts p = Parser.parsePeriod(args);
        Task t = tasks.add(new DoWithinPeriod(p.desc, p.start, p.end));
        storage.save(tasks.asList());
        ui.renderAdded(t, tasks.size());
    }

    /** Searches tasks by keyword. */
    private void executeFind(String args) throws EveException {
        String q = Parser.parseFind(args);
        ui.showFindResults(tasks.find(q));
    }

    /** Marks or unmarks a task. */
    private void executeMark(String args, boolean done) throws EveException {
        int n = Parser.parseIndex(args, done);
        if (tasks.isEmpty()) {
            ui.showError("There aren’t any tasks yet~ (✿◠‿◠) Add one first!");
            return;
        }
        if (n < 1 || n > tasks.size()) {
            ui.showError("Please provide a valid task number (1-" + tasks.size() + ").");
            return;
        }
        Task t = tasks.setDone(n - 1, done);
        storage.save(tasks.asList());
        ui.showMarked(t, done);
    }

    /** Deletes a task at the given index. */
    private void executeDelete(String args) throws EveException {
        int n = Parser.parseDeleteIndex(args);
        if (tasks.isEmpty()) {
            ui.showError("There aren’t any tasks yet~ (✿◠‿◠) Add one first!");
            return;
        }
        if (n < 1 || n > tasks.size()) {
            ui.showError("Please provide a valid task number (1-" + tasks.size() + ").");
            return;
        }
        Task removed = tasks.deleteAt(n - 1);
        storage.save(tasks.asList());
        ui.showDeleted(removed, tasks.size());
    }

    /**
     * Entry point for launching Eve in CLI mode.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Eve().run();
    }

    /**
     * Processes a single user command and returns Eve's response as a string.
     * Used in GUI mode.
     *
     * @param full the full command string entered by the user
     * @return Eve's response to the command
     */
    public String getResponse(String full) {
        if (full == null || full.trim().isEmpty()) {
            return "Please type a command.";
        }
        full = full.trim();
        Command cmd = Parser.parseCommand(full);
        if (cmd == null) {
            return "Sorry, I don't know that command (T_T) \nMaybe try 'help' so I can guide you?";
        }

        try {
            return executeCommandForGui(cmd, Parser.args(full));
        } catch (EveException e) {
            return e.getMessage();
        }
    }

    /**
     * Executes a command in GUI mode and returns the rendered string output.
     *
     * @param cmd  the parsed command
     * @param args the arguments string associated with the command
     * @return the string response for display in the GUI
     * @throws EveException if parsing or execution fails
     */
    private String executeCommandForGui(Command cmd, String args) throws EveException {
        switch (cmd) {
            case HELP:
                return ui.renderHelp();
            case LIST:
                return ui.renderList(tasks.asList());
            case TODO:
                return renderTodo(args);
            case DEADLINE:
                return renderDeadline(args);
            case EVENT:
                return renderEvent(args);
            case PERIOD:
                return renderPeriod(args);
            case MARK:
                return renderMark(args, true);
            case UNMARK:
                return renderMark(args, false);
            case DELETE:
                return renderDelete(args);
            case FIND:
                return renderFind(args);
            case BYE:
                return "Bye. Hope to see you again soon!";
            default:
                return "Sorry, I don't know that command.";
        }
    }

    // === Command Helpers (GUI mode) ===

    private String renderTodo(String args) throws EveException {
        String desc = Parser.parseTodoDesc(args);
        Task t = tasks.add(new Todo(desc));
        storage.save(tasks.asList());
        return ui.renderAdded(t, tasks.size());
    }

    private String renderDeadline(String args) throws EveException {
        DeadlineParts p = Parser.parseDeadline(args);
        Task t = tasks.add(new Deadline(p.desc, p.when));
        storage.save(tasks.asList());
        return ui.renderAdded(t, tasks.size());
    }

    private String renderEvent(String args) throws EveException {
        EventParts p = Parser.parseEvent(args);
        Task t = tasks.add(new Event(p.desc, p.from, p.to));
        storage.save(tasks.asList());
        return ui.renderAdded(t, tasks.size());
    }

    private String renderPeriod(String args) throws EveException {
        PeriodParts p = Parser.parsePeriod(args);
        Task t = tasks.add(new DoWithinPeriod(p.desc, p.start, p.end));
        storage.save(tasks.asList());
        return ui.renderAdded(t, tasks.size());
    }

    private String renderMark(String args, boolean done) throws EveException {
        int n = Parser.parseIndex(args, done);
        if (n < 1 || n > tasks.size()) {
            return "Please provide a valid task number (1-" + tasks.size() + ").";
        }
        Task t = tasks.setDone(n - 1, done);
        storage.save(tasks.asList());
        return ui.renderMarked(t, done);
    }

    private String renderDelete(String args) throws EveException {
        int n = Parser.parseDeleteIndex(args);
        if (n < 1 || n > tasks.size()) {
            return "Please provide a valid task number (1-" + tasks.size() + ").";
        }
        Task removed = tasks.deleteAt(n - 1);
        storage.save(tasks.asList());
        return ui.renderDeleted(removed, tasks.size());
    }

    private String renderFind(String args) throws EveException {
        String keyword = args.trim();
        if (keyword.isEmpty()) {
            return "Please provide a keyword to search for.";
        }
        List<Task> matches = tasks.find(keyword);
        return ui.renderMatches(matches);
    }
}
