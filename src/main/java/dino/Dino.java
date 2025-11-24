package dino;

import java.io.IOException;
import java.util.ArrayList;

/** The main class of the Dino application */
public class Dino {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";

    /**
     * Constructs a Dino application instance with the storage file.
     *
     * @param filePath path to the storage file
     */
    public Dino(String filePath) throws IOException {
        ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Constructs a Dino application instance with the default storage file.
     *
     * @throws IOException if the file cannot be accessed
     */
    public Dino() throws IOException {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Returns the UI instance used by the application.
     *
     * @return the Ui object
     */
    public Ui getUi() {
        return ui;
    }

    /** Starts the Dino application. */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            try {
                Command command = Parser.parse(input);
                command.executeCommand(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DinoException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
            new Dino(DEFAULT_FILE_PATH).run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            return Parser.parse(input).executeCommand(tasks, ui, storage);
        } catch (DinoException e) {
            return ui.getError(e.getMessage());
        }
    }

    /**
     * Checks whether the given input is an exit command.
     *
     * @param input the user's input message
     * @return true if the command is an exit command, false otherwise
     */
    public boolean isExitCommand(String input) {
        try {
            Command command = Parser.parse(input);
            return command.isExit();
        } catch (DinoException e) {
            return false;
        }
    }
}
