package sheares;

import java.time.format.DateTimeParseException;

import sheares.command.Command;
import sheares.exception.DukeException;
import sheares.exception.WrongInputException;

/**
 * Entry point of the chatbot
 */
public class Sheares {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isFirstTime;

    /**
     * Constructor for new chatbot instance with filePath to existing data
     * @param filePath
     */
    public Sheares(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    /**
     * Runs the cli version of the chatbot
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand().trim();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showError("    Pls key in valid deadline format yyyy-MM-dd");
            } finally {
                ui.showLine();
            }
        }
        assert isExit;
    }

    /**
     * Provides the string form of chatbot output for GUI version
     * @param input
     * @return
     */
    public String getResponse(String input) {
        StringBuilder init = new StringBuilder();
        try {
            if (input.isEmpty()) {
                throw new WrongInputException();
            }
            Command c = Parser.parse(input);
            init.append(c.executeWithString(tasks, ui, storage));
        } catch (DukeException e) {
            init.append(e.getMessage()).append("\n");
        } catch (DateTimeParseException e) {
            init.append("    Pls key in valid deadline format yyyy-MM-dd" + "\n");
        } finally {
            init.append("\n");
        }
        return init.toString();
    }

    public static void main(String[] args) {
        new Sheares("./data/duke.txt").run();
    }
}

