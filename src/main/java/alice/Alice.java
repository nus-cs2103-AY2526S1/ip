package alice;

import alice.command.Command;
import alice.exceptions.AliceException;

import java.util.Scanner;
import java.nio.file.Path;

public class Alice {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean shouldExit = false;

    public Alice(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public boolean shouldExit() {
        return shouldExit;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);

            if (c.isExit()) {
                shouldExit = true;
            }

            return response;
        } catch (AliceException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs the Alice program
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);// Initialise scanner

        // initial greeting from AliceBot
        ui.greet();

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = scanner.nextLine();
                Command c = Parser.parse(fullCommand);
                String output = c.execute(tasks, ui, storage);
                ui.showMessage(output);
                isExit = c.isExit();
            } catch (AliceException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String filePath = Path.of("data", "alice.txt").toString();
        new Alice(filePath).run();
    }
}
