package jackbot;

import jackbot.task.Deadline;
import jackbot.task.Event;
import jackbot.task.Task;
import jackbot.task.Todo;

import java.util.List;

/**
 * Main entry point and command loop for the Jackbot CLI todo manager.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Load and persist tasks via {@link Storage}.</li>
 *   <li>Parse user input via {@link Parser} and execute commands on {@link TaskList}.</li>
 *   <li>Interact with the user through {@link Ui}.</li>
 * </ul>
 *
 * <p>Supported commands (delegated from {@link Parser}): LIST, MARK, UNMARK, DELETE, TODO,
 * DEADLINE, EVENT, BYE.</p>
 *
 * <p>On startup, Jackbot attempts to load tasks from the provided file path. If the file exists,
 * it shows an informational message and continues with the loaded list; otherwise it starts with
 * an empty list.</p>
 *
 * @author Haxatron
 */
public class Jackbot {

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;

    /**
     * Constructs a new {@code Jackbot} bound to a storage file.
     *
     * @param filePath path to the tasks file used by {@link Storage}
     */
    public Jackbot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();

        try {
            List<Task> loaded = storage.load();
            this.tasks = new TaskList(loaded);
            if (!loaded.isEmpty()) {
                ui.showInfo("Previously saved task file detected. Loading task list from file...");
            }
        } catch (JackbotException e) {
            ui.showLoadingError();
            this.tasks = new TaskList(); // start empty if load failed
        }
    }

    /**
     * Runs the interactive REPL: reads user commands, executes them,
     * persists state when it changes, and prints results to the UI.
     * Terminates when the user issues the BYE command or when input ends.
     */
    public void run() {
        ui.showWelcome();

        boolean exit = false;
        while (!exit && ui.hasNextLine()) {
            String input = ui.readLine();

            try {
                Parser.Result r = parser.parse(input);

                switch (r.type) {
                    case BYE:
                        exit = true;
                        break;

                    case LIST:
                        ui.showList(tasks.asList());
                        break;

                    case MARK: {
                        Task t = tasks.get(r.index);
                        if (t.isDone()) {
                            throw new JackbotException("Task is already marked");
                        }
                        t.mark();
                        storage.save(tasks.asList());
                        ui.showMarked(t);
                        break;
                    }

                    case UNMARK: {
                        Task t = tasks.get(r.index);
                        if (!t.isDone()) {
                            throw new JackbotException("Task is already unmarked");
                        }
                        t.unmark();
                        storage.save(tasks.asList());
                        ui.showUnmarked(t);
                        break;
                    }

                    case DELETE: {
                        Task removed = tasks.delete(r.index);
                        storage.save(tasks.asList());
                        ui.showDeleted(removed, tasks.size());
                        break;
                    }

                    case TODO: {
                        ensureNotEmpty(r.text, "Task description cannot be empty");
                        Task t = new Todo(r.text);
                        tasks.add(t);
                        storage.save(tasks.asList());
                        ui.showAdded(t, tasks.size());
                        break;
                    }

                    case DEADLINE: {
                        ensureNotEmpty(r.text, "Task description cannot be empty");
                        Task t = new Deadline(r.text);
                        tasks.add(t);
                        storage.save(tasks.asList());
                        ui.showAdded(t, tasks.size());
                        break;
                    }

                    case EVENT: {
                        ensureNotEmpty(r.text, "Task description cannot be empty");
                        Task t = new Event(r.text);
                        tasks.add(t);
                        storage.save(tasks.asList());
                        ui.showAdded(t, tasks.size());
                        break;
                    }

                    case FIND: {
                        ensureNotEmpty(r.text, "Search keyword cannot be empty");
                        ui.showFound(tasks.find(r.text));
                        break;
                    }

                    default:
                        throw new JackbotException("Command doesn't exist");
                }

            } catch (JackbotException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Unexpected error: " + e.getMessage());
            }
        }

        ui.showGoodbye();
    }

    /**
     * Ensures a string is not null/blank.
     *
     * @param s   the string to check
     * @param msg error message for the thrown exception
     * @throws JackbotException if {@code s} is null or blank
     */
    private void ensureNotEmpty(String s, String msg) throws JackbotException {
        if (s == null || s.trim().isEmpty()) throw new JackbotException(msg);
    }

    /**
     * Program entry point.
     *
     * @param args command-line arguments (ignored). The app stores tasks under {@code ./tasks.txt}.
     */
    public static void main(String[] args) {
        new Jackbot("./tasks.txt").run();
    }
}
