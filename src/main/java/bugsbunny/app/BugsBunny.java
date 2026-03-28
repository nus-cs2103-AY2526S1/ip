package bugsbunny.app;

import java.io.IOException;

import bugsbunny.commands.Command;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.parsers.Parser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.TaskList;

/**
 * Application entry point for the BugsBunny chatbot.
 */
public class BugsBunny {
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates the app and attempts to load tasks from the given file path.
     */
    public BugsBunny() {
        storage = new Storage(BugsBunny.DEFAULT_FILE_PATH);
        ui = new Ui();

        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (BugsBunnyException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Runs the chatbot: reads commands, executes them, and exits when requested.
     */
    public void run() {
        ui.showWelcome();
        System.out.println();
        System.out.println(ui.showCommandGuide());
        ui.showLine();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                String output = c.execute(tasks, ui, storage);
                System.out.println(output);
                isExit = c.isExit();
            } catch (BugsBunnyException e) {
                System.out.println(ui.showError(e.getMessage()));
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new BugsBunny().run();
    }
}
