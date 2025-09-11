package john.core;

import john.adapters.ConsoleUi;
import john.core.command.Command;
import john.core.command.CommandResult;
import john.core.exception.ParseException;
import john.core.parser.CommandParser;
import john.model.TaskList;

import john.adapters.FileStorage;
import john.ports.Ui;

import java.util.Scanner;

/**
 * Core application class for the John task manager.
 * <p>
 * Roles
 * - CLI entry point and command loop (run, main).
 * - Backend service for the JavaFX UI (getReply).
 * - Orchestrates parsing, command execution, persistence, and UI messaging.
 * <p>
 * Storage
 * - Uses FileStorage.resolveBesideJar() to choose a writable data path near the
 * running JAR; falls back to a home-directory path if needed.
 * <p>
 * UI
 * - In CLI mode, ConsoleUi reads from System.in and writes to System.out.
 * ConsoleUi.close() will close the Scanner, which also closes System.in.
 * <p>
 * Design notes
 * - Uses static fields for a simple single-VM CLI. For richer testing and multiple
 * instances, prefer dependency injection and instance fields.
 * - Commands should return messages via CommandResult rather than printing directly.
 */
public class John {
    /**
     * In-memory task list for the current session. Populated on startup via storage.load().
     */
    private static TaskList tasks;

    /**
     * Persistent storage for tasks. Defaults to saving next to the running JAR.
     */
    private static final FileStorage storage =
            new FileStorage(FileStorage.resolveBesideJar().toString());

    /**
     * Console-based UI that reads from System.in and writes to System.out.
     */
    private static final ConsoleUi ui = new ConsoleUi(new Scanner(System.in), System.out);

    /**
     * Runs the CLI loop: load tasks, greet the user, then repeatedly read, parse, and
     * execute commands until an exit command or EOF is encountered. Parse errors are
     * shown to the user; unexpected exceptions are surfaced before shutdown.
     */
    public static void run() {
        tasks = storage.load();
        try (Ui ignored = ui) {
            ui.showWelcome(tasks.size());
            while (true) {
                String line = ui.nextCommand();
                if (line == null) {
                    break; // EOF
                }
                try {
                    Command cmd = CommandParser.parse(line);
                    CommandResult res = cmd.execute(tasks, storage, ui);
                    if (!res.feedback().isBlank()) {
                        ui.showMessage(res.feedback());
                    }
                    if (res.exit()) {
                        break;
                    }
                } catch (ParseException e) {
                    ui.showMessage(e.getMessage());
                }
            }
        } catch (Exception e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Standard Java entry point for CLI mode. Delegates to run().
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        John.run();
    }

    /**
     * Constructs a backend instance for GUI mode and loads tasks immediately.
     * Note that this does not start the CLI loop; the JavaFX controller will call
     * getReply(String) to process individual user inputs.
     */
    public John() {
        tasks = storage.load();
        assert tasks != null : "storage.load() must not return null";
    }

    /**
     * Immutable reply returned to the JavaFX controller.
     *
     * @param message user-facing feedback text
     * @param exit    true if the application should exit after showing the message
     */
    public record Reply(String message, boolean exit) {
    }

    /**
     * Processes a single user input and returns a reply for the GUI.
     * The reply contains the user-facing message and a boolean that signals
     * whether the application should exit. Commands may mutate the task list
     * and persist changes via storage as needed.
     *
     * @param input raw user input text
     * @return a Reply containing the message and an exit flag
     */
    public Reply getReply(String input) {
        assert input != null : "controller should not pass null input";
        try {
            Command cmd = CommandParser.parse(input);
            CommandResult res = cmd.execute(tasks, storage, ui);
            return new Reply(res.feedback(), res.exit());
        } catch (ParseException e) {
            return new Reply(e.getMessage(), false);
        }
    }
}