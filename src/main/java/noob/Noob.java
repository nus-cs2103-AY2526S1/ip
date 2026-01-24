package noob;

import java.util.Scanner;

import noob.command.Command;
import noob.exception.NoobException;
import noob.parser.Parser;
import noob.storage.Storage;
import noob.task.TaskList;
import noob.ui.Ui;

/**
 * Main chatbot class
 */
public class Noob {
    private static final String DEFAULT_FILE_PATH = "data/tasks-file.txt";

    private Storage storage;
    private Ui ui = new Ui();
    private TaskList tasks;
    private Scanner scanner;

    public Noob(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage);
        } catch (NoobException e) {
            this.ui.displayInitializationError(e);
            this.tasks = new TaskList();
        }
    }

    public Noob() {
        try {
            this.storage = new Storage(DEFAULT_FILE_PATH);
            this.tasks = new TaskList(this.storage);
        } catch (NoobException e) {
            this.ui.displayInitializationError(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Initialises a new scanner and starts the bot
     */
    public void start() {
        this.scanner = new Scanner(System.in);
        this.ui.greet();
        boolean isExit = false;

        // Chat loop
        while (!isExit) {
            String input = scanner.nextLine();

            try {
                Command command = Parser.parse(input);
                command.execute(this.storage, this.tasks, this.ui);
                isExit = command.isExit();
            } catch (NoobException e) {
                this.ui.displayError(e);
            }
        }
        this.exit();
    }

    /**
     * Gets the display message response based on user input in GUI
     *
     * @param userInput User's text field input
     * @return Message to be displayed to user
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            return command.execute(this.storage, this.tasks, this.ui);
        } catch (NoobException e) {
            return this.ui.displayError(e);
        }
    }

    /**
     * Greets the user upon GUI launch
     *
     * @return Greeting message
     */
    public String greetGui() {
        return this.ui.greet();
    }

    /**
     * Exits the GUI and displays message
     *
     * @return Exit message
     */
    public String exitGui() {
        return this.ui.exit();
    }

    /**
     * Exits conversation and closes scanner
     */
    private void exit() {
        this.ui.exit();
        this.scanner.close();
    }

    public static void main(String[] args) {
        Noob bot = new Noob("data/tasks-file.txt");
        bot.start();
    }
}
