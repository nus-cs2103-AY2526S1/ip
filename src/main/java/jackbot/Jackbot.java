package jackbot;

import java.util.List;

import jackbot.task.Task;

/**
 * Main entry point and command loop for the Jackbot CLI todo manager.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Load and persist tasks via {@link Storage}.</li>
 *   <li>Delegate command parsing/execution to {@link CommandEngine}
 *       (which wraps {@link Parser} and {@link TaskList}).</li>
 *   <li>Interact with the user through {@link Ui}.</li>
 * </ul>
 *
 * <p>Supported commands (handled by {@link CommandEngine}): LIST, MARK, UNMARK, DELETE, TODO,
 * DEADLINE, EVENT, FIND, BYE.</p>
 *
 * <p>On startup, Jackbot attempts to load tasks from the provided file path. If the file exists,
 * it shows an informational message and continues with the loaded list; otherwise it starts with
 * an empty list.</p>
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
     * <p>
     * Implementation note: command logic is delegated to {@link CommandEngine}
     * so both CLI and JavaFX UI share identical behavior and messages.
     * Terminates when the user issues the BYE command or when input ends.
     */
    public void run() {
        ui.showWelcome();

        CommandEngine engine = new CommandEngine(storage, tasks, parser);
        boolean exit = false;

        while (!exit && ui.hasNextLine()) {
            String input = ui.readLine();

            CommandEngine.Response resp = engine.process(input);

            // Render each message using the existing Ui framing
            for (String msg : resp.messages) {
                ui.showInfo(msg);
            }

            exit = resp.exit;
        }

        ui.showGoodbye();
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
