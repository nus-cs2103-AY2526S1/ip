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
 * Application entry point and main command loop for the John task manager.
 * <p>
 * Responsibilities:
 * - Initialize storage, UI, and in-memory task list.
 * - Load tasks from persistent storage at startup.
 * - Read user input lines, parse them into commands, execute, and display results.
 * - Gracefully handle parse errors and unexpected exceptions.
 * - Close UI resources on shutdown.
 * <p>
 * Lifecycle:
 * 1) tasks = storage.load()
 * 2) ui.showWelcome(taskCount)
 * 3) Loop:
 * - line = ui.nextCommand() (null on EOF)
 * - parse line into Command
 * - execute Command -> CommandResult
 * - ui.showMessage(result.feedback) if non-blank
 * - break if result.exit is true
 * 4) Close UI (which closes the underlying Scanner)
 * <p>
 * Notes:
 * - FileStorage.resolveBesideJar() chooses a data file path next to the running JAR
 * (or falls back to a home directory path). This avoids hardcoding src/ paths.
 * - ConsoleUi.close() will close the Scanner. If that Scanner wraps System.in,
 * System.in will be closed as well. The PrintStream (System.out) is not closed.
 * - This class uses static fields for simplicity in a single-VM CLI program.
 * For greater testability, prefer dependency injection and instance fields.
 */
public class John {
    /**
     * In-memory task list backing the session. Populated on startup via storage.load().
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
     * Runs the main REPL loop: load tasks, greet the user, process commands until exit or EOF.
     * Any ParseException thrown by command parsing is caught and shown to the user.
     * Other unexpected exceptions are also surfaced via the UI before shutdown.
     */
    public static void run() {
        tasks = storage.load();
        try (Ui ignored = ui) {
            ui.showWelcome(tasks.size());
            while (true) {
                String line = ui.nextCommand();
                if (line == null) break; // EOF
                try {
                    Command cmd = CommandParser.parse(line);
                    CommandResult res = cmd.execute(tasks, storage, ui);
                    if (!res.feedback().isBlank()) ui.showMessage(res.feedback());
                    if (res.exit()) break;
                } catch (ParseException e) {
                    ui.showMessage(e.getMessage());
                }
            }
        } catch (Exception e) {
            ui.showMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        John.run();
    }
}