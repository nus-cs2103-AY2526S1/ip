package reboot;

import java.io.IOException;

import reboot.command.Command;
import reboot.gui.Gui;
import reboot.parser.Parser;

/**
 * Represents a chatbot that will be able to track tasks.
 */
public class Reboot {

    private final Gui gui;
    private TaskList tasks;
    private final Storage storage;
    private final String welcome;

    /**
     * Constructs a new Reboot chatbot when given a specified file path.
     * @param filePath Location of file to store data of tasklist.
     */
    public Reboot(String filePath) {
        gui = new Gui();
        storage = new Storage(filePath);
        welcome = gui.showWelcome();

        try {
            tasks = new TaskList(storage.load());
            tasks.updateRecurringTasks();
        } catch (IOException e) {
            gui.showError("Error reading file. Will start with a new tasklist");
            tasks = new TaskList();
            storage.writeFile(tasks.getAll());
        }
    }

    /**
     * Returns the welcome message.
     */
    public String getWelcome() {
        return this.welcome;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(tasks, gui, storage);
    }
}
