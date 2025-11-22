package apunable.ui;

import exceptions.ApunableException;
import javafx.application.Platform;
import models.ContactBook;
import models.TaskList;
import utils.Command;
import utils.Parser;
import utils.Storage;
import utils.Ui;

/**
 * A Chatbot that only talks about models with users.
 */
public class ApunableBot {

    private Storage storage;
    private Storage contactStorage;
    private TaskList tasks;
    private ContactBook contacts;
    private Ui ui;

    /**
     * Initializes a new Chatbot instance and load tasklist from {@code filePath}. 
     * 
     * @param filePath path to file that store tasklist. 
     */
    public ApunableBot(String filePath, String contactPath) {
        ui = new Ui();
        storage = new Storage(filePath);
        contactStorage = new Storage(contactPath);
        try {
            tasks = new TaskList(storage.load());
            contacts = new ContactBook(contactStorage.load());
        } catch (ApunableException e) {
            ui.showLoadingError();
            tasks = new TaskList();
            contacts = new ContactBook();
        }
    }

    public ApunableBot() {
        this("data/tasks.txt", "data/contacts.txt");
    }

    /**
     * Starts running the chatbot to interact with users(accept inputs, process and produce output). 
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, contacts, ui, storage, contactStorage);
                isExit = c.isExit();
            } catch (ApunableException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new ApunableBot("data/tasks.txt", "data/contacts.txt").run();
    }

    /**
     * Returns welcome message. 
     */
    public String getWelcomeMessage() {
        ui.showWelcome();
        return ui.getOutput();
    }
    
    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, contacts, ui, storage, contactStorage);
            if (c.isExit()) {
                Platform.runLater(Platform::exit);
            }

            return ui.getOutput();
        } catch (ApunableException | AssertionError e) {
            ui.showError(e.getMessage());
            return ui.getOutput();
        }
    }
}
