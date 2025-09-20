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
 * components to provide an interactive command-line chatbot that
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

    public Eve() {
        List<Task> loaded = storage.load();
        tasks = new TaskList(loaded);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String full = ui.readCommand();
            if (full == null) {
                ui.showError("Goodbye (EOF).");
                break;
            } // EOF
            full = full.trim();
            if (full.isEmpty()) {
                continue;
            }

            Command cmd = Parser.parseCommand(full);
            if (cmd == null) {
                ui.showUnknown();
                continue;
            }

            String args = Parser.args(full);
            try {
                switch (cmd) {
                    case HELP:
                        ui.showHelp();
                        break;
                    case LIST:
                        ui.showList(tasks.asList());
                        break;
                    case TODO: {
                        String desc = Parser.parseTodoDesc(args);
                        Task t = tasks.add(new Todo(desc));
                        storage.save(tasks.asList());
                        ui.showAdded(t, tasks.size());
                        break;
                    }
                    case PERIOD: {
                        Parser.PeriodParts p = Parser.parsePeriod(args);
                        Task t = tasks.add(new DoWithinPeriod(p.desc, p.start, p.end));
                        storage.save(tasks.asList());
                        ui.renderAdded(t, tasks.size());
                        break;
                    }
                    case DEADLINE: {
                        DeadlineParts p = Parser.parseDeadline(args);
                        Task t = tasks.add(new Deadline(p.desc, p.when));
                        storage.save(tasks.asList());
                        ui.showAdded(t, tasks.size());
                        break;
                    }
                    case FIND: {
                        String q = Parser.parseFind(args);
                        ui.showFindResults(tasks.find(q));
                        break;
                    }
                    case EVENT: {
                        EventParts p = Parser.parseEvent(args);
                        Task t = tasks.add(new Event(p.desc, p.from, p.to));
                        storage.save(tasks.asList());
                        ui.showAdded(t, tasks.size());
                        break;
                    }
                    case MARK: {
                        int n = Parser.parseIndex(args, true);
                        if (tasks.isEmpty()) {
                            ui.showError("There aren’t any tasks yet~ (✿◠‿◠) Add one first!");
                            break;
                        }
                        if (n < 1 || n > tasks.size()) {
                            ui.showError("Please provide a valid task number (1-" + tasks.size() + ").");
                            break;
                        }
                        Task t = tasks.setDone(n - 1, true);
                        storage.save(tasks.asList());
                        ui.showMarked(t, true);
                        break;
                    }
                    case UNMARK: {
                        int n = Parser.parseIndex(args, false);
                        if (tasks.isEmpty()) {
                            ui.showError("There aren’t any tasks yet~ (✿◠‿◠) Add one first!");
                            break;
                        }
                        if (n < 1 || n > tasks.size()) {
                            ui.showError("Please provide a valid task number (1-" + tasks.size() + ").");
                            break;
                        }
                        Task t = tasks.setDone(n - 1, false);
                        storage.save(tasks.asList());
                        ui.showMarked(t, false);
                        break;
                    }
                    case DELETE: {
                        int n = Parser.parseDeleteIndex(args);
                        if (tasks.isEmpty()) {
                            ui.showError("There aren’t any tasks yet~ (✿◠‿◠) Add one first!");
                            break;
                        }
                        if (n < 1 || n > tasks.size()) {
                            ui.showError("Please provide a valid task number (1-" + tasks.size() + ").");
                            break;
                        }
                        Task removed = tasks.deleteAt(n - 1);
                        storage.save(tasks.asList());
                        ui.showDeleted(removed, tasks.size());
                        break;
                    }
                    case BYE: {
                        isExit = true;
                        break;
                    }
                }
            } catch (EveException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Eve().run();
    }

    public String getResponse(String full) {
        if (full == null || full.trim().isEmpty()) {
            return "Please type a command.";
        }
        full = full.trim();
        Command cmd = Parser.parseCommand(full);
        if (cmd == null) {
            return "Sorry, I don't know that command (T_T) \nMaybe try 'help' so I can guide you?";
        }

        String args = Parser.args(full);
        try {
            switch (cmd) {
                case HELP:
                    return ui.renderHelp();
                case LIST:
                    return ui.renderList(tasks.asList());
                case TODO: {
                    String desc = Parser.parseTodoDesc(args);
                    Task t = tasks.add(new Todo(desc));
                    storage.save(tasks.asList());
                    return ui.renderAdded(t, tasks.size());
                }
                case DEADLINE: {
                    DeadlineParts p = Parser.parseDeadline(args);
                    Task t = tasks.add(new Deadline(p.desc, p.when));
                    storage.save(tasks.asList());
                    return ui.renderAdded(t, tasks.size());
                }
                case EVENT: {
                    EventParts p = Parser.parseEvent(args);
                    Task t = tasks.add(new Event(p.desc, p.from, p.to));
                    storage.save(tasks.asList());
                    return ui.renderAdded(t, tasks.size());
                }
                case MARK: {
                    int n = Parser.parseIndex(args, true);
                    if (n < 1 || n > tasks.size()) {
                        return "Please provide a valid task number (1-" + tasks.size() + ").";
                    }
                    Task t = tasks.setDone(n - 1, true);
                    storage.save(tasks.asList());
                    return ui.renderMarked(t, true);
                }
                case UNMARK: {
                    int n = Parser.parseIndex(args, false);
                    if (n < 1 || n > tasks.size()) {
                        return "Please provide a valid task number (1-" + tasks.size() + ").";
                    }
                    Task t = tasks.setDone(n - 1, false);
                    storage.save(tasks.asList());
                    return ui.renderMarked(t, false);
                }
                case PERIOD: {
                    PeriodParts p = Parser.parsePeriod(args);
                    Task t = tasks.add(new DoWithinPeriod(p.desc, p.start, p.end));
                    storage.save(tasks.asList());
                    return ui.renderAdded(t, tasks.size());
                }
                case DELETE: {
                    int n = Parser.parseDeleteIndex(args);
                    if (n < 1 || n > tasks.size()) {
                        return "Please provide a valid task number (1-" + tasks.size() + ").";
                    }
                    Task removed = tasks.deleteAt(n - 1);
                    storage.save(tasks.asList());
                    return ui.renderDeleted(removed, tasks.size());
                }
                case FIND: {
                    String keyword = args.trim();
                    if (keyword.isEmpty()) {
                        return "Please provide a keyword to search for.";
                    }
                    List<Task> matches = tasks.find(keyword);
                    return ui.renderMatches(matches);
                }
                case BYE:
                    return "Bye. Hope to see you again soon!";
                default:
                    return "Sorry, I don't know that command.";
            }
        } catch (EveException e) {
            return e.getMessage();
        }
    }
}
