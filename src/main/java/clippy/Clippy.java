package clippy;

import java.util.List;

import clippy.command.Command;
import clippy.parser.Parser;
import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * The main class for the Clippy application, a command-line task manager.
 * It initializes the UI, storage, and task list, and runs the main command loop.
 */
public class Clippy {
    private static final String LS = System.lineSeparator();
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private String commandType;

    /**
     * Constructs a Clippy application instance, initializing the UI, storage, and task list.
     */
    public Clippy() {
        this.ui = new Ui();
        this.storage = new Storage();
        List<Task> loaded = storage.load();
        this.tasks = new TaskList(loaded);
        assert this.tasks != null : "TaskList should be initialized";
    }

    /**
     * Starts the Clippy application, displaying the welcome message and entering the command loop.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                assert command != null : "Parsed Command should not be null";
                executeCommand(command, ui);
                isExit = command.isExit();
            } catch (ClippyException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Unexpected error: " + e.getMessage());
            }
        }
    }

    public String getWelcome() {
        StringBuilder sb = new StringBuilder();
        Ui sinkUi = new Ui(s -> sb.append(s).append(LS));
        sinkUi.welcome();
        return sb.toString().trim();
    }

    public String getResponse(String input) {
        StringBuilder sb = new StringBuilder();
        Ui sinkUi = new Ui(s -> sb.append(s).append(LS));
        try {
            Command command = Parser.parse(input);
            assert command != null : "Parsed Command should not be null";
            executeCommand(command, sinkUi);
        } catch (ClippyException e) {
            sinkUi.showError(e.getMessage());
        } catch (Exception e) {
            sinkUi.showError("Unexpected error: " + e.getMessage());
        }
        return sb.toString().trim();
    }

    /**
     * Returns the type of the last executed command.
     *
     * @return The command type as a string.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Executes the given command using the provided UI, updating the command type.
     *
     * @param command  The command to execute.
     * @param targetUi The UI to use for displaying messages.
     * @throws ClippyException If an error occurs during command execution.
     */
    private void executeCommand(Command command, Ui targetUi) throws ClippyException {
        command.execute(tasks, targetUi, storage);
        this.commandType = command.getClass().getSimpleName();
    }

    public static void main(String[] args) {
        new Clippy().run();
    }
}
