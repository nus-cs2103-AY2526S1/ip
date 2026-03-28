package luna;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import luna.ui.Ui;

/**
 * The main class for luna application.
 */
public class Luna {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a new instance of the Luna application.
     * Attempts to load tasks from the specified file path. If loading fails,
     * initializes an empty task list.
     *
     * @param filePath File path of the task storage file.
     */

    public Luna(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load(), storage);
        } catch (IOException e) {
            System.out.println(" Error loading tasks: " + e.getMessage());
            taskList = new TaskList(new ArrayList<>(), storage);
        }

    }

    public Luna() {};

    /**
     * Entry point of the Luna application.
     */
    public static void main(String[] args) {
        new Luna("data/luna.txt").run();
    }

    /**
     * Runs the Luna application.
     * Displays a greeting, continuously processes user input,
     * and executes commands until an exit command is encountered.
     */
    public void run() {
        ui.greeting();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String input = sc.nextLine();
            try {
                Command c = Parser.parse(input);
                c.execute(taskList, ui);
                if (c instanceof ExitCommand) {
                    isExit = true;
                }
            } catch (DateTimeParseException | LunaException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);

            if (command instanceof ExitCommand) {
                return command.execute(taskList, ui);
            }

            return command.execute(taskList, ui);

        } catch (DateTimeParseException | LunaException e) {
            return e.getMessage();
        }

    }


}

