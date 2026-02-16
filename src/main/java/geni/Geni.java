package geni;

import java.util.Scanner;

import geni.exception.GeniException;
import geni.parser.Parser;
import geni.storage.Storage;
import geni.task.TaskList;
import geni.ui.UI;

// Solution below inspired by https://github.com/david-eom/CS2103T-IP/tree/master
/**
 * Entry point of the Geni application.
 * Initializes components and runs the main loop.
 */
public class Geni {
    private UI ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Creates a {@code Geni} instance.
     *
     * @param filePath path to the data file
     */
    public Geni(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser(ui, storage, tasks);
    }
    /**
     * Starts the main execution loop of the Geni application.
     * <p>
     * Continuously reads user input, parses it, and executes commands until
     * the user chooses to exit. Handles errors gracefully by printing
     * appropriate error messages.
     * </p>
     */
    public void run() {
        System.out.print(ui.getGreeting());
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String inp = sc.nextLine();
                String response = parser.parseAndExecute(inp);
                System.out.print(response);
            } catch (GeniException e) {
                if (e.getMessage().equals("exit")) {
                    System.out.println(ui.getExit());
                    break;
                }
                ui.line();
                System.out.println("OOPS! " + e.getMessage());
                ui.line();
            }
        }

    }

    /**
     * Returns the greeting message of the assistant.
     *
     * @return greeting message as a string
     */
    public String getGreetingMessage() {

        return ui.getGreeting();
    }

    /**
     * Processes a single user input and returns the assistant's response.
     *
     * @param input user command string
     * @return response from the assistant
     */
    public String getResponse(String input) {
        try {

            return parser.parseAndExecute(input);
        } catch (GeniException e) {
            if (e.getMessage().equals("exit")) {
                return ui.getExit();
            }
            return "OOPS! " + e.getMessage();
        }
    }

}
