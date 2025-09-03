package bytebot;

import java.io.IOException;

import bytebot.command.Command;
import bytebot.parser.Parser;
import bytebot.storage.Storage;
import bytebot.task.TaskList;
import bytebot.ui.Ui;

/**
 * Entry point for the ByteBot application.
 * <p>
 * This class combines together the Ui and Storage, and parser/command
 * components
 */
public class ByteBot {
    private final Ui ui;
    private final Storage storage;

    /**
     * Creates a new Bytebot instance, initializing the UI and
     * storage.
     */
    public ByteBot() {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            storage.load();
        } catch (IOException e) {
            storage.initializeWithTaskList(new TaskList());
            ui.showError("Could not load tasks from file: " + e.getMessage());
        }
    }

    /**
     * Runs the main event loop: reads user input, parses it into a command,
     * Executes the command, and repeats until an exit command is issued.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(ui, storage);
                isExit = c.isExit();
            } catch (ByteException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.closeScanner();
    }

    /**
     * Application entry point.
     *
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        new ByteBot().run();
    }
}
