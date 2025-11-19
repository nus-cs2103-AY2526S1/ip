package mochibot;

import java.io.FileNotFoundException;

import mochibot.command.Command;
import mochibot.parser.Parser;
import mochibot.storage.Storage;
import mochibot.task.TaskList;
import mochibot.ui.Gui;

/**
 * This class represents the entry point of the MochiBot chatbot application.
 * <p>
 *     The {@code MochiBot} class initializes the GUI and loads any existing tasks into the tasklist.
 *     It also handles all user interactions with the application.
 * </p>
 */
public class MochiBot {
    private TaskList tasks;
    private final Gui gui;

    /**
     * Creates a new MochiBot instance and loads existing tasks into the tasklist.
     */
    public MochiBot() {
        gui = new Gui();
        try {
            tasks = Storage.loadTaskList();
        } catch (FileNotFoundException e) {
            System.out.println("Scanner cannot find file to read.");
            tasks = new TaskList();
        }
    }

    public String displayGreeting() {
        return gui.displayGreeting();
    }

    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, gui);
        } catch (MochiBotException e) {
            response = gui.displayErrorMessage(e.getMessage());
        }
        return response;
    }
}
