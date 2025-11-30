package dyke.ui;

import java.util.Scanner;

import dyke.data.Library;
import dyke.parse.Parser;
import dyke.parse.Storage;


/**
 * Contains Main method to run DYKE
 */
public class Dyke {
    private static Library library = new Library();
    // file location for saved tasks
    private static final String FILE_PATH = "./data/dyke.txt";
    private Storage storage;
    private Parser parser;
    private Ui ui;
    private boolean isRunning;

    /**
     * Creates the Dyke program
     */
    public Dyke() {
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        this.parser = new Parser(library, ui, storage);
        this.isRunning = true;
    }

    private String run(String input) {
        String[] commands = input.split(" ", 2);
        String msg = parser.parseCommand(commands);
        this.isRunning = parser.isRunning();
        return msg;
    }

    /**
     * Passes a welcome message for GUI to show
     * @return String message
     */
    public String welcome() {
        return ui.welcomeMessage();
    }

    /**
     * Initialises Dyke by loading in any saved tasks.
     * @return String message for loading status
     */
    public String init() {
        return storage.loadTasks(library);
    }

    /**
     * Asserts if exit command has not been given.
     * @return boolean
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Runs Dyke in CLI
     * @param args User inputs
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui(); // User Interface class

        // Print Welcome Message
        ui.welcomeMessage();

        // flag for exiting while loop on "Bye!" dyke.command
        boolean isRunning = true;

        // Loadin existing Tasks into dyke.data.Library
        Storage storage = new Storage(FILE_PATH);
        ui.printMessageNoBar(storage.loadTasks(library));

        // Create Parse Engine
        Parser parser = new Parser(library, ui, storage);

        while (sc.hasNextLine() && isRunning) {
            String[] input = sc.nextLine().split(" ", 2);
            parser.parseCommand(input);
            if (!parser.isRunning()) {
                break;
            }

        }

        // Save current tasks into dyke.ui.Dyke.txt
        if (!library.isEmpty()) {
            ui.printMessageNoBar(storage.saveTasks(library));
        }
        sc.close();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return this.run(input);
    }
}
