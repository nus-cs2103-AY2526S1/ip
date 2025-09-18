package charsiew;

import java.io.IOException;
import java.util.Scanner;

import charsiew.command.Command;
import charsiew.parser.Parser;
import charsiew.storage.Storage;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * The main CharSiew chatbot application class.
 * Handles initialization, user interaction, and the main program loop.
 */
public class CharSiew {
    /** Handles file storage and retrieval of tasks. */
    public final Storage storage;

    /** Stores the current list of tasks. */
    private final TaskList tasks;

    /** Handles all interactions with the user. */
    private final Ui ui;

    /** Tracks the last command for UndoCommand*/
    private Command lastCommand = null;

    /**
     * Constructs a CharSiew instance with a given storage file path.
     * Loads tasks from storage or initializes an empty TaskList if loading fails.
     *
     * @param filePath Path to the file storing tasks.
     */
    public CharSiew(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loadedTasks;

        try {
            loadedTasks = storage.load();
        } catch (Exception e) {
            ui.showError("Failed to load Char Siew from file");
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    /**
     * Starts the CharSiew program and runs the main input loop.
     * Reads user commands from the console, parses them, executes them,
     * and handles any exceptions that occur.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = sc.nextLine();
                Command c = Parser.parse(fullCommand, this);
                lastCommand = c;

                assert c != null : "Parser should never return a null command";
                assert tasks != null : "Task list is not initialized";
                assert ui != null : "UI is not initialized";
                assert storage != null : "Storage is not initialized";

                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point for the CharSiew application.
     *
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new CharSiew("data/tasks.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws IOException {
        storage.save(tasks);

        try {
            Command c = new Parser().parse(input, this);
            lastCommand = c;
            
            assert c != null : "Parser returned null for input: " + input;
            assert tasks != null : "Task list is not initialized";
            assert ui != null : "UI is not initialized";
            assert storage != null : "Storage is not initialized";
            
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the last command.
     * @return Last command stored.
     */
    public Command getLastCommand() {
        return lastCommand;
    }
}
