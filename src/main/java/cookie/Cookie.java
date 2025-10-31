package cookie;

import cookie.exception.CookieException;
import cookie.parser.Parser;
import cookie.storage.Storage;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Main class for the Cookie task management chatbot.
 * Handles interaction between the UI, Storage, Parser and Tasks.
 */
public class Cookie {

    private Storage storage;
    private TaskList listOfTasks;
    private Ui ui;

    /**
     * Constructs new instance of Cookie application.
     * Initializes Ui and Storage and loads tasks from saved file.
     *
     * @param filePath Path of file to load tasks from.
     */
    public Cookie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            listOfTasks = new TaskList(storage.load());
        } catch (CookieException e) {
            ui.showLoadingErrorGui();
            listOfTasks = new TaskList();
        }
    }

    /**
     * Processes user input and returns the relevant output for the GUI.
     *
     * @param input User input string.
     * @return Response to be displayed in GUI.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseForGui(listOfTasks, ui, storage, input);
        } catch (CookieException e) {
            return ui.showErrorGui(e.getMessage());
        }
    }
}
