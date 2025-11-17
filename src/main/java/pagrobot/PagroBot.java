package pagrobot;

import java.util.Scanner;

import pagrobot.parser.Parser;
import pagrobot.storage.Storage;
import pagrobot.tasklist.TaskList;
import pagrobot.ui.Ui;

/**
 * Class for the application. pagroBot is a task management chatbot that
 * stores tasks, retrieves them from memory, and allows interaction via
 * commands.
 */
public class PagroBot {
    /** File path where tasks are stored. */
    public static final String FILE_PATH = "./data/botTask.txt";

    private static Ui ui = new Ui();
    private Storage storage = new Storage(PagroBot.FILE_PATH);
    private TaskList taskList = storage.getMemory();
    private Parser parser = new Parser(taskList, ui);

    /**
     * Runs the program by initializing storage, UI, and parser.
     * Continuously reads user input until the exit command is given.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        TaskList taskList;

        try (Scanner scanner = new Scanner(System.in)) {
            Storage storage = new Storage(PagroBot.FILE_PATH);
            ui.greet();
            taskList = storage.getMemory();
            Parser parser = new Parser(taskList, ui);

            while (true) {
                String input = scanner.nextLine();
                String result;
                try {
                    result = parser.handleMessage(input);
                } catch (Exception e) {
                    continue;
                }
                storage.toMemory(taskList);
                if (result == null) {
                    break;
                }
            }
        }
    }

    public static Ui getUi() {
        return ui;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String response = parser.handleMessage(input);
            return response;
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            storage.toMemory(taskList);
        }
    }

    /**
     * Generates a greeting.
     * @return Bot's greeting.
     */
    public String greet() {
        return this.ui.greet();
    }
}
