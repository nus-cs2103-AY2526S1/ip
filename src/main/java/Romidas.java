import java.io.File;
import java.util.ArrayList;

import application.Parser;
import application.Storage;
import application.TaskList;
import application.Ui;
import command.Command;
import exception.RomidasException;
import tasks.Task;

/**
 * Main application class for the Romidas task management system.
 * Handles the application lifecycle, user input processing, and coordination between components.
 */
public class Romidas {
    /** Path to the data file for persistent storage */
    private static final String DATA_PATH = getProjectRootPath() + File.separator + "romidas.txt";
    
    /**
     * Determines the project root path for data file storage.
     * Handles different execution contexts (project root vs text-ui-test subdirectory).
     *
     * @return The absolute path to the project root directory.
     */
    private static String getProjectRootPath() {
        String currentDir = System.getProperty("user.dir");
        
        if (currentDir.endsWith("text-ui-test")) {
            return new File(currentDir).getParent();
        }
        
        return currentDir;
    }
    /** Storage component for file I/O operations */
    private Storage storage;
    /** Task list component for managing tasks */
    private TaskList taskList;
    /** User interface component for input/output */
    private Ui ui;

    /**
     * Constructs a new Romidas application instance.
     * Initializes all components and loads existing tasks from storage.
     */
    public Romidas() {
        this.ui = new Ui();
        this.storage = new Storage();
        ArrayList<Task> store = storage.loadTasks(DATA_PATH);
        this.taskList = new TaskList(store);
    }

    /**
     * Runs the main application loop.
     * Handles user input, command processing, and application lifecycle.
     * Continues until the user issues an exit command or encounters an error.
     */
    public void run() {
        ui.welcome();
        boolean isBye = false;
        while (!isBye) {
            try {
                String input = ui.readCommand();
                if (input.equalsIgnoreCase("bye")) {
                    isBye = true;
                    ui.showLine();
                    break;
                }
                ui.showLine();
                Command c = Parser.parse(input, taskList, ui, storage, DATA_PATH);
                c.execute(taskList, ui, storage);
                isBye = c.isBye();
            } catch (NumberFormatException e) {
                ui.showError("tasks.Task number must be an integer.");
            } catch (IllegalArgumentException e) {
                ui.showError("I'm sorry, I don't recognise that command. "
                        + "Try one of list, event, todo, deadline, mark, unmark, delete");
            } catch (RomidasException e) {
                ui.showError(e.getMessage());
            } finally {
                if (!isBye) {
                    ui.showLine();
                }
            }
        }
        ui.showGoodbye();
    }

    /**
     * Returns input in the GUI
     * @param input
     * @return String response to be shown
     */
    public String getResponse(String input) {
        try {
            GuiUi guiUi = new GuiUi();
            
            if (input.trim().equalsIgnoreCase("bye")) {
                return "Bye. Hope to see you again soon!";
            }
            
            Command c = Parser.parse(input, taskList, guiUi, storage, DATA_PATH);
            
            c.execute(taskList, guiUi, storage);
            
            return guiUi.getOutput();
            
        } catch (NumberFormatException e) {
            return "Task number must be an integer.";
        } catch (IllegalArgumentException e) {
            return "I'm sorry, I don't recognise that command. Try one of list, event, todo, deadline, mark, unmark, delete, find";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Main entry point for the Romidas application.
     * Creates and runs the application instance.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Romidas romidas = new Romidas();
        romidas.run();
    }
}