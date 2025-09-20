package pickle.chat;

import java.io.IOException;

import pickle.parser.Parser;
import pickle.storage.Storage;
import pickle.task.TaskList;
import pickle.ui.Ui;



/**
 * Start of Pickle Chatbot
 * Allows users to add, remove, mark and unmark tasks to do in a list
 * Such tasks include, todos, deadlines and events.
 */
public class Pickle {

    private static final String line = "________________________________________________";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Creates a new instance of the Pickle application.
     *
     * @param filePath the file path where all tasks are saved into and loaded from.
     */
    public Pickle(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException io) {
            System.out.println("ERROR" + io.getMessage());
            this.tasks = new TaskList();
        }

    }

    /**
     * Runs the main body of the Pickle Chatbot.
     * Creates a loop that constantly accepts inputs by the user.
     *
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {

            String input = ui.readComment();
            try {
                ui.showLine();
                isExit = Parser.parse(input, tasks, ui, storage);
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Gets the response of the Pickle bot based on the inputted string the user keys in.
     * @param input The command that the users has entered.
     * @return a string that the Pickle bot will reply based on the input.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseGui(input, tasks, ui, storage);
        } catch (Exception e) {
            return ui.showErrorGui(e.getMessage());
        }
    }

    /**
     * Begins the Pickle Chatbot application.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Pickle("data/Pickle.txt").run();
    }
}
