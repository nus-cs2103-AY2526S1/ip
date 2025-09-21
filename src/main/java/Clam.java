import command.Command;
import parser.Parser;
import storage.Storage;
import ui.Ui;
import task.TaskList;

import java.io.FileNotFoundException;

/**
 * The main class for the Clam chatbot program, which consists of the {@link TaskList} task list,,
 * {@link Storage} save file I/O and {@link Ui} user interface.
 * <p>
 * This class initialises all components upon construction, and its {@code main()} method
 * starts the program, with the filepath defined to be "./save.txt".
 * </p>
 */
public class Clam {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a {@link Clam} object with the specified filepath.
     * <p>
     * If no file is found at the specified filepath, it will create a new
     * file at the specified filepath to work with.
     * </p>
     * @param filepath the filepath for the .txt file that this {@code Clam}
     *                 object will run the program with.
     */
    public Clam(String filepath) {
        storage = new Storage(filepath);
        ui = new Ui();

        try {
            tasks = storage.loadSaveFile();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            ui.chatbotPrint("It seems there's an error with loading the save file :(");
        } catch (Exception e) {
            ui.showError(e.getMessage());
        } finally {
            ui.printLine();
        }
    }

    /**
     * Runs the program by activating the main program loop:
     * <ol>
     * <li>check if program is to be exited, if yes, exit the loop</li>
     * <li>read the next line of user input</li>
     * <li>parse the input into a command and execute it</li>
     * <li>update the exit value based on the result of the command execution</li>
     * </ol>
     * if any errors occur during the loop, they are caught and printed by
     * the {@link Ui} object, and the program continues to run (with a fresh loop).
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while(!isExit) {
            try {
                String input = ui.getInput();
                ui.printLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        Clam c = new Clam("./save.txt");
        c.run();
    }
}
