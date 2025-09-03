package bytebot;

import java.io.IOException;

import bytebot.command.Command;
import bytebot.parser.Parser;
import bytebot.storage.Storage;
import bytebot.task.TaskList;
import bytebot.ui.GuiUi;
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
    private final boolean isGuiMode;

    /**
     * Creates a new Bytebot instance, initializing the UI and
     * storage.
     */
    public ByteBot() {
        this(false);
    }

    /**
     * Creates a new Bytebot instance, initializing the UI and
     * storage.
     *
     * @param isGuiMode Whether this instance is running in GUI mode
     */
    public ByteBot(boolean isGuiMode) {
        this.isGuiMode = isGuiMode;
        if (isGuiMode) {
            this.ui = new GuiUi();
        } else {
            this.ui = new Ui();
        }
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
     * Gets a response from the ByteBot.
     *
     * @param input User input
     * @return Response from ByteBot
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(ui, storage);

            if (c.isExit()) {
                return "Bye, hope to see you again soon!";
            }

            return response;

        } catch (ByteException e) {
            return ui.showError(e.getMessage());
        }
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
