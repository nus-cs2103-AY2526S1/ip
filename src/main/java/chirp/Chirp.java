package chirp;

import java.io.IOException;

import chirp.actions.Action;
import chirp.exceptions.ChirpException;
import chirp.io.MainWindow;
import chirp.io.Parser;
import chirp.io.Ui;
import chirp.tasks.TaskList;

/**
 * Represents main object of Chirp chatbot
 */
public class Chirp {
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";
    private TaskList taskList;
    private Ui ui;
    private FileManager fileManager;
    private boolean isRunning;
    private boolean hasStartUp;

    /**
     * Initialises chatbot before being run
     *
     * @param window Ui display window
     */
    public Chirp(MainWindow window) {
        ui = new Ui(window);
        isRunning = true;
        hasStartUp = false;
        ui.queryFilePath(DEFAULT_FILE_PATH);
    }

    /**
     * Initialises bot with input of data filepath
     * @param filePath Expect either filepath or empty string
     */
    private void startUp(String filePath) throws IOException {
        if (filePath.isEmpty()) {
            filePath = DEFAULT_FILE_PATH;
        }
        try {
            fileManager = new FileManager(filePath);
            taskList = fileManager.loadTasks();
        } catch (ChirpException e) {
            // Initialisation error
            ui.loadingError(e.getMessage());
            taskList = new TaskList();
        }
        ui.greet();
        hasStartUp = true;
    }

    /**
     * Handles user input
     */
    public void handleUserInput(String input) {
        try {
            if (!hasStartUp) {
                startUp(input);
            } else {
                try {
                    Action action = Parser.parse(input);
                    action.execute(taskList, ui);
                    if (action.isExit()) {
                        isRunning = false;
                    }
                } catch (ChirpException e) {
                    ui.inputError(e.getMessage());
                }
                fileManager.saveTasks(taskList);
            }
        } catch (Exception e) {
            ui.fatalError(e.getMessage());
            isRunning = false;
        }
    }

    /**
     * Returns isRunning boolean
     *
     * @return isRunning
     */
    public boolean getIsRunning() {
        return isRunning;
    }
}
