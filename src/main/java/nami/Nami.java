package nami;

import nami.ui.Ui;
import nami.storage.Storage;
import nami.task.TaskList;
import nami.task.Tasks;
import nami.parser.Parser;
import nami.command.Command;
import nami.exception.DukeException;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Entry point and coordinator for the Nami task manager.
 * Wires {@code Ui}, {@code Storage}, and {@code TaskList}, then runs the CLI loop.
 */
public class Nami {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Nami class
     * @param filePath
     * @throws IOException
     * @throws DukeException
     */

    public Nami(String filePath) throws IOException, DukeException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        ArrayList<Tasks> loaded = storage.load();  // uses your existing Storage
        this.tasks = new TaskList(loaded);
    }

    /**
<<<<<<< HEAD
     * run and initialise the application
=======
     * Starts the read–eval–print loop: reads input, parses into a command, executes it,
     * and exits when a terminating command is issued.
>>>>>>> branch-A-JavaDoc
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            try {
                Command cmd = Parser.parse(input);
                String cd = cmd.execute(tasks, ui, storage);
                System.out.println(cd);
                isExit = cmd.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Unexpected error: " + e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Program entry point. Delegates to {@link #run()} and handles fatal startup errors.
     */
    public static void main(String[] args) {
        try {
            new Nami("./data/duke.txt").run();
        } catch (IOException e) {
            System.out.println("Failed to load/save tasks: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Fatal error: " + e.getMessage());
        }
    }
}

