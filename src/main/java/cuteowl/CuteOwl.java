package cuteowl;

import cuteowl.command.Command;
import cuteowl.exception.CuteOwlException;
import cuteowl.note.NoteList;
import cuteowl.parser.Parser;
import cuteowl.storage.Storage;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * The main class for CuteOwl chatbot application.
 * This class initializes core components and handles main execution loop.
 */

public class CuteOwl {
    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;
    private final NoteList notes;
    private String commandType;

    /**
     * Constructs a CuteOwl instance with the specified file path for persistent storage.
     * Initializes UI, storage and tasks from file.
     *
     * @param filePath Path to the file used for loading and saving tasks.
     */
    public CuteOwl(String filePath) {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        notes = new NoteList();
    }

    /**
     * Starts the main loop of the chatbot.
     * Continuously reads user commands, parses and execute them until an exit command is received.
     */

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage, notes);
                isExit = c.isExit();
            } catch (CuteOwlException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point of the application.
     * Creates a CuteOwl instance and starts the chatbot.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new CuteOwl("data/tasks.txt").run();
    }

    /**
     * Generates a response for the user's chat message
     */
    /*public String getResponse(String input) {
        return "CuteOwl heard: " + input;
    }*/

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage, notes);
            commandType = c.getClass().getSimpleName();
            return c.getString();
        } catch (CuteOwlException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getCommandType() {
        if (commandType == null) {
            return " ";
        }

        return commandType;
    }

}