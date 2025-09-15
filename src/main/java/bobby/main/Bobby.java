package bobby.main;

import bobby.exception.BobbyException;
import bobby.parser.Parser;
import bobby.storage.Storage;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Chatbot Class
 */
public class Bobby {
    private Parser parser;
    Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Bobby() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (BobbyException e) {
            ui.showMessage(e.getMessage());
        }
        parser = new Parser(taskList);
    }

    public void save() {
        try {
            storage.save(taskList.saveTasks());
        } catch (BobbyException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            return parser.processCommand(input);
        } catch (BobbyException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Bobby().run();
    }

    public void run() {
        ui.run(parser);
        this.save();
    }
}
