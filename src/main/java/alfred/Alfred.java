package alfred;

import java.io.IOException;
import java.util.Scanner;

import command.Command;
import exceptions.AlfredException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Alfred {
    private TaskList tasks;
    private Storage storage;

    public Alfred(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = storage.load();
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("data/alfred.txt");
        TaskList tasks = storage.load(); // load tasks at startup

        ui.showWelcome();

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                if (!sc.hasNextLine()) {
                    break;        // EOF ends program cleanly
                }
                String line = sc.nextLine();

                try {
                    Command cmd = Parser.parse(line); // parse user input → Command
                    cmd.execute(tasks, ui);           // perform action (no Storage here)

                    if (cmd.isExit()) {
                        // Save once, right before exiting
                        try {
                            storage.save(tasks);
                        } catch (IOException e) {
                            ui.showError("Failed to save tasks: " + e.getMessage());
                        }
                        ui.showBye();
                        break;
                    }
                } catch (AlfredException de) {
                    ui.showError(de.getMessage());    // friendly domain errors
                } catch (Exception e) {
                    ui.showError("Unexpected error: " + e.getMessage());
                }
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            return cmd.execute(tasks, new Ui());
        } catch (AlfredException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Unexpected error: " + e.getMessage();
        }
    }

    /**
     * Saves the current tasks to storage.
     * @throws IOException if there's an error saving to the file
     */
    public void saveToStorage() throws IOException {
        storage.save(tasks);
    }
}
