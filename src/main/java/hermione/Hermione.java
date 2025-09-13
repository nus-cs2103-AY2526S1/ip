package hermione;

import hermione.parsers.InputParser;
import hermione.parsers.ResponseResult;
import hermione.storage.CsvTaskStorage;
import hermione.storage.TaskStorage;
import hermione.ui.console.ConsoleUi;
import hermione.ui.javafx.Main;
import javafx.application.Application;

/**
 * Main class for the Hermione application.
 */
public class Hermione {

    private static final String NAME = "Hermione";

    private final InputParser inputParser;

    /**
     * Constructs a new Hermione instance.
     *
     * @param filePath Path to the CSV file where tasks are stored.
     */
    public Hermione(String filePath) {
        TaskStorage storage = new CsvTaskStorage(filePath);
        this.inputParser = new InputParser(storage);
    }

    /**
     * Starts the application in either console or GUI mode based on command-line arguments.
     *
     * @param args Command-line arguments. Accepts an optional '--console' flag.
     */
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Usage: java -jar hermione.jar [--console]");
        } else if (args.length == 1 && args[0].equals("--console")) {
            startConsole();
        } else {
            startGui();
        }
    }

    private static void startConsole() {
        new ConsoleUi(NAME).start();
    }

    private static void startGui() {
        Application.launch(Main.class);
    }

    /**
     * Parses the user input and returns the corresponding response.
     *
     * @param input The user input command.
     * @return The result of parsing the input.
     */
    public ResponseResult getResponse(String input) {

        return inputParser.parseInput(input);
    }
}
