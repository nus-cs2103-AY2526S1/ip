package nina;

import java.util.Scanner;

import nina.command.Command;
import nina.task.TaskList;

public class Nina {
    static String LINE = "___________________________________\n";
    private static final String STORAGE_FILE_PATH = "data/nina.Nina.txt";
    private TaskList tasks;
    private Storage storage;
    private UI ui;


    /**
     * Creates a Nina chatbot object.
     * Initializes storage, read tasks from file, and sets up the UI.
     */
    public Nina() {
        this.storage = new Storage(STORAGE_FILE_PATH);
        this.tasks = storage.read();
        this.ui = new UI();
    }

    /**
     * Runs the main program loop.
     * Displays a greeting, continuously reads user input,
     * parses it into a Command object, executes the command,
     * and saves the updated tasks. The loop terminates when the user
     * enters the bye command.
     */
    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                ui.exit();
                break;
            }

            try {
                Command cmd = Parser.parse(input);
                System.out.println(LINE);
                cmd.execute(tasks);
                storage.write(tasks);
                System.out.println(LINE);
            } catch (CommandException e) {
                ui.showError("nina.command.Command error: " + e.getMessage());
            } catch (InvalidInputException i) {
                ui.showError("Input error: " + i.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        String trimmedInput = input.trim();

        if (trimmedInput.equals("greet")) {
            return ui.greet();
        }

        if (trimmedInput.equals("bye")) {
            return ui.exit();
        }

        try {
            Command cmd = Parser.parse(input);
            String response = cmd.execute(tasks);
            storage.write(tasks);
            return response;
        } catch (CommandException c) {
            return ui.showError(c.getMessage());
        } catch (InvalidInputException i) {
            return ui.showError(i.getMessage());
        }
    }
    /**
     * Main entry point of the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        Nina test = new Nina();
        test.run();
    }
}
