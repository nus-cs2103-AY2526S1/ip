package penguin.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * The {@code Penguin} program is a chatbot that keeps track of your tasks.
 * <p>
 * The chatbot listens to user input and does/says something accordingly.
 */
public class Penguin {
    private static TaskList tasks;
    private static Ui ui;
    private static Storage storage;
    private static final String FILE_CREATION_ERROR = "Unable to create file: ";

    private TaskList loadTasks() {
        try {
            return new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            return handleMissingFile();
        }
    }

    private TaskList handleMissingFile() {
        try {
            storage.createFolder();
            storage.createFile();
            return new TaskList(new ArrayList<>());
        } catch (IOException io) {
            ui.printErrorMessage(FILE_CREATION_ERROR + io.getMessage());
            return new TaskList(new ArrayList<>());
        }
    }

    public Penguin() {
        ui = new Ui();
        storage = new Storage();
        tasks = loadTasks();
    }

    public String setUp() {
        return ui.sayWelcome();
    }

    public String getResponse(String input) {
        return Parser.parse(input, tasks, ui, storage);
    }
}
