package meeseeks;

import meeseeks.command.Command;
import meeseeks.parser.Parser;
import meeseeks.storage.Storage;
import meeseeks.task.TaskList;
import meeseeks.ui.Ui;

/**
 * Main application class for the Meeseeks task manager.
 * Handles the core logic and communication between UI and storage.
 */
public class Meeseeks {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Meeseeks(String filePath) {
        assert filePath != null : "File path cannot be null";
        assert !filePath.trim().isEmpty() : "File path cannot be empty";
        
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Could not load tasks.");
            tasks = new TaskList();
        }
    }

    /**
     * Creates a Meeseeks instance with the default file path.
     */
    public Meeseeks() {
        this("data/meeseeks.txt");
    }

    public String getResponse(String input) {
        assert input != null : "Input cannot be null";
        
        try {
            Command c = Parser.parse(input);
            String response = c.executeAndGetResponse(tasks, storage);
            storage.save(tasks);
            return response;
        } catch (Exception e) {
            return "Oh Geez! " + e.getMessage();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                storage.save(tasks);
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        // Text UI is deprecated - use GUI instead
        // new Meeseeks("data/meeseeks.txt").run();
        System.out.println("Please use the GUI version by running the Launcher class.");
    }
}
