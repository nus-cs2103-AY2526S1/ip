package chani;
import java.util.ArrayList;
import java.util.List;

import chani.commands.Command;
import chani.tasks.Task;


/**
 * Represents the main chatbot class
 */
public class Chani {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String initMessage;
    /**
     * Class Constructor that initialises storage to a file, else may initialise temporary storage
     * @param fPath txt file path for storage.
     */
    public Chani(String fPath) {
        this.ui = new Ui("Chani");
        try {
            this.storage = new Storage(fPath);
            List<Task> loadedTasks = storage.load();
            tasks = new TaskList(loadedTasks);
            this.initMessage = "127-iq initiated successfully.";
        } catch (StorageException e) {
            this.initMessage = e.getMessage()
                    + " Starting fresh, your history will not be saved.";
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Returns gui message for Chani bot.
     * @param input text to return.
     * @return Chani bot's message.
     */
    public String getResponse(String input) {
        String chatLabel = "Chani: ";
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
        } catch (ChaniException | NumberFormatException | IndexOutOfBoundsException e) {
            response = e.getMessage();
        }
        return chatLabel + response;
    }

    /**
     * Returns gui greeting for Chani bot.
     * @return greeting message.
     */
    public String getGreeting() {
        return ui.showWelcome();
    }

    public String getInitMessage() {
        return initMessage;
    }
}
